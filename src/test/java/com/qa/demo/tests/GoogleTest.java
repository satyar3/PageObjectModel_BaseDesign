package com.qa.demo.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.demo.pages.GooglePage;
import com.qa.drivermanager.DriverManager;
import com.qa.utils.LogUtils;

@Listeners(com.qa.listeners.ExtentListeners.class)
public class GoogleTest extends DemoBaseTest
{
	@Test
	public void DefaultTest()
	{		
		GooglePage demoPage = pageFactory.getPageObject(GooglePage.class);
		demoPage. googleTest();
		demoPage.verifyTest1();
		LogUtils.reportInfo(DriverManager.getDriver().getCurrentUrl());
	}
	
	@Test
	public void DefaultTest2()
	{		
		GooglePage demoPage = pageFactory.getPageObject(GooglePage.class);
		demoPage. googleTest();
		demoPage.verifyTest1();
		LogUtils.reportInfo(DriverManager.getDriver().getCurrentUrl());
	}
}
