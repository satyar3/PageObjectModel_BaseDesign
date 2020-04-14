package com.qa.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.driverfactory.DriverFactory;

public class WaitUtils
{

	public static WebDriver driver = DriverFactory.getDriver();

	public static boolean WaitForElementPresent(By elt, int timeOutInSeconds)
	{
		WebElement el;
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			el = wait.until(ExpectedConditions.presenceOfElementLocated(elt));
		} catch (Exception e)
		{
			el = null;
		}

		if (el != null)
			return true;
		else
			return false;
	}

	public static boolean WaitForElementVisible(By elt, int timeOutInSeconds)
	{
		WebElement el;
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			el = wait.until(ExpectedConditions.visibilityOfElementLocated(elt));
		} catch (Exception e)
		{
			el = null;
		}

		if (el != null)
			return true;
		else
			return false;
	}

	public static boolean WaitForElementVisible(WebElement elt, int timeOutInSeconds)
	{
		WebElement el;
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			el = wait.until(ExpectedConditions.visibilityOf(elt));
		} catch (Exception e)
		{
			el = null;
		}

		if (el != null)
			return true;
		else
			return false;
	}
}
