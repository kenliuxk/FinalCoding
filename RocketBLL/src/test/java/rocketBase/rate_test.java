package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;

public class rate_test {

	//TODO - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	@Test
	public void rateTest() {
		double interestRate = 0;
		try {
			interestRate = RateBLL.getRate(640);
		}
		catch(RateException e) {
			e.printStackTrace();
		}
		System.out.println(interestRate);
		assertEquals(interestRate, 5.0, 0.01);
	}
	//TODO - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test(expected = RateException.class)
	public void exceptionTest() throws RateException{
		RateBLL.getRate(500);
	}
	
	@Test
	public void getPaymentTest() {
		System.out.println(RateBLL.getPayment(0.04/12, 360, 300000, 0, false));
		assertTrue(RateBLL.getPayment(0.04/12, 360, 300000, 0, false) == -1432.2458863963616);
	}
	@Test
	public void test() {
		assert(1==1);
	}

}
