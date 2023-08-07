package WebDriverManager;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Chrome {
	
	public static WebDriver driver;
	@Test
	public static void S4Chrome() {
		
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\mrufu\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriverManager.chromedriver().arch64().setup();;
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--window-position=0,0");
		options.addArguments("--window-size=1840,1080");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-gpu");
		
	    driver = new ChromeDriver(options);  
	    driver.get("http://www.google.com");
	    driver.manage().window().maximize();
	    System.out.println(driver.getTitle());
	    driver.quit();
	}
}