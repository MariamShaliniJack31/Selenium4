package Basics;

import java.util.Optional;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v114.emulation.Emulation;
import org.testng.annotations.Test;

public class A1_CDP {
	
	@Test
	public void func() throws Exception {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		ChromeDriver driver = new ChromeDriver(options);
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		devTools.send(Emulation.setGeolocationOverride(Optional.of(52.5043),
		                                               Optional.of(13.4501),
		                                               Optional.of(1)));
		Thread.sleep(2000);
		driver.get("https://my-location.org/");
		//driver.quit();
	}
}
