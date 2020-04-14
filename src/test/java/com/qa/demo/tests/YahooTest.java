package com.qa.demo.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.demo.pages.YahooPage;
import com.qa.driverfactory.DriverFactory;
import com.qa.utils.LogUtils;

@Listeners(com.qa.listeners.ExtentListeners.class)
public class YahooTest extends DemoBaseTest
{
	@Test
	public void YahooTest()
	{
		DriverFactory.getDriver().get("https://www.yahoo.com");
		YahooPage demoPage = pageFactory.getPageObject(YahooPage.class);
		demoPage. yahooTest();
		demoPage.verifyTest2();
		LogUtils.reportInfo(DriverFactory.getDriver().getCurrentUrl());
	}
}
