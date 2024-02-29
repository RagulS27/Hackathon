package baseTest;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


public class BaseTest {
	public WebDriver driver;
	public Logger logger;
	Properties p;

	
	@BeforeClass
	@Parameters({"os","browser"})
	public void openApp(String os,String br) throws IOException {
		
//		logger=LogManager.getLogger(this.getClass());
//		FileReader file = new FileReader(".//src/test/resources/Config.properties");
//		p=new Properties();
//		p.load(file);
//		
//	
//		
//		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
//			DesiredCapabilities capabalities = new DesiredCapabilities();
//			//os
//			if(os.equalsIgnoreCase("windows")) {
//				capabalities.setPlatform(Platform.WIN11);
//			}
//			else if (os.equalsIgnoreCase("mac")) {
//				capabalities.setPlatform(Platform.MAC);
//			}
//			else {
//				System.out.println("no matching os .....");
//				return;
//			}
//			//browser
//			
//			if(browser.equalsIgnoreCase("chrome")) {
//				driver=new ChromeDriver();			
//			}
//			else if(browser.equalsIgnoreCase("edge")) {
//				driver=new EdgeDriver();
//			}
//			else {
//				System.out.println("no matching browser .....");
//				return;
//			}
//			 driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub") , capabalities);
//		}
//		else if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
//			if(browser.equalsIgnoreCase("chrome")) {
//				driver=new ChromeDriver();
//				logger.info("Chrome browser opened successfully");
//			}
//			else if(browser.equalsIgnoreCase("edge")){
//				driver=new EdgeDriver();
//				logger.info("Edge browser opened successfully");
//			}
//			else {
//				System.out.println("no matching browser......");
//				logger.info("no matching browser......");
//				return;
//			}
//		}
//				driver.get(p.getProperty("appURL"));
//		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//				
//
//				driver.manage().window().maximize();
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		logger=LogManager.getLogger(this.getClass());
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("No Matching os");
			}
			switch(br.toLowerCase()) {
			case  "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case  "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			default:
				System.out.println("No matching browser");
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		else if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch(br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver=new EdgeDriver();
				break;
			default:
				System.out.println("No Matching Browser");
				return;
			}
		}
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
}
