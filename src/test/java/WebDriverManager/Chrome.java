package WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Chrome {
	
	public static void main(String[] args) {
	
		WebDriverManager.chromedriver().arch64().setup();;
		WebDriver driver = new ChromeDriver();
	        
	    driver.get("http://www.google.com");
	    driver.manage().window().maximize();
	    System.out.println(driver.getTitle());
	    driver.quit();
	}
}
