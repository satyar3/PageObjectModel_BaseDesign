package com.qa.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qa.drivermanager.DriverManager;
import com.qa.factory.PageFactory;
import com.qa.listeners.ExtentListeners;

public class ElementUtils extends PageFactory
{
	static Select sel;

	public ElementUtils(WebDriver driver)
	{
		super(driver);
	}

	public static void clickOrFail(By element, String failMessage)
	{
		try
		{
			ExtentListeners.insertScreenshotIntoReport();
			DriverManager.getDriver().findElement(element).click();
			LogUtils.reportPass("Clicked on : " + element.toString());
		} catch (Exception e)
		{
			//System.out.println("Unable to click on : "+element);
			LogUtils.log.get().error("Unable to click on : " + element);
			//LogUtils.reportFail(failMessage+" because of "+e.getClass());
			LogUtils.testFlowFail(failMessage + "<details>" + "<summary>" + "<b>" + "<font color=" + "red>"
					+ "Exception Occured: Click here to see the full error" + "</font>" + "</b >" + "</summary>"
					+ e.getMessage() + "</details>" + " \n");
		}
	}

	public static void sendKeyOrFail(By element, String failMessage, String... textToBeFilled)
	{
		try
		{
			DriverManager.getDriver().findElement(element).sendKeys(textToBeFilled);
			ExtentListeners.insertScreenshotIntoReport();
			LogUtils.reportPass("Filled in : " + element.toString());
		} catch (Exception e)
		{
			//System.out.println("Unable to fill in : "+element);
			LogUtils.log.get().error("Unable to fill in : " + element);
			LogUtils.testFlowFail(failMessage + "<details>" + "<summary>" + "<b>" + "<font color=" + "red>"
					+ "Exception Occured:Click to see" + "</font>" + "</b >" + "</summary>" + e.getMessage()
					+ "</details>" + " \n");
		}
	}

	public static void selectByVisibleTextOrFail(By element, String textToBeSelected, String failMessage)
	{
		try
		{
			WebElement el = DriverManager.getDriver().findElement(element);
			sel = new Select(el);
			sel.selectByVisibleText(textToBeSelected);
			ExtentListeners.insertScreenshotIntoReport();
			LogUtils.reportPass("Selected : " + element.toString());
		} catch (Exception e)
		{
			//System.out.println("Unable to select : "+element);
			LogUtils.log.get().error("Unable to select : " + element);
			LogUtils.testFlowFail(failMessage + "<details>" + "<summary>" + "<b>" + "<font color=" + "red>"
					+ "Exception Occured:Click to see" + "</font>" + "</b >" + "</summary>" + e.getMessage()
					+ "</details>" + " \n");
		}
	}

	public static WebElement getElement(By element)
	{
		WebElement el = null;
		try
		{
			el = DriverManager.getDriver().findElement(element);
		} catch (Exception e)
		{
			el = null;
		}

		return el;
	}

	public static List<WebElement> getElements(By element)
	{
		List<WebElement> el = null;
		try
		{
			el = DriverManager.getDriver().findElements(element);
		} catch (Exception e)
		{
			el = null;
		}

		return el;
	}
}
