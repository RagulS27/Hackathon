package pageObject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class EMICalculator {
	
	WebDriver driver;
	@BeforeTest
	public void setup() {
		
		
		driver =new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://emicalculator.net/");
		driver.manage().window().maximize();
		//driver.findElement(By.xpath("//*[@id=\"car-loan\"]/a")).click();
		
		
	}
	@Test(priority = 1)
	public void emicalculator() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id='car-loan']/a")).click();
		WebElement amount=driver.findElement(By.xpath("//*[@id='loanamount']"));
		WebElement interest=driver.findElement(By.xpath("//*[@id='loaninterest']"));	
		WebElement term=driver.findElement(By.xpath("//*[@id='loanterm']"));
		
		
		amount.clear();
		amount.sendKeys("1500000");
		
		
		interest.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		interest.sendKeys(Keys.BACK_SPACE);
		interest.sendKeys("9.5"); 
		
		term.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		term.sendKeys(Keys.BACK_SPACE);
		term.sendKeys("1",Keys.ENTER);
		//term.sendKeys(Keys.ENTER);
		
	    WebElement year = driver.findElement(By.xpath("//*[@id=\"yearheader\"]"));
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView()",year);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[@id='year2024']")).click();
		Thread.sleep(3000);
		String principalAmount=driver.findElement(By.xpath("//*[@id='monthyear2024']/td/div/table/tbody/tr[1]/td[2]")).getText();
		String intersetAmount=driver.findElement(By.xpath("//*[@id='monthyear2024']/td/div/table/tbody/tr[1]/td[3]")).getText();
		
		System.out.println("Principal amount per month :"+principalAmount);
		System.out.println("Interest amount per month :"+intersetAmount);
	}
	
	public void homeLoan() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id='home-loan']/a")).click();
		Thread.sleep(5000);
		WebElement amount=driver.findElement(By.xpath("//*[@id='loanamount']"));
		WebElement interest=driver.findElement(By.xpath("//*[@id=\"loaninterest\"]"));	
		WebElement term=driver.findElement(By.xpath("//*[@id=\"loanterm\"]"));
		amount.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		amount.clear();
		amount.sendKeys("3000000");
		
		
		interest.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		interest.sendKeys(Keys.BACK_SPACE);
		interest.sendKeys("9.5"); 
		
		term.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		term.sendKeys(Keys.BACK_SPACE);
		term.sendKeys("10");
	}
	
	public void excel() throws InterruptedException {
		Thread.sleep(30000);
		System.out.println("Home Loan Successful");
		
		WebElement table=driver.findElement(By.id("emipaymenttable"));
		List<WebElement> headers = table.findElements(By.tagName("th"));
		for(WebElement header:headers) {
			String text=header.getText();
			System.out.println(text);
		}
		System.out.println("..........................");
		List<WebElement> allRows = table.findElements(By.xpath("(//tr[@class='row no-margin yearlypaymentdetails'])//td"));
		System.out.println(allRows.size());
		
		for(int i=0;i<allRows.size();i++)
			{
				 System.out.println(".....row ="+allRows.get(i).getText());
			
		}
		System.out.println("..........................");
			
	}
	
	

	public void loanCalculator() throws InterruptedException {
		
	    //UI check
		driver.findElement(By.xpath("//*[@id=\"menu-item-dropdown-2696\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"menu-item-2423\"]/a")).click();
		
		WebElement amountTextBox=driver.findElement(By.xpath("//*[@id=\"loanamount\"]"));
		WebElement interestTextBox=driver.findElement(By.xpath("//*[@id=\"loaninterest\"]"));	
		WebElement termTextBox=driver.findElement(By.xpath("//*[@id=\"loanterm\"]"));
		
		amountTextBox.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		amountTextBox.sendKeys(Keys.BACK_SPACE);
		amountTextBox.sendKeys("5000000");
		
		interestTextBox.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		interestTextBox.sendKeys(Keys.BACK_SPACE);
		interestTextBox.sendKeys("7");
		
		termTextBox.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		termTextBox.sendKeys(Keys.BACK_SPACE);
		termTextBox.sendKeys("5");
		
		if(amountTextBox.isEnabled()) {
			System.out.println("Loan amount Text Box is Enabled");
		}
		else {
			System.out.println("Loan amount Text Box is Disabled");
		}
		
		WebElement slider=driver.findElement(By.xpath("//*[@id=\"loanamountslider\"]"));
		
		if(slider.isEnabled()) {
			System.out.println("Loan amount Slider is Enabled");
		}
		else {
			System.out.println("Loan amount Slider is Disabled");
		}
	}
	
	

	public void EMIAmountCalculator() {
		
		WebElement termTextBox1=driver.findElement(By.xpath("//*[@id='loanterm']"));
		termTextBox1.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		termTextBox1.sendKeys(Keys.BACK_SPACE);
		termTextBox1.sendKeys("12");
		
		driver.findElement(By.xpath("//*[@id=\"ltermwrapper\"]/div[1]/div/div/div/div/div/label[1]")).click();
		WebElement slider=driver.findElement(By.xpath("//*[@id=\"loantermslider\"]/span"));
		System.out.println("First year value= "+slider.getAttribute("style"));
		String First_year_value=slider.getAttribute("style");
		
		driver.findElement(By.xpath("//*[@id=\"ltermwrapper\"]/div[1]/div/div/div/div/div/label[2]")).click();
		
		//WebElement slider=driver.findElement(By.xpath("//*[@id=\"loantermslider\"]/span"));
		System.out.println("First month value= "+slider.getAttribute("style"));
		String First_month_value=slider.getAttribute("style");
		
		Assert.assertEquals(First_year_value, First_month_value);
		
		
	}
		
	

	public void loanAmountCalculator() {
		
		WebElement termTextBox2=driver.findElement(By.xpath("//*[@id='loanterm']"));
		termTextBox2.sendKeys(Keys.CONTROL, Keys.chord("a")); 
		termTextBox2.sendKeys(Keys.BACK_SPACE);
		termTextBox2.sendKeys("15");
		
		driver.findElement(By.xpath("//*[@id=\"ltermwrapper\"]/div[1]/div/div/div/div/div/label[1]")).click();
		WebElement slider=driver.findElement(By.xpath("//*[@id=\"loantermslider\"]/span"));
		System.out.println("Second year value= "+slider.getAttribute("style"));
		
		String second_year_value=slider.getAttribute("style");
		
		driver.findElement(By.xpath("//*[@id=\"loan-amount-calc\"]/a[1]")).click();
		driver.findElement(By.xpath("//*[@id=\"ltermwrapper\"]/div[1]/div/div/div/div/div/label[2]")).click();
		
		System.out.println("Second month value= "+slider.getAttribute("style"));
		
		String second_month_value=slider.getAttribute("style");
		
		Assert.assertEquals(second_year_value, second_month_value);
	}
	

	@AfterTest
	void closeBrowser() {
		driver.quit();
	}
	
}
