package com.qa.listeners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.driverfactory.DriverFactory;
import com.qa.factory.PageFactory;

public class ExtentManager extends PageFactory
{

	public ExtentManager(WebDriver driver)
	{
		super(driver);
	}

	private static ExtentReports extent;

	public static ExtentReports createInstance(String fileName)
	{
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);

		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(fileName);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Satyaranjan Muduli");
		extent.setSystemInfo("Organization", "NA");
		extent.setSystemInfo("Build no", "Build-1234");

		return extent;
	}

	public static String screenshotPath;
	public static String screenshotName;
	public static ThreadLocal<String> screenshotFullPath = new ThreadLocal<String>();

	public static void captureScreenshot()
	{

		File scrFile = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		screenshotFullPath.set(System.getProperty("user.dir") + "\\Screenshots\\" + ExtentListeners.className.get() + "\\"
				+ "\\" + ExtentListeners.testName.get() + "\\" + screenshotName);

		try
		{
			FileUtils.copyFile(scrFile, new File(screenshotFullPath.get()));
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}
}
