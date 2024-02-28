package pageObject;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeLoanPage extends BasePage {

	public HomeLoanPage(WebDriver driver) {
		super(driver);	
	}

	@FindBy(xpath="//*[@id=\"menu-item-dropdown-2696\"]")
	WebElement CalculatorMenu;
	
	@FindBy(xpath="//*[@id=\"menu-item-3294\"]/a")
	WebElement HomeLoanEmiCalculatorMenu;
	
	@FindBy(xpath="//*[@id=\"homeprice\"]")
	WebElement homePrice;
	
	@FindBy(xpath="//*[@id=\"downpayment\"]")
	WebElement downPayment;
	
	@FindBy(xpath="//*[@id=\"homeloaninsuranceamount\"]")
	WebElement loanInsurance;
	
	@FindBy(xpath="//*[@id=\"homeloaninterest\"]")
	WebElement loanInterest;
	
	@FindBy(xpath="//*[@id=\"homeloanterm\"]")
	WebElement loanTenure;
	
	@FindBy(xpath="//*[@id=\"loanfees\"]")
	WebElement loanFee;
	
	@FindBy(xpath="//*[@id=\"startmonthyear\"]")
	WebElement calender;
	
	@FindBy(xpath="(//span[contains(text(),'Feb')])[1]")
	WebElement febMonth;
	
	@FindBy(xpath="//*[@id=\"yearheader\"]")
	WebElement year;
	
	@FindBy(id="//*[@id=\"paymentschedule\"]/table/tbody/tr[1]")
	WebElement headers;
	
	@FindBy(id="paymentschedule")
	WebElement table;
	
	@FindBy(xpath="//tr[@class='row no-margin yearlypaymentdetails']//td")
	List<WebElement> allRows;
	
	public WebElement getTable() {
		return table;
	}
	
	public void CalculatorMenu() {
		CalculatorMenu.click();
	}
	
	public void HomeLoanEmiCalculatorMenu() {
		HomeLoanEmiCalculatorMenu.click();
	}
	
	public void homePrice(String value) {
		homePrice.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		homePrice.sendKeys(Keys.BACK_SPACE);
		homePrice.sendKeys(value);
	}
	
	public void downPayment(String value) {
		downPayment.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		downPayment.sendKeys(Keys.BACK_SPACE);
		downPayment.sendKeys(value);
	}
	
	public void loanInsurance(String value) {
		loanInsurance.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		loanInsurance.sendKeys(Keys.BACK_SPACE);
		loanInsurance.sendKeys(value);
	}
	
	public void loanInterest(String value) {
		loanInterest.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		loanInterest.sendKeys(Keys.BACK_SPACE);
		loanInterest.sendKeys(value);
	}
	
	public void loanTenure(String value) {
		loanTenure.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		loanTenure.sendKeys(Keys.BACK_SPACE);
		loanTenure.sendKeys(value);
	}
	
	public void loanFee(String value) {
		loanFee.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		loanFee.sendKeys(Keys.BACK_SPACE);
		loanFee.sendKeys(value);
	}
	
	public void calender() {
		calender.click();
	}
	
	public void febMonth() {
		febMonth.click();
	}
	
	public void scroll() {
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView()",year);
	}
	
	public List<WebElement> getAllRows() {
		return allRows;
	}
}
