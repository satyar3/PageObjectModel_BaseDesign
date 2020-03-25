package com.qa.factory;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public abstract class TestFactory
{
	protected WebDriver driver;
	protected Properties prop;
	protected FileInputStream fs;
	protected PageFactory pageFactory;

	protected abstract String getPropertyFilePath();

	protected abstract void setDriverPath();

	public String getProperty(String propKey)
	{
		return prop.getProperty(propKey);
	}
}
