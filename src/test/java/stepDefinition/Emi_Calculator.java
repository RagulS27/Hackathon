package stepDefinition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import baseTest.BaseClass;
import io.cucumber.java.en.*;
import pageObject.CarLoanPage;
import pageObject.EMIPage;
import pageObject.HomeLoanPage;
import utils.ExcelUtils;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;



public class Emi_Calculator{
	
			//WebDriver driver;
			Properties p;
			CarLoanPage cr;
			HomeLoanPage lr;
			EMIPage em;
			String PrincipalAmount;
			String InterestAmount;
			String yearSlider;
			String monthSlider;
			Hooks h=new Hooks();
			WebDriver driver = BaseClass.getDriver();
			
		@Given("the user is on carLoan page")
		public void the_user_is_on_car_loan_page() throws IOException, Exception {
		    BaseClass.getLogger().info("********Go to EMI Calcultor**********");
		    cr=new CarLoanPage(driver);
			cr.clickCarLoan();
		    
		}

		@When("user enter the valid credentials\\(amount:{string},interest:{string},term:{string})")
		public void user_enter_the_valid_credentials_amount_interest_term(String string, String string2, String string3) {
			BaseClass.getLogger().info("********Enter the Valid Inputs**********");
		    cr.enterAmount("1500000");
		    cr.enterInterest("9.5");
		    cr.enterTerm("1");
		}

		@Then("user move to the table")
		public void user_move_to_the_table() {
			BaseClass.getLogger().info("********Click the Year Button**********");
		    cr.scroll();
		    cr.clickYear();
		}

		@Then("user should see the principal amount and interest amount")
		public void user_should_see_the_principal_amount_and_interest_amount() {
			BaseClass.getLogger().info("********Extract the Principal and Interest Amount**********");
			PrincipalAmount=cr.getAmount();
			InterestAmount=cr.getInterest();
		    System.out.println("Principal Amount:"+cr.getAmount());
		    System.out.println("Interest Amount:"+cr.getInterest());
		    
		}

		@Given("the user click the homeLoan menu")
		public void the_user_click_the_home_loan_menu() {
			BaseClass.getLogger().info("********Click the Home Loan Menu**********");
			lr=new HomeLoanPage(driver);
			lr.CalculatorMenu();
			lr.HomeLoanEmiCalculatorMenu();
		}

		@When("the user enter the valid credentials")
		public void the_user_enter_the_valid_credentials() {
			BaseClass.getLogger().info("*********Enter the Valid Inputs**********");
		    lr.homePrice("4000000");
		    lr.downPayment("20");
		    lr.loanInsurance("10000");
		    lr.loanInterest("9");
		    lr.loanTenure("10");
		    lr.loanFee(".25");
		    lr.calender();
		    lr.febMonth();
		    
		}

		@Then("the user move to payment schedule table")
		public void the_user_move_to_payment_schedule_table() {
			BaseClass.getLogger().info("*********Move to payment schedule table**********");
		    lr.scroll();
		}

		@Then("the user extract the data")
		public void the_user_extract_the_data() throws IOException {
			BaseClass.getLogger().info("*********Extract the Year on Year value from Table**********");
			List<String> Heaader =new ArrayList<String>();
			List<String> Data =new ArrayList<String>();
		    List<WebElement> headers = (lr.getTable()).findElements(By.tagName("th"));
		    for(WebElement header:headers) {
				//String text=header.getText();
				if(!header.getText().equals("")) {
					Heaader.add(header.getText());
				}
			}
			System.out.println(Heaader);
			lr.getAllRows();
			
			for(int i=0;i<lr.getAllRows().size();i++)
				{
					 System.out.println(".....row ="+lr.getAllRows().get(i).getText());
					 Data.add(lr.getAllRows().get(i).getText());
			}
			ExcelUtils.excel(PrincipalAmount,InterestAmount,Heaader,Data);
		}
		
		@Given("the user open the loan calculator")
		public void the_user_open_the_loan_calculator() {
			BaseClass.getLogger().info("*********Move to EMI Calculator menu**********");
		    em=new EMIPage(driver);
		    em.clicCalculatorMenu();
		    em.clickLoanCalculatorMenu();	    
		}

		@When("the user enter the inputs")
		public void the_user_enter_the_inputs() {
			BaseClass.getLogger().info("*********Enter the Valid Inputs**********");
			em.enterAmount("2000000");
		    em.enterInterest("9.5");
		    em.enterTerm("10");
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}

		@Then("the user check the UI")
		public void the_user_check_the_ui() {
			BaseClass.getLogger().info("*********Checking the UI of Loan Calculator Page**********");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			if(em.amountTextBox().isDisplayed()) {
				System.out.println("Loan amount Text Box is Displayed");
			}
			else {
				System.out.println("Loan amount Text Box is not Displayed");
			}
			if(em.interestTextBox().isDisplayed()) {
				System.out.println("Interest amount Text Box is Displayed");
			}
			else {
				System.out.println("Interest amount Text Box is not Displayed");
			}
			if(em.termTextBox().isDisplayed()) {
				System.out.println("Term Text Box is Displayed");
			}
			else {
				System.out.println("Term Text Box is not Displayed");
			}
			
			if(em.feeTextBox().isDisplayed()) {
				System.out.println("Fee amount Text Box is Displayed");
			}
			else {
				System.out.println("Fee amount Text Box is not Displayed");
			}
			
			if(em.checkSlider().isEnabled()) {
				System.out.println("Loan amount Slider is Enabled");
			}
			else {
				System.out.println("Loan amount Slider is Disabled");
			}
			Boolean amountBox=em.amountTextBox().isDisplayed();
			Boolean interestBox=em.interestTextBox().isDisplayed();
			Boolean termBox=em.termTextBox().isDisplayed();
			Boolean slider=em.checkSlider().isEnabled();
			Boolean feeBox=em.feeTextBox().isDisplayed();
			Boolean interestSlider=em.checkInterestSlider().isEnabled();
			Boolean feeSlider=em.checkLoanFeesSlider().isEnabled();
			Boolean amountSlider=em.checkAmountSlider().isEnabled();
			BaseClass.getLogger().info("Validating the Expected message!");

			Assert.assertEquals(amountBox,true);
			Assert.assertEquals(interestBox,true);
			Assert.assertEquals(termBox,true);
			Assert.assertEquals(feeBox,true);
			Assert.assertEquals(slider,true);
			Assert.assertEquals(interestSlider,true);
			Assert.assertEquals(feeSlider,true);
			Assert.assertEquals(amountSlider,true);
			
		}
		

		@Then("user check the changes in slider")
		public void user_check_the_changes_in_slider() {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			BaseClass.getLogger().info("*********Check the changes in Slider**********");
			em.clickYear();
			yearSlider=em.getSlider();
			em.clickMonth();
			monthSlider=em.getSlider();
			Assert.assertEquals(yearSlider, monthSlider);
			System.out.println("EMI Calculator Verified\n");
			
		}
		

		@Given("the user is on emi Loan Calculator")
		public void the_user_is_on_emi_loan_calculator() {
			BaseClass.getLogger().info("*********Move to Emi Calculator Page**********");
			em=new EMIPage(driver);
		    em.clicCalculatorMenu();
		    em.clickLoanCalculatorMenu();
		    em.LoanAmountMenu();

		}

		@When("user enter the loan valid credentials")
		public void user_enter_the_loan_valid_credentials() {
			BaseClass.getLogger().info("*********Enter the Valid Inputs**********");
			em.enterEmi("25000");
		    em.enterInterest("10.75");
		    em.enterTerm("10");
		    em.enterFee("10000");
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    
		}
		@Then("the user check the loan UI")
		public void the_user_check_the_loan_UI() {
			BaseClass.getLogger().info("*********Checking the UI of EMI Calculator Page**********");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			if(em.emiTextBox().isDisplayed()) {
				System.out.println("EMI Text Box is Displayed");
			}
			else {
				System.out.println("EMI Text Box is not Displayed");
			}
			if(em.interestTextBox().isDisplayed()) {
				System.out.println("Interest amount Text Box is Displayed");
			}
			else {
				System.out.println("Interest amount Text Box is not Displayed");
			}
			if(em.termTextBox().isDisplayed()) {
				System.out.println("Term Text Box is Displayed");
			}
			else {
				System.out.println("Term Text Box is not Displayed");
			}
			
			if(em.feeTextBox().isDisplayed()) {
				System.out.println("Fee Text Box is Displayed");
			}
			else {
				System.out.println("Fee Text Box is not Displayed");
			}
			
			if(em.checkSlider().isEnabled()) {
				System.out.println("Loan amount Slider is Enabled");
			}
			else {
				System.out.println("Loan amount Slider is Disabled");
			}
			
			
			Boolean emiBox=em.emiTextBox().isDisplayed();
			Boolean interestBox=em.interestTextBox().isDisplayed();
			Boolean termBox=em.termTextBox().isDisplayed();
			Boolean slider=em.checkSlider().isEnabled();
			Boolean feeBox=em.feeTextBox().isDisplayed();
			Boolean emiSlider=em.checkLoanEmiSlider().isEnabled();
			Boolean interestSlider=em.checkInterestSlider().isEnabled();
			Boolean feeSlider=em.checkLoanFeesSlider().isEnabled();
			
			BaseClass.getLogger().info("Validating the Expected message!");
			Assert.assertEquals(emiBox,true);
			Assert.assertEquals(interestBox,true);
			Assert.assertEquals(termBox,true);
			Assert.assertEquals(feeBox,true);
			Assert.assertEquals(slider,true);
			Assert.assertEquals(emiSlider,true);
			Assert.assertEquals(interestSlider,true);
			Assert.assertEquals(feeSlider,true);
		}
		
		@And("user check the change in slider")
		public void user_check_the_change_in_slider() {
			BaseClass.getLogger().info("*********Check the changes in Slider**********");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			em.clickYear();
			yearSlider=em.getSlider();
			em.clickMonth();
			monthSlider=em.getSlider();
			Assert.assertEquals(yearSlider, monthSlider);
			System.out.println("Loan Calculator Verified\n");
		}
		
		@Given("the user open the loan Tenure calculator")
		public void the_user_open_the_loan_tenure_calculator() {
			BaseClass.getLogger().info("*********Move to Loan Tenure Calculator Page**********");
			em=new EMIPage(driver);
		    em.clicCalculatorMenu();
		    em.clickLoanCalculatorMenu();
		    em.clickLoanTenure();
		}

		@Then("the user check the loan Tenure UI")
		public void the_user_check_the_loan_tenure_ui() {
			BaseClass.getLogger().info("*********Checking the UI of EMI Calculator Page**********");
			EMIPage emi = new EMIPage(driver);
			emi.clickLoanTenure();
			
			Boolean emiBox=emi.emiTextBox().isDisplayed();
			Boolean interestBox=emi.interestTextBox().isDisplayed();
			Boolean loanBox=emi.amountTextBox().isDisplayed();
			Boolean slider=emi.checkSlider().isEnabled();
			Boolean feeBox=emi.feeTextBox().isDisplayed();
			Boolean interestSlider=emi.checkInterestSlider().isEnabled();
			Boolean feeSlider=emi.checkLoanFeesSlider().isEnabled();
			Boolean amountSlider=emi.checkAmountSlider().isEnabled();
			
			if(em.emiTextBox().isDisplayed()) {
				System.out.println("EMI Text Box is Displayed");
			}
			else {
				System.out.println("EMI amount Text Box is not Displayed");
			}
			if(em.interestTextBox().isDisplayed()) {
				System.out.println("Interest amount Text Box is Displayed");
			}
			else {
				System.out.println("Interest amount Text Box is not Displayed");
			}
			if(em.amountTextBox().isDisplayed()) {
				System.out.println("Loan amount Text Box is Displayed");
			}
			else {
				System.out.println("Loan amount Text Box is not Displayed");
			}
			
			if(em.feeTextBox().isDisplayed()) {
				System.out.println("Fee amount Text Box is Displayed");
			}
			else {
				System.out.println("Fee amount Text Box is not Displayed");
			}
			
			if(em.checkSlider().isEnabled()) {
				System.out.println("Loan amount Slider is Enabled");
			}
			else {
				System.out.println("Loan amount Slider is Disabled");
			}
			
			BaseClass.getLogger().info("Validating the Expected message!");
			Assert.assertEquals(emiBox,true);
			Assert.assertEquals(interestBox,true);
			Assert.assertEquals(loanBox,true);
			Assert.assertEquals(feeBox,true);
			Assert.assertEquals(slider,true);
			Assert.assertEquals(interestSlider,true);
			Assert.assertEquals(feeSlider,true);
			Assert.assertEquals(amountSlider,true);
			System.out.println("------------UI Check Verified--------------");
		}
		
	} 

