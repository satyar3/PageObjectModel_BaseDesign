package com.qa.demo.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.demo.pages.FBPage;
import com.qa.driverfactory.DriverFactory;
import com.qa.utils.LogUtils;

@Listeners(com.qa.listeners.ExtentListeners.class)
public class FBTest extends DemoBaseTest
{
	@Test
	public void FBTest()
	{
		DriverFactory.getDriver().get("https://www.facebook.com");
		FBPage demoPage = pageFactory.getPageObject(FBPage.class);
		demoPage. faceBookTest();
		demoPage.verifyTest3();
		LogUtils.reportInfo(DriverFactory.getDriver().getCurrentUrl());
	}
}
