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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.locators.RelativeLocator.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
//import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.michaelpage.common.utils.MichaelpageConstants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RelativeLocators_FF2 {

	private WebDriver driver;
	private WebDriverWait webDriverWait;
	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public ExtentTest test;
	public ExtentTest node;

	@BeforeSuite
	public void setUpClass() {

		WebDriverManager.firefoxdriver().setup();
		System.out.println("In BeforeSuite");

		spark = new ExtentSparkReporter("ExtentReports.html");	//https://www.extentreports.com/docs/versions/5/java/index.html

		spark.config().setTheme(Theme.STANDARD);
		spark.config().setReportName("ZipCodes > BackBone.js App");
		spark.config().setEncoding("utf-8");
		spark.config().setDocumentTitle("ZipCodes");

		extent = new ExtentReports();
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.attachReporter(spark);

		test = extent.createTest("BackBone.js > Getting Zip Codes");
		node = test.createNode("WebDriverManager Started");
	}

	@BeforeTest
	public void setUp() throws ClassNotFoundException {

		//		ChromeOptions options = new ChromeOptions();
		//		//ChromeOptions options = new ChromeOptions();
		//		options.setAcceptInsecureCerts(true);
		//		options.setCapability(CapabilityType.BROWSER_NAME,"chrome");
		//		options.addArguments("start-maximized");
		//		options.addArguments("--js-flags=--expose-gc");
		//		options.addArguments("--enable-precise-memory-info");
		//		options.addArguments("--disable-popup-blocking");
		//		options.addArguments("--disable-default-apps");
		//		options.addArguments("--disable-notifications");
		//		options.addArguments("disable-infobars");
		//		//options.addArguments("--window-size=1920,1080");
		//		options.addArguments("--remote-allow-origins=*");

		FirefoxProfile fxProfile = new FirefoxProfile();
		fxProfile.setAcceptUntrustedCertificates(false);
		fxProfile.setAssumeUntrustedCertificateIssuer(true);
		fxProfile.setPreference("xpinstall.signatures.required", false);
		fxProfile.setPreference("network.cookie.cookieBehavior", 1);
		fxProfile.setPreference("browser.download.folderList", 2);
		fxProfile.setPreference("browser.download.manager.showWhenStarting", false);
		//fxProfile.setPreference("browser.download.dir", MichaelpageConstants.DOWNLOAD_PATH + "\\");
		fxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
		fxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"application/pdf,application/ms-excel,text/csv,image/jpeg,image/svg+xml,image/png,application/csv,application/vnd.ms-excel,application/octet-stream");

		FirefoxOptions options = new FirefoxOptions();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("moz:firefoxOptions", options);
		//capabilities.setCapability(FirefoxProfile., fxProfile);
		options .setProfile(fxProfile);
		capabilities.setCapability("marionette", true);



		WebDriverManager.firefoxdriver().arch64().setup();

		System.out.println("In BeforeTest"+ options.getBrowserName().toString());
		driver = new FirefoxDriver(options);
		webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//new GherkinKeyword("When"),

		node = test.createNode("FireFoxOptions configured");
		node = test.createNode(new GherkinKeyword("When"), 	"FireFoxOptions Configured 2");
	}

	@Test
	public void zipCodeTest() throws Exception {

		driver.navigate().to("https://www.zip-codes.com/search.asp?selectTab=3");
		node = test.createNode("Opened the Website");
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
		node = test.createNode("Highlighted the City TextBox");

		//Using RelativeLocator of City using select 
		WebElement elm_City_rl = driver.findElement(RelativeLocator.with(By.xpath("//input[@name='fld-city']")).above(By.xpath("//div[@class='frm-group'][label='State:']//select[@class='form-control']")) ); // In pom.xml selenium-java 4.0.0 use this to use with
		System.out.println(elm_City_rl.isEnabled() );
		highLighterMethod(driver, elm_City_rl );
		elm_City_rl.sendKeys("New York");
		Thread.sleep(1000);
		node = test.createNode("Given Value in the City TextBox");

		//Click on Find Zip Codes button
		WebElement btn_FindZipCodes = driver.findElement(with(By.xpath("//input[@value='Find ZIP Codes']")).below(By.xpath("//div[@class='frm-group'][label='State:']//select[@class='form-control']")) );
		highLighterMethod(driver, btn_FindZipCodes );
		btn_FindZipCodes.click();	
		node = test.createNode("Clicked on Find ZipCodes");

		webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@class='statTable']/tbody/tr/td[1]/a")));
		//Get all the ZipCodes under the City
		System.out.println(	driver.findElements(By.xpath("//table[@class='statTable']/tbody/tr/td[1]/a")).size() );
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

		node = test.createNode("Got the ZipCodes and getting some Labels");

		node.log(Status.PASS, "Got the ZipCodes and getting some Labels");
		node.pass("I am ExtentTest Pass");
		node.fail("I am ExtentTest Fail");
		node.fail(MarkupHelper.createLabel("Test Case Failed", ExtentColor.RED));
		node.skip("I am ExtentTest Skip");
		node.warning("I am ExtentTest Warning");

		node.addScreenCaptureFromPath("user.dir"+"/Screenshot.png");

		for (String i : zipInfos) 
			System.out.println(i+"\n");
	}

	private String findZipColumnDataByLabel(String label) {
		return driver.findElement(RelativeLocator.with(By.tagName("td")).toRightOf(By.xpath(String.format("//span[text()='%s:']", label)))).getText();
	}

	@AfterTest
	public void tearDown() {

		node = test.createNode("Closing the Browser");
		extent.flush();
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
				js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
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



