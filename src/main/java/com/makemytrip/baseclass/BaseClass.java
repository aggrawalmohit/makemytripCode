package com.makemytrip.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.makemytrip.Listner.WebEventHandler;



public class BaseClass 
{

	public FileInputStream fs;
	public static WebDriver driver ;
	public static Properties prop;
	public static EventFiringWebDriver event_driver;
	
	
	@SuppressWarnings("deprecation")
	public void initialise() 

	{

		try {
			fs = new FileInputStream(new File("C:\\Users\\Aggrawals\\eclipse-workspace\\"
		    + "MakeMyTrip\\src\\main\\java\\com\\makemytrip\\configure\\config.properties"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		prop = new  Properties();
		try {
			prop.load(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(driver==null) 
		{
			if(prop.getProperty("browser").equalsIgnoreCase("chrome")) 
			{
				System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--incognito");
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				driver = new ChromeDriver(capabilities);

			}
			else if(prop.getProperty("browser").equalsIgnoreCase("FF")) 
			{
				System.setProperty("webdriver.gecko.driver", "D:\\selenium\\geckodriver.exe");
				driver = new FirefoxDriver();
				

			}
		}
		
		
		 driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		 driver.manage().window().maximize();
		
		 eventhandler();
		 //EventFiringWebDriver event_driver = new EventFiringWebDriver(driver);
		 //WebEventHandler handler = new WebEventHandler();
		 //event_driver.register(handler);
		 //driver=event_driver;
		 driver.get(prop.getProperty("url"));
		 driver.manage().deleteAllCookies();

	} public void eventhandler() 
	{
		 event_driver = new EventFiringWebDriver(driver);
		 WebEventHandler handler = new WebEventHandler();
		 event_driver.register(handler);
		 driver=event_driver;
	}
}
