package WebDriverManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

		DesiredCapabilities capabilities = new DesiredCapabilities();
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--window-position=0,0");
		//options.addArguments("--window-size=1920,1200");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-gpu");

		//To disable"Chrome is being controlled by automated test software" --- first Line is required
		//it's better to use Collections.singletonList instead of Arrays.asList since we are passing in a single argument to excludeSwitches.
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.setExperimentalOption("useAutomationExtension", false);
		

		options.addArguments("disable-infobars");
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-default-apps");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-extensions");
		options.addArguments("start-maximized");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-web-security",  "--allow-running-insecure-content");
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.default_content_settings.cookies", 2);
		options.setExperimentalOption("prefs", prefs);
		
		//String[] switches = {"user-data-dir= C:\\Users\\mrufu\\AppData\\Local\\Google\\Chrome\\User Data\\"  };
		String[] switches = {"user-data-dir="  };
		capabilities.setCapability("chrome.switches", "--disable-extensions");
		capabilities.setCapability("chrome.switches", switches);
		//capabilities.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS, true);
		capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		capabilities.setCapability("cssSelectorsEnabled", true);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(capabilities);
		
		driver = new ChromeDriver(options);  
		driver.get("http://www.google.com");

		System.out.println(driver.getTitle());
		driver.quit();
	}
}

//options.addArguments("--headless", "--window-size=1920,1200");
//driver.manage().window().maximize();