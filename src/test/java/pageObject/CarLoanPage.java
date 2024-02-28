package pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

	public class CarLoanPage extends BasePage {

		public CarLoanPage(WebDriver driver) {
			super(driver);
		}
	
	@FindBy(xpath="//*[@id='car-loan']")
	WebElement carLoan;
	
	@FindBy(xpath="//*[@id='loanamount']")
	WebElement amount;
	
	@FindBy(xpath="//*[@id='loaninterest']")
	WebElement interest;
	
	@FindBy(xpath="//*[@id='loanterm']")
	WebElement term;
	
	@FindBy(xpath="//*[@id='yearheader']")
	WebElement yearHeader;
	
	@FindBy(xpath="//td[@id='year2024']")
	WebElement year2024;
	
	@FindBy(xpath="//*[@id=\"monthyear2024\"]/td/div/table/tbody/tr[1]/td[2]")
	WebElement principalAmount;
	
	@FindBy(xpath="//*[@id=\"monthyear2024\"]/td/div/table/tbody/tr[1]/td[3]")
	WebElement interestAmount;
	
	@FindBy(xpath="//*[@id='home-loan']/a")
	WebElement homeLoan;
	
	
	public void clickCarLoan() {
		 carLoan.click();
	}
	
	public void enterAmount(String value) {
		amount.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		amount.sendKeys(Keys.BACK_SPACE);
		amount.sendKeys(value);
	}
	
	public void enterInterest(String value) {
		interest.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		interest.sendKeys(Keys.BACK_SPACE);
		interest.sendKeys(value);
	}
	
	public void enterTerm(String value) {
		term.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		term.sendKeys(Keys.BACK_SPACE);
		term.sendKeys(value,Keys.ENTER);
	}
	
	public void scroll() {
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView()",yearHeader);
	}
	
	public void clickYear() {
		year2024.click();
	}
	
	public String getAmount() {
		return principalAmount.getText();
	}
	
	public String getInterest() {
		return interestAmount.getText();
	}
	
	public void clickHomeLoan() {
		homeLoan.click();
	}
	
	
	
}
	

