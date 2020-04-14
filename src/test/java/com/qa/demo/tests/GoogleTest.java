package com.qa.demo.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.demo.pages.GooglePage;
import com.qa.driverfactory.DriverFactory;
import com.qa.utils.LogUtils;

@Listeners(com.qa.listeners.ExtentListeners.class)
public class GoogleTest extends DemoBaseTest
{
	@Test
	public void DefaultTest()
	{		
		DriverFactory.getDriver().get("https://www.google.com");
		GooglePage demoPage = pageFactory.getPageObject(GooglePage.class);
		demoPage. googleTest();
		demoPage.verifyTest1();
		LogUtils.reportInfo(DriverFactory.getDriver().getCurrentUrl());
	}
	
	@Test
	public void DefaultTest2()
	{		
		DriverFactory.getDriver().get("https://www.google.com");
		GooglePage demoPage = pageFactory.getPageObject(GooglePage.class);
		demoPage. googleTest();
		demoPage.verifyTest1();
		LogUtils.reportInfo(DriverFactory.getDriver().getCurrentUrl());
	}
}
