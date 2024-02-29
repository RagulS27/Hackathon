package pageObject;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EMIPage extends BasePage{

	public EMIPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//*[@id=\"menu-item-dropdown-2696\"]")
	WebElement CalculatorMenu;
	
	@FindBy(xpath="//*[@id='menu-item-2423']/a")
	WebElement loanCalculatorMenu;
	
	@FindBy(xpath="//*[@id='loan-amount-calc']")
	WebElement loanMenu;
	
	@FindBy(xpath="//*[@id=\"loanamount\"]")
	WebElement amountTextBox;
	
	@FindBy(xpath="//*[@id=\"loaninterest\"]")
	WebElement interestTextBox;
	
	@FindBy(xpath="//*[@id=\"loanterm\"]")
	WebElement termTextBox;
	
	@FindBy(xpath="//*[@id='loanfees']")
	WebElement feeTextBox ;
	
	@FindBy(xpath="//*[@id='ltermwrapper']/div[1]/div/div/div/div/div/label[1]")
	WebElement yearButton;
	
	@FindBy(xpath="//*[@id='ltermwrapper']/div[1]/div/div/div/div/div/label[2]")
	WebElement monthButton;
	
	@FindBy(xpath="//*[@id='loantermslider']/span")
	WebElement slider;
	
	@FindBy(xpath="//*[@id='loaninterestslider']/span")
	WebElement interestSlider;
	
	@FindBy(xpath="//*[@id=\"loanamountslider\"]")
	WebElement amountSlider;
	
	@FindBy(xpath="//*[@id=\"loanfeesslider\"]")
	WebElement loanFeesSlider;
	
	@FindBy(xpath="//*[@id=\"loan-amount-calc\"]/a[1]")
	WebElement LoanAmountMenu;
	
	@FindBy(xpath="//*[@id='loanemislider']")
	WebElement loanEmiSlider;
	
	@FindBy(xpath="//*[@id='loanemi']")
	WebElement emiTextBox ;
	
	@FindBy(xpath="//*[@id='loan-tenure-calc']")
	WebElement loanTenureMenu ;
	
	public void clickLoanTenure() {
		loanTenureMenu.click();
	}
	
	public WebElement emiTextBox() {
		return emiTextBox;
	}
	
	public void LoanAmountMenu() {
		LoanAmountMenu.click();
	}
	
	public void clicCalculatorMenu() {
		CalculatorMenu.click();
	}
	
	public void clickLoanCalculatorMenu() {
		loanCalculatorMenu.click();
	}
	
	public WebElement amountTextBox() {
		return amountTextBox;
	}
	
	public WebElement interestTextBox() {
		return interestTextBox;
	}
	
	public WebElement termTextBox() {
		return termTextBox;
	}
	
	public WebElement feeTextBox() {
		return feeTextBox;
	}
	
	public WebElement checkSlider() {
		return slider;
	}
	
	public WebElement checkInterestSlider() {
		return interestSlider;
	}
	
	public WebElement checkAmountSlider() {
		return amountSlider;
	}
	
	public WebElement checkLoanFeesSlider() {
		return loanFeesSlider;
	}
	
	public WebElement checkLoanEmiSlider() {
		return loanEmiSlider;
	}
	
	public void enterAmount(String value) {
		amountTextBox.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		amountTextBox.sendKeys(Keys.BACK_SPACE);
		amountTextBox.sendKeys(value);
	}
	
	public void enterInterest(String value) {
		interestTextBox.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		interestTextBox.sendKeys(Keys.BACK_SPACE);
		interestTextBox.sendKeys(value);
	}
	
	public void enterTerm(String value) {
		termTextBox.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		termTextBox.sendKeys(Keys.BACK_SPACE);
		termTextBox.sendKeys(value);
	}
	
	public void enterFee(String value) {
		feeTextBox.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		feeTextBox.sendKeys(Keys.BACK_SPACE);
		feeTextBox.sendKeys(value);
	}
	
	public void enterEmi(String value) {
		emiTextBox.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		emiTextBox.sendKeys(Keys.BACK_SPACE);
		emiTextBox.sendKeys(value);
	}
	
	public void clickYear() {
		yearButton.click();
	}
	
	public void clickMonth() {
		monthButton.click();
	}
	
	public String getSlider() {
		return slider.getAttribute("style");
	}

}
	
