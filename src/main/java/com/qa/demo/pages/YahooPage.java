package com.qa.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.utils.ElementUtils;
import com.qa.utils.LogUtils;

public class YahooPage extends DemoBasePage
{
	public YahooPage(WebDriver driver)
	{
		super(driver);
	}

	private static By googleSearchBox = By.name("q1");
	private static By googleSearch1stResult = By.cssSelector(".erkvQe li:first-child span");
	
	private static By yahooSearchBox = By.cssSelector("#header-search-form #header-search-input");
	private static By yahooSearch1stResult = By.cssSelector("[role='listbox'] li:first-child");
	
	private static By FBPhoneTextBox = By.cssSelector("[data-testid='royal_email']");
	private static By FBLoginButton = By.cssSelector("#loginbutton2");

	public void googleTest()
	{
		ElementUtils.sendKeyOrFail(googleSearchBox, "Unable to fill in Google Search Box in 1", "Software Testing 1");
		ElementUtils.clickOrFail(googleSearch1stResult, "Failed to click on google search result");
	}

	public void faceBookTest()
	{
		ElementUtils.sendKeyOrFail(FBPhoneTextBox, "Unable to fill in FB Name Box in 2", "9632587412");
		ElementUtils.clickOrFail(FBLoginButton, "Failed to click on sign in button of FB Form");
	}
	
	public void yahooTest()
	{
		ElementUtils.sendKeyOrFail(yahooSearchBox, "Unable to fill in Yahoo Search Box in 3", "Software Testing");
		ElementUtils.clickOrFail(yahooSearch1stResult, "Failed to click on yahoo search result");
	}

	public void verifyTest1()
	{
		LogUtils.reportFail("Assertion Failed in VerifyTest 1");
		//LogUtils.reportPass("Assertion Passed in VerifyTest 1");
	}

	public void verifyTest2()
	{
		LogUtils.reportPass("Assertion Passed in VerifyTest 2");
		//LogUtils.reportFail("Assertion Failed in VerifyTest 2");
	}

	public void verifyTest3()
	{
		LogUtils.reportPass("Assertion Passed in VerifyTest 3");
		//LogUtils.reportFail("Assertion Failed in VerifyTest 3");
		LogUtils.reportPass("Assertion Passed in VerifyTest 3");
	}
}
