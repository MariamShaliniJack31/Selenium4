package Selenium4;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.locators.RelativeLocator.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class RelativeLocators {

	private WebDriver driver;
	private WebDriverWait webDriverWait;

	@BeforeSuite
	public void setUpClass() {

		WebDriverManager.chromedriver().setup();
		System.out.println("In BeforeSuite");
	}
	
	@BeforeTest
	public void setUp() {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("--js-flags=--expose-gc");
		options.addArguments("--enable-precise-memory-info");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-default-apps");
		options.addArguments("--disable-notifications");
		options.addArguments("disable-infobars");
		//options.addArguments("--window-size=1920,1080");
		options.addArguments("--remote-allow-origins=*");
		
		System.out.println("In BeforeTest");
		driver = new ChromeDriver(options);
		webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@Test
	public void zipCodeTest() throws Exception {
		
		driver.navigate().to("https://www.zip-codes.com/search.asp?selectTab=3");
		// In pom.xml selenium-java 4.0.0-alpha-7 used this to work below code. Below Code Working
		//In selenium-java 4.0.0-alpha-7, RelativeLocators.with is not there , only withTagName is there
//		WebElement elm_City = driver.findElement(By.xpath("//label[text()='Town/City:']//following-sibling::input"));
//		elm_City.sendKeys("Sofia Shalini");
//		Thread.sleep(1000);
		
		//Use selenium-java 4.0.0-alpha-7
//		System.out.println("In Test");
//		WebElement lbl_TownCity = driver.findElement(By.xpath("//h4[text()='Find ZIP Codes by City, State, Address, or Area Code']//parent::div//label[text()='Town/City:']"));
//		System.out.println(lbl_TownCity.isDisplayed() );
//		Thread.sleep(2000);
//		highLighterMethod(driver, lbl_TownCity );
//		Thread.sleep(2000);
//		WebElement elm_City_rl = driver.findElement(RelativeLocator.withTagName("input").above(By.xpath("//div[@class='frm-group'][label='State:']//select[@class='form-control']")) ); // In pom.xml selenium-java 4.0.0 use this to use with
//		System.out.println(elm_City_rl.isEnabled() );
//		highLighterMethod(driver, elm_City_rl );
//		elm_City_rl.sendKeys("Sofia Shalini Rufus");
//		Thread.sleep(2000);
		
		//WebElement passwordField = driver.findElement(with(By.tagName("input")).below(emailAddressField));
		//By.xpath("//label[text()='Town/City:']//following-sibling::input"));
		//By.xpath("//input[@name='fld-city']"
		//These are working
		////form[@name='form1']//label[text()='Town/City:']
		//label[text()='Town/City:']//following-sibling::input
		//label[text()='Town/City:']/parent::div/input[@name='fld-city']
		
		//Use selenium-java 4.8.3
		//Getting Town/City: Label
		System.out.println("In Test");
		WebElement lbl_TownCity = driver.findElement(By.xpath("//h4[text()='Find ZIP Codes by City, State, Address, or Area Code']//parent::div//label[text()='Town/City:']"));
		System.out.println(lbl_TownCity.isDisplayed() );
		highLighterMethod(driver, lbl_TownCity );
		
		//Using RelativeLocator of City using select 
		WebElement elm_City_rl = driver.findElement(RelativeLocator.with(By.xpath("//input[@name='fld-city']")).above(By.xpath("//div[@class='frm-group'][label='State:']//select[@class='form-control']")) ); // In pom.xml selenium-java 4.0.0 use this to use with
		System.out.println(elm_City_rl.isEnabled() );
		highLighterMethod(driver, elm_City_rl );
		elm_City_rl.sendKeys("New York");
		Thread.sleep(1000);
		
		//Click on Find Zip Codes button
		WebElement btn_FindZipCodes = driver.findElement(RelativeLocator.with(By.xpath("//input[@value='Find ZIP Codes']")).below(By.xpath("//div[@class='frm-group'][label='State:']//select[@class='form-control']")) );
		highLighterMethod(driver, btn_FindZipCodes );
		btn_FindZipCodes.click();		

		//Get all the ZipCodes under the City
		List<String> zipTableLinks = driver.findElements(By.xpath("//table[@class='statTable']/tbody/tr/td[1]/a")).
				stream().limit(10).map(a -> String.format("https://www.zip-codes.com/%s", a.getAttribute("href"))).collect(Collectors.toList());
		
		System.out.println(zipTableLinks);
		List<String> zipInfos = new ArrayList<>();

		
		zipTableLinks.stream().forEach(l -> {
			driver.navigate().to(l);
			zipInfos.add(findZipColumnDataByLabel("City"));
			zipInfos.add(findZipColumnDataByLabel("State"));
			zipInfos.add(findZipColumnDataByLabel("Zip Code"));
			zipInfos.add(findZipColumnDataByLabel("Longitude"));
			zipInfos.add(findZipColumnDataByLabel("Latitude"));
		});
		
		for (String i : zipInfos) 
			System.out.println(i);
	}
	
	private String findZipColumnDataByLabel(String label) {
		return driver.findElement(RelativeLocator.with(By.tagName("td")).toRightOf(By.xpath(String.format("//span[text()='%s:']", label)))).getText();
	}

	@AfterTest
	public void tearDown() {
		if (driver != null)	 {
			driver.quit();
		}
	}
	
	// Method is for HighLight the element before performing any Actions
	public static void highLighterMethod(WebDriver driver, WebElement elementToHighlight) {
		try {
			String originalStyle = elementToHighlight.getAttribute("style");
			JavascriptExecutor js = (JavascriptExecutor) driver;

			for (int i = 0; i < 2; i++) {
				js.executeScript("arguments[0].setAttribute('style', 'background: black; border: 2px solid red;');",
							elementToHighlight);
					Thread.sleep(150);
					js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", elementToHighlight);
					Thread.sleep(150);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

}
	
	
	
	