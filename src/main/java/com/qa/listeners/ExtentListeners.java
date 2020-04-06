package com.qa.listeners;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.SimpleLayout;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qa.utils.LogUtils;

public class ExtentListeners implements ITestListener, IInvokedMethodListener
{

	static Date d = new Date();
	static String fileName = "ExtentReport.html";
	//static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

	public static ThreadLocal<SoftAssert> softAssert = new ThreadLocal<SoftAssert>();

	public static boolean STATUS_PASS = true;
	public static boolean STATUS_FAIL = false;
	//public static boolean STATUS_ASSERTION_FAIL = false;
	public static ThreadLocal<Boolean> STATUS_ASSERTION_FAIL = new ThreadLocal<Boolean>();
	public static ThreadLocal<String> className = new ThreadLocal<String>();
	public static ThreadLocal<String> testName = new ThreadLocal<String>();
	public String testId = "";
	public Object testClass = new Object();

	File newReportFolder = new File(System.getProperty("user.dir") + "\\reports\\");
	boolean createdReportFolder = newReportFolder.mkdir();

	File newLogFolder = new File(System.getProperty("user.dir") + "\\logs\\");
	boolean createdLOgFolder = newLogFolder.mkdir();

	private static ExtentReports extent = ExtentManager
			.createInstance(System.getProperty("user.dir") + "\\reports\\" + fileName);
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result)
	{

		ExtentTest test = extent
				.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());
		testReport.set(test);
		LogUtils.log.get().info("Starting @Class : " + result.getTestClass().getName() + " @TestCase : "
				+ result.getMethod().getMethodName());
		LogUtils.log.get().info("***************************Test Started******************************");
	}

	public void onTestSuccess(ITestResult result)
	{
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);
		LogUtils.log.get().info("Success @Class : " + result.getTestClass().getName() + " @TestCase : "
				+ result.getMethod().getMethodName());
		LogUtils.log.get().info("***************************Test Passed******************************");
	}

	public void onTestFailure(ITestResult result)
	{
		if (!STATUS_ASSERTION_FAIL.get())
		{
			testReport.get().log(Status.FAIL, result.getThrowable().getClass().getSimpleName());
			//String excepionMessage = ExceptionUtil.getStackTrace(result.getThrowable());
			String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
			testReport.get()
					.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
							+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>")
							+ "</details>" + " \n");
			//testReport.get().log(Status.FAIL, result.getThrowable());
		}

		String failureLogg = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);
		LogUtils.log.get().error("Failed @Class : " + result.getTestClass().getName() + " @TestCase : "
				+ result.getMethod().getMethodName());
		LogUtils.log.get().error("***************************Test Failed******************************");
	}

	public void onTestSkipped(ITestResult result)
	{
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);
		LogUtils.log.get().info("Skipped @Class : " + result.getTestClass().getName() + " @TestCase : "
				+ result.getMethod().getMethodName());
		LogUtils.log.get().error("***************************Test Skipped******************************");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{

	}

	public void onStart(ITestContext context)
	{

	}

	public void onFinish(ITestContext context)
	{
		if (extent != null)
		{
			extent.flush();
		}
	}

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult)
	{
		//STATUS_ASSERTION_FAIL.set(false);
		if (LogUtils.log.get() == null)
		{
			try
			{
				//freemarker.log.Logger.selectLoggerLibrary(freemarker.log.Logger.LIBRARY_NONE);
				SimpleLayout layout = new SimpleLayout();
				//String PATTERN = "%d [%p|%c|%C{1}] %m%n";
				LogUtils.log.set(Logger
						.getLogger(Thread.currentThread().getClass().getSimpleName() + Thread.currentThread().getId()));
				FileAppender fileaAppender = new FileAppender(layout, "./logs/"
						+ Thread.currentThread().getClass().getSimpleName() + Thread.currentThread().getId() + ".log",
						false);
				fileaAppender.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
				fileaAppender.setAppend(false);
				fileaAppender.activateOptions();
				LogUtils.log.get().addAppender(fileaAppender);
				PropertyConfigurator.configure("src\\main\\resources\\log4j.properties");
				//BasicConfigurator.configure();
			} 
			catch (IOException e)
			{	
				e.printStackTrace();
			} 

		}
		if (method.getTestMethod().isTest())
		{
			softAssert.set(new SoftAssert());
			ExtentListeners.STATUS_ASSERTION_FAIL.set(false);
			//className.set(testResult.getTestClass().getName());
			className.set(testResult.getTestClass().getRealClass().getSimpleName());
			testName.set(method.getTestMethod().getMethodName());
			testClass = testResult.getInstance();
		}
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult)
	{
		if (method.getTestMethod().isTest())
		{
			if (STATUS_ASSERTION_FAIL.get())
			{
				testResult.setStatus(ITestResult.FAILURE);
				testResult.setThrowable(new Exception("Test failed because of incomplete action !!!"));
			}
			softAssert.get().assertAll();
		}
	}

	public static void assertFail(String message)
	{
		ExtentListeners.insertScreenshotIntoReport();
		softAssert.get().fail(message);
		ExtentListeners.STATUS_ASSERTION_FAIL.set(true);
	}

	public static void insertScreenshotIntoReport()
	{
		try
		{
			ExtentManager.captureScreenshot();
			testReport.get().info("<b>" + "<font color=" + "red>" + "Screenshot :" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotFullPath.get()).build());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}