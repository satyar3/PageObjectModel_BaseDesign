package com.qa.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.utils.ElementUtils;
import com.qa.utils.LogUtils;

public class DemoPage extends DemoBasePage
{
	public DemoPage(WebDriver driver)
	{
		super(driver);
	}
	private static By googleSearchBox1 = By.name("q");
	private static By googleSearchBox2 = By.name("1q");
	private static By googleSearch1stResult = By.cssSelector(".erkvQe li:first-child span");
	
	public void searchTest1()
	{
		ElementUtils.sendKeyOrFail(googleSearchBox1, "Unable to fill in Google Search Box", "Software Testing");
		ElementUtils.clickOrFail(googleSearch1stResult, "Failed to click on google sarch box");		
		//System.out.println(ElementUtils.getElement(googleSearchBox2).getText()+"*********");
	}
	public void searchTest2()
	{
		ElementUtils.sendKeyOrFail(googleSearchBox2, "Unable to fill in Google Search Box", "Software Testing");
		ElementUtils.clickOrFail(googleSearch1stResult, "Failed to click on google sarch box");		
		//System.out.println(ElementUtils.getElement(googleSearchBox2).getText()+"*********");
	}
	public void verifyTest1()
	{
		LogUtils.reportFail("Assertion Failed in VerifyTest 1");
		/*LogUtils.reportFail("Assertion Failed in VerifyTest 2");
		LogUtils.reportFail("Assertion Failed in VerifyTest 3");*/

		LogUtils.reportPass("Assertion Passed in VerifyTest 1");
		/*LogUtils.reportPass("Assertion Passed in VerifyTest 2");
		LogUtils.reportPass("Assertion Passed in VerifyTest 3");*/
	}
	public void verifyTest2()
	{
		/*LogUtils.reportFail("Assertion Failed in VerifyTest 1");
		LogUtils.reportFail("Assertion Failed in VerifyTest 2");
		LogUtils.reportFail("Assertion Failed in VerifyTest 3");*/

		LogUtils.reportPass("Assertion Passed in VerifyTest 1");
		/*LogUtils.reportPass("Assertion Passed in VerifyTest 2");
		LogUtils.reportPass("Assertion Passed in VerifyTest 3");*/
	}
}
