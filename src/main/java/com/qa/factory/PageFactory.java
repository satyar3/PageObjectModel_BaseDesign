package com.qa.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.qa.demo.pages.DemoBasePage;
import com.qa.utils.LogUtils;

public class PageFactory
{
	protected WebDriver driver;
	protected static TestFactory seleniumTest = null;
	
	public PageFactory(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public <T extends DemoBasePage> T getPageObject(Class<T> cls)
	{
		try
		{
			T page = null;
			AjaxElementLocatorFactory ajaxElemLocatorFactory = new AjaxElementLocatorFactory(driver, 10);
			page = org.openqa.selenium.support.PageFactory.initElements(driver, cls);
			org.openqa.selenium.support.PageFactory.initElements(ajaxElemLocatorFactory, page);
			return cls.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
		} 
		catch (Exception e)
		{
			//System.out.println("Unable to instansiate : "+cls+" class");
			LogUtils.log.error("Unable to instansiate : "+cls+" class");
			e.printStackTrace();
			return null;
		}		
	}
}
