package rocketData;

import java.io.Serializable;

public class LoanRequest implements Serializable {

	private int iTerm;
	private double dRate;
	private double dAmount;
	private int iCreditScore;
	private int iDownPayment;
	private double dPayment;
	private double dIncome;
	private double dExpenses;
	
	//	TODO - RocketBLL.LoanRequest
	//			missing attributes...
	//			Income
	//			Expenses
	//			Add these attributes to the class... add getters and setters.
	
	public double getdIncome() {
		return dIncome;
	}
	public void setdIncome(double dIncome) {
		this.dIncome = dIncome;
	}
	public double getdExpenses() {
		return dExpenses;
	}
	public void setdExpenses(double dExpenses) {
		this.dExpenses = dExpenses;
	}
	public LoanRequest() {
		super();
	}
	public int getiTerm() {
		return iTerm;
	}
	public void setiTerm(int iTerm) {
		this.iTerm = iTerm;
	}
	public double getdRate() {
		return dRate;
	}
	public void setdRate(double dRate) {
		this.dRate = dRate;
	}
	public double getdAmount() {
		return dAmount;
	}
	public void setdAmount(double dAmount) {
		this.dAmount = dAmount;
	}
	public int getiCreditScore() {
		return iCreditScore;
	}
	public void setiCreditScore(int iCreditScore) {
		this.iCreditScore = iCreditScore;
	}
	public int getiDownPayment() {
		return iDownPayment;
	}
	public void setiDownPayment(int iDownPayment) {
		this.iDownPayment = iDownPayment;
	}
	public double getdPayment() {
		return dPayment;
	}
	public void setdPayment(double dPayment) {
		this.dPayment = dPayment;
	}
	
	
}
