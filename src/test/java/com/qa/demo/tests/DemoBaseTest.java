package com.qa.demo.tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.drivermanager.DriverManager;
import com.qa.factory.PageFactory;
import com.qa.factory.TestFactory;
import com.qa.utils.LogUtils;

public class DemoBaseTest extends TestFactory
{

	@Override
	protected String getPropertyFilePath()
	{
		return "C:\\Back Up\\P Workspace\\PageObjectModel_Arora\\src\\test\\resources\\properties\\Config.properties";
	}

	@Override
	protected void setDriverPath()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\selenium_drivers\\chromedriver.exe");
	}
	
	@BeforeTest
	public void setUp()
	{
		if(DriverManager.getDriver() == null)
		{
			try
			{
				prop = new Properties();
				fs = new FileInputStream(getPropertyFilePath());
				prop.load(fs);
				setDriverPath();

			} catch (Exception e)
			{
				//System.out.println("Property File Not Found !!!");
				LogUtils.log.error("Property File Not Found !!!");
			}

			if (getProperty("browser").equalsIgnoreCase("Chrome"))
			{
				driver = new ChromeDriver();
			}
			
			pageFactory = new PageFactory(driver);
			DriverManager.setDriver(driver);
			driver.get("https://google.com");
			
		}
		else
		{
			//System.out.println("Not initializing Driver again as it's already initialized");
			LogUtils.log.info("Not initializing Driver again as it's already initialized");
		}
	}
	@AfterTest
	public void tearDown()
	{
		driver.quit();
			
	}
}