package Basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SwitchToNewWindow {

	public WebDriver driver;
	
	@Test
	public void setUp() {
		
		WebDriverManager.chromedriver().arch64().setup();
		ChromeOptions options = new ChromeOptions();
		driver = new ChromeDriver(options);
		driver.get("https://www.google.com/");
		System.out.println("1st Window : "+driver.getTitle());
		
		// Opens a new window and switches to new window
		driver.switchTo().newWindow(WindowType.WINDOW);
		//driver.switchTo().window(wndHandle);

		// Opens BrowserStack homepage in the newly opened window
		driver.navigate().to("https://www.browserstack.com/");
		System.out.println("2nd Window : "+driver.getTitle());
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}