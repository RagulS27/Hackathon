package baseTest;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BaseClass {
	static WebDriver driver;
	static Properties p;
	static Logger logger;
	
	public static WebDriver initilizeBrowser() throws IOException  {
			 if(getProperties().getProperty("execution_env").equalsIgnoreCase("local"))
				{
					switch(getProperties().getProperty("browser").toLowerCase())
					{
					case "chrome":
				        driver=new ChromeDriver();
				        break;
				    case "edge":
				    	driver=new EdgeDriver();
				        break;
				    default:
				        System.out.println("No matching browser");
				        driver=null;
					}
				}

	    driver.manage().deleteAllCookies();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		return driver;
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static Properties getProperties() throws IOException {
		FileReader file=new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\Config.properties");
		p=new Properties();
		p.load(file);
		return p;
		
	}
	
	public static Logger getLogger() {
		logger=LogManager.getLogger();
		return logger;
	}
}
