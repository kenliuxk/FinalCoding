package rocket.app.view;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import eNums.eAction;
import exceptions.RateException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController implements Initializable {

	private MainApp mainApp;
	
	//	TODO - RocketClient.RocketMainController
	@FXML
	private Button btnPayment;
	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpenses;
	@FXML
	private TextField txtCreditScore;
	@FXML
	private TextField txtHouseCost;
	@FXML
	private TextField txtDownPayment;
	@FXML
	private ComboBox loanTerm;
	//	Create private instance variables for:
	//		TextBox  - 	txtIncome
	//		TextBox  - 	txtExpenses
	//		TextBox  - 	txtCreditScore
	//		TextBox  - 	txtHouseCost
	//		ComboBox -	loan term... 15 year or 30 year
	//		Labels   -  various labels for the controls
	//		Button   -  button to calculate the loan payment
	//		Label    -  to show error messages (exception throw, payment exception)
	@FXML
	private Label lblIncome;
	@FXML
	private Label lblExpenses;
	@FXML
	private Label lblCreditScore;
	@FXML
	private Label lblHouseCost;
	@FXML
	private Label lblTerm;
	@FXML
	private Label lblDownPayment;
	@FXML
	public Label lblError;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	//	TODO - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		//	TODO - RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		//	TODO - RocketClient.RocketMainController
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		//			I've created you an instance of lq...  execute the setters in lq
		lq.setdIncome(Double.parseDouble(txtIncome.getText()));
		lq.setdExpenses(Double.parseDouble(txtExpenses.getText()));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lq.setdAmount(Double.parseDouble(txtHouseCost.getText()) - Double.parseDouble(txtDownPayment.getText()));
		if(loanTerm.getSelectionModel().getSelectedItem().toString() == "15 Years")
			lq.setiTerm(180);
		else
			lq.setiTerm(360);

		a.setLoanRequest(lq);
		
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		double firstPayment = lRequest.getdIncome() * 0.28;
		double secondPayment = (lRequest.getdIncome() - lRequest.getdExpenses()) * 0.36;
		double finalPayment;
		if(firstPayment < secondPayment){
			finalPayment = firstPayment;
		}
		else {
			finalPayment = secondPayment;
		}
		
		double payment = lRequest.getdPayment();
		String output;
		if (payment < finalPayment) {
			output = new DecimalFormat("#.##").format(payment);
			String APR = String.valueOf(lRequest.getdRate());
			lblError.setText("Your payment is: $" + output + ", and your APR is: " + APR + "%");
		}
		else {
			output = "House Cost too high.";
			lblError.setText(output);
		}
	}
	
	ObservableList<String> list = FXCollections.observableArrayList("15 Years" , "30 Years");
	
	public void initialize(URL location , ResourceBundle resources) {
		loanTerm.setItems(list);
	}
	
	public void HandleRateException(RateException e){
		lblError.setText("Credit Score Too Low");
	}
	
}
