package com.qa.demo.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.demo.pages.DemoPage;

@Listeners(com.qa.listeners.ExtentListeners.class)
public class DemoTest extends DemoBaseTest
{
	@Test
	public void fun1()
	{
		DemoPage demoPage = pageFactory.getPageObject(DemoPage.class);
		demoPage. searchTest1();
		demoPage.verifyTest1();
	}
	@Test
	public void fun2()
	{
		DemoPage demoPage = pageFactory.getPageObject(DemoPage.class);
		demoPage. searchTest2();
		demoPage.verifyTest2();
	}
}
