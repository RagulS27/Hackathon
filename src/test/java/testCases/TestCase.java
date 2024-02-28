package testCases;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageObject.EMIPage;
import pageObject.HomeLoanPage;
import pageObject.CarLoanPage;
import utils.ExcelUtils;

import utils.Screenshot;



public class TestCase extends BaseTest{
	
	String PrincipalAmount;
	String InterestAmount;
	String yearSlider;
	String monthSlider;
	public static String path;
	
	@Test(priority=1)
	public void carLoanValidation() throws IOException {
		logger.info("********Starting TestCase Car Loan Validation*********");
		CarLoanPage loan=new CarLoanPage(driver);
		loan.clickCarLoan();
		logger.info("Entering the valid inputs..");
		loan.enterAmount("1500000");
		loan.enterInterest("9.5");
		loan.enterTerm("1");
		loan.scroll();
		loan.clickYear();
		path=Screenshot.screenShot("carLoanValidation", driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		logger.info("Getting the data of Principal and Interest Amount..");
		PrincipalAmount=loan.getAmount();
		InterestAmount=loan.getInterest();
		System.out.println("PrincipalAmount: "+PrincipalAmount);
		System.out.println("InterestAmount:"+InterestAmount);
		logger.info("Validating the Expected message!");
		Assert.assertEquals("₹ 1,19,650", loan.getAmount());
		System.out.println("---------Car Loan Verified-----------");
		logger.info("********Finished TestCase Car Loan Validation*********");
	}
	
	@Test(priority=2)
	public void homeLoanValidation() throws IOException {
		List<String> Heaader =new ArrayList<String>();
		List<String> Data =new ArrayList<String>();
		logger.info("********Starting TestCase Home Loan Validation*********");
		HomeLoanPage hm=new HomeLoanPage(driver);
		hm.CalculatorMenu();
		hm.HomeLoanEmiCalculatorMenu();
		logger.info("Entering the valid inputs..");
		hm.homePrice("4000000");
		hm.downPayment("20");
		hm.loanInsurance("10000");
		hm.loanInterest("9");
		hm.loanTenure("10");
		hm.loanFee("0.25");
		hm.calender();
		hm.febMonth();
		hm.scroll();
		path=Screenshot.screenShot("homeLoanValidation", driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		logger.info("Extract the data from table..");
		WebElement table=driver.findElement(By.id("paymentschedule"));
		List<WebElement> headers = table.findElements(By.tagName("th"));
		for(WebElement header:headers) {
			//String text=header.getText();
			if(!header.getText().equals("")) {
				Heaader.add(header.getText());
			}
		}
		path=Screenshot.screenShot("homeLoanValidation", driver);
		System.out.println(Heaader);
		hm.getAllRows();
		
		for(int i=0;i<hm.getAllRows().size();i++)
			{
				 System.out.println(".....row ="+hm.getAllRows().get(i).getText());
				 Data.add(hm.getAllRows().get(i).getText());
		}
		
		
		
		ExcelUtils.excel(PrincipalAmount,InterestAmount,Heaader,Data);
		System.out.println("----------Home Loan Verified--------------");
		logger.info("********Finished TestCase Home Loan Validation*********");
	}
	
	@Test(priority=3)
	public void UIcheck() throws IOException {
		logger.info("********Starting TestCase UI Check in EMI Calculator*********");

		EMIPage emi = new EMIPage(driver);
		emi.clicCalculatorMenu();
		emi.clickLoanCalculatorMenu();
		logger.info("Entering the valid inputs..");
		emi.enterAmount("2000000");
		emi.enterInterest("9.5");
		emi.enterTerm("10");
		emi.enterFee("10000");
		logger.info("Checking the UI of TextBox and Scales ..");
		path=Screenshot.screenShot("UIcheck homeLoanValidation", driver);
		if(emi.amountTextBox().isDisplayed()) {
			System.out.println("Loan amount Text Box is Displayed");
		}
		else {
			System.out.println("Loan amount Text Box is not Displayed");
		}
		if(emi.interestTextBox().isDisplayed()) {
			System.out.println("Interest amount Text Box is Displayed");
		}
		else {
			System.out.println("Interest amount Text Box is not Displayed");
		}
		if(emi.termTextBox().isDisplayed()) {
			System.out.println("Term Text Box is Displayed");
		}
		else {
			System.out.println("Term Text Box is not Displayed");
		}
		
		if(emi.feeTextBox().isDisplayed()) {
			System.out.println("Fee amount Text Box is Displayed");
		}
		else {
			System.out.println("Fee amount Text Box is not Displayed");
		}
		
		if(emi.checkSlider().isEnabled()) {
			System.out.println("Loan amount Slider is Enabled");
		}
		else {
			System.out.println("Loan amount Slider is Disabled");
		}
		
		
		Boolean amountBox=emi.amountTextBox().isDisplayed();
		Boolean interestBox=emi.interestTextBox().isDisplayed();
		Boolean termBox=emi.termTextBox().isDisplayed();
		Boolean slider=emi.checkSlider().isEnabled();
		Boolean feeBox=emi.feeTextBox().isDisplayed();
		
		logger.info("Validating the Expected message!");

		Assert.assertEquals(amountBox,true);
		Assert.assertEquals(interestBox,true);
		Assert.assertEquals(termBox,true);
		Assert.assertEquals(feeBox,true);
		Assert.assertEquals(slider,true);

		logger.info("********Finished TestCase UI Check in EMI Calculator*********");
	}
	
	@Test(priority=4)
	public void emiCalulatorValidation() throws IOException  {
		logger.info("********Starting TestCase EMI Calculator*********");
		EMIPage emi = new EMIPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		emi.clickYear();
		logger.info("Clicked on year button..");
		yearSlider=emi.getSlider();
		emi.clickMonth();
		logger.info("Clicked on month button..");
		path=Screenshot.screenShot("emiCalulatorValidation", driver);
		monthSlider=emi.getSlider();
		logger.info("Validating the Expected message!");
		Assert.assertEquals(yearSlider, monthSlider);
		System.out.println("----------EMI Calculator Verified-------------");
		logger.info("********Finished TestCase EMI Calculator*********");
		
	}
	
	@Test(priority = 5)
	public void loanCalculatorUICheck() throws IOException {
		logger.info("********Starting TestCase UI Check in Loan Calculator*********");
		EMIPage emi = new EMIPage(driver);
		logger.info("Entering the valid inputs..");
		emi.LoanAmountMenu();
		emi.enterEmi("25000");
		emi.enterInterest("9.5");
		emi.enterTerm("10");
		emi.enterFee("10000");
		path=Screenshot.screenShot("UIcheck loanCalculatorUICheck", driver);
		if(emi.emiTextBox().isDisplayed()) {
			System.out.println("EMI Text Box is Displayed");
		}
		else {
			System.out.println("EMI Text Box is not Displayed");
		}
		if(emi.interestTextBox().isDisplayed()) {
			System.out.println("Interest amount Text Box is Displayed");
		}
		else {
			System.out.println("Interest amount Text Box is not Displayed");
		}
		if(emi.termTextBox().isDisplayed()) {
			System.out.println("Term Text Box is Displayed");
		}
		else {
			System.out.println("Term Text Box is not Displayed");
		}
		
		if(emi.feeTextBox().isDisplayed()) {
			System.out.println("Fee Text Box is Displayed");
		}
		else {
			System.out.println("Fee Text Box is not Displayed");
		}
		
		if(emi.checkSlider().isEnabled()) {
			System.out.println("Loan amount Slider is Enabled");
		}
		else {
			System.out.println("Loan amount Slider is Disabled");
		}
		
		
		Boolean emiBox=emi.emiTextBox().isDisplayed();
		Boolean interestBox=emi.interestTextBox().isDisplayed();
		Boolean termBox=emi.termTextBox().isDisplayed();
		Boolean slider=emi.checkSlider().isEnabled();
		Boolean feeBox=emi.feeTextBox().isDisplayed();
		
		logger.info("Validating the Expected message!");
		Assert.assertEquals(emiBox,true);
		Assert.assertEquals(interestBox,true);
		Assert.assertEquals(termBox,true);
		Assert.assertEquals(feeBox,true);
		Assert.assertEquals(slider,true);
		logger.info("********Finished TestCase UI Check in Loan Calculator*********");
	}
	
	
	@Test(priority=6)
	public void loanCalulatorValidation() throws IOException {
		logger.info("********Starting TestCase Loan Calculator*********");
		EMIPage emi = new EMIPage(driver);
		emi.clickYear();
		logger.info("Clicked on year button..");
		yearSlider=emi.getSlider();
		emi.clickMonth();
		logger.info("Clicked on month button..");
		monthSlider=emi.getSlider();
		path=Screenshot.screenShot("loanCalulatorValidation", driver);
		Assert.assertEquals(yearSlider, monthSlider);
		System.out.println("------------Loan Calculator Verified--------------");
		logger.info("********Finished TestCase Loan Calculator*********");
	}
	

	@Test(priority=7)
	public void loanTenureCalculator() throws IOException {
		logger.info("********Starting TestCase UI Check for Loan Tenure Calculator*********");
		EMIPage emi = new EMIPage(driver);
		emi.clickLoanTenure();
		path=Screenshot.screenShot("loanTenureCalulatorValidation", driver);
		Boolean emiBox=emi.emiTextBox().isDisplayed();
		Boolean interestBox=emi.interestTextBox().isDisplayed();
		Boolean loanBox=emi.amountTextBox().isDisplayed();
		Boolean slider=emi.checkSlider().isEnabled();
		Boolean feeBox=emi.feeTextBox().isDisplayed();
		if(emi.emiTextBox().isDisplayed()) {
			System.out.println("EMI Text Box is Displayed");
		}
		else {
			System.out.println("EMI amount Text Box is not Displayed");
		}
		if(emi.interestTextBox().isDisplayed()) {
			System.out.println("Interest amount Text Box is Displayed");
		}
		else {
			System.out.println("Interest amount Text Box is not Displayed");
		}
		if(emi.amountTextBox().isDisplayed()) {
			System.out.println("Loan amount Text Box is Displayed");
		}
		else {
			System.out.println("Loan amount Text Box is not Displayed");
		}
		
		if(emi.feeTextBox().isDisplayed()) {
			System.out.println("Fee amount Text Box is Displayed");
		}
		else {
			System.out.println("Fee amount Text Box is not Displayed");
		}
		
		if(emi.checkSlider().isEnabled()) {
			System.out.println("Loan amount Slider is Enabled");
		}
		else {
			System.out.println("Loan amount Slider is Disabled");
		}
		
		logger.info("Validating the Expected message!");
		Assert.assertEquals(emiBox,true);
		Assert.assertEquals(interestBox,true);
		Assert.assertEquals(loanBox,true);
		Assert.assertEquals(feeBox,true);
		Assert.assertEquals(slider,true);
		logger.info("********Finished TestCase UI Check for Loan Tenure Calculator*********");
		System.out.println("------------UI Check Verified--------------");
	}
}

