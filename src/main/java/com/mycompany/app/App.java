package com.mycompany.app;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Hello world!
 *
 */
public class App {
	private static WebDriver driver;
	public static String Sauce_Username = System.getenv("Sauce_Username");
	public static String Sauce_Password = System.getenv("Sauce_Password");
	public static String Sauce_Accesskey = System.getenv("Accesskey");
	public static String Sauce_Tunnel = "Purposefinancial_tunnel"/*System.getenv("Tunnel")*/;
	public static String Sauce_url = "https://" + Sauce_Username + ":" + Sauce_Accesskey
			+ "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
	public static DesiredCapabilities cap = new DesiredCapabilities();
	public static MutableCapabilities sauceoptions = new MutableCapabilities();
	public static MutableCapabilities capabilities = new MutableCapabilities();

	public static void main(String[] args) {
		try {
			
			System.out.println("<<<<Test Starts>>>>");
			RemoteWebDriver driver = null;
			WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			sauceoptions.setCapability("username", Sauce_Username);
			sauceoptions.setCapability("accessKey", Sauce_Accesskey);
			sauceoptions.setCapability("tunnelIdentifier", Sauce_Tunnel);
			sauceoptions.setCapability("extendedDebugging", true);
			sauceoptions.setCapability("seleniumVersion", "3.141.59");
			sauceoptions.setCapability("idleTimeout", 120);
			sauceoptions.setCapability("capturePerformance", true);
			//sauceoptions.setCapability("name", sauceTestName);
			WebDriverManager.chromedriver().arch32().setup();
			ChromeOptions browserOptions = new ChromeOptions();
			browserOptions.setExperimentalOption("w3c", true);
			browserOptions.addArguments("disable-infobars");
			browserOptions.addArguments("disable-extensions");
			browserOptions.setExperimentalOption("useAutomationExtension", false);
			capabilities.setCapability("goog:chromeOptions", browserOptions);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability("platformName", "Windows 10");
			capabilities.setCapability("browserVersion", "latest");
			capabilities.setCapability("browserName", "CHROME");
			capabilities.setCapability("sauce:options", sauceoptions);
			driver = new RemoteWebDriver(new URL(Sauce_url), capabilities);
			driver.setFileDetector(new LocalFileDetector());
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Launching Web Page");
			driver.get("https:google.com");
			System.out.println("Web Page Launched");
			Thread.sleep(10000);
			//driver.findElement(By.xpath("//a[contains(text(),'Start Application')]")).click();
			Thread.sleep(5000);
			System.out.println("<<<<Test Ends>>>>");
			driver.close();
		} catch (InterruptedException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
