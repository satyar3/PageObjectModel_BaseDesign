package com.qa.driverfactory;

import org.openqa.selenium.WebDriver;

public class DriverFactory
{
	protected WebDriver driver;
	public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();

	//public static RemoteWebDriver driver;
	//public static ThreadLocal<RemoteWebDriver> threadLocal = new ThreadLocal<RemoteWebDriver>();	

	public static WebDriver getDriver()
	{
		return dr.get();
	}

	public static void setDriver(WebDriver driver)
	{
		dr.set(driver);
	}
}
