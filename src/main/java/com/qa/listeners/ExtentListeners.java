package com.qa.listeners;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

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
	static String fileName = "Extent.html";
	//static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
	
	public static SoftAssert softAssert;
			
	public static boolean STATUS_PASS = true;
	public static boolean STATUS_FAIL = false;
	public static boolean STATUS_ASSERTION_FAIL = false;
	public static String className = "";
	public static String testName = "";
	
	public Object testClass = new Object();
	
	File newFolder = new File(System.getProperty("user.dir") + "\\reports\\");
    boolean created =  newFolder.mkdir();

	private static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir") + "\\reports\\" + fileName);
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result)
	{
		ExtentTest test = extent.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());
		testReport.set(test);
		LogUtils.log.info("Starting @Class : "+result.getTestClass().getName()+" @TestCase : " + result.getMethod().getMethodName());
		LogUtils.log.info("***************************Test Started******************************");
	}

	public void onTestSuccess(ITestResult result)
	{
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);
		LogUtils.log.info("Success @Class : "+result.getTestClass().getName()+" @TestCase : " + result.getMethod().getMethodName());
		LogUtils.log.info("***************************Test Passed******************************");
	}

	public void onTestFailure(ITestResult result)
	{
		if(!STATUS_ASSERTION_FAIL)
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
		/*try
		{
			ExtentManager.captureScreenshot();
			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>", MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName).build());
		}
		catch (IOException e)
		{

		}*/

		String failureLogg = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);
		LogUtils.log.error("Failed @Class : "+result.getTestClass().getName()+" @TestCase : " + result.getMethod().getMethodName());
		LogUtils.log.error("***************************Test Failed******************************");
	}

	public void onTestSkipped(ITestResult result)
	{
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);
		LogUtils.log.info("Skipped @Class : "+result.getTestClass().getName()+" @TestCase : " + result.getMethod().getMethodName());
		LogUtils.log.error("***************************Test Skipped******************************");
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
		if(method.getTestMethod().isTest())
		{
			softAssert = new SoftAssert();
			ExtentListeners.STATUS_ASSERTION_FAIL = false;
			className =	testResult.getTestClass().getName();
			testName = 	method.getTestMethod().getMethodName();
			testClass = testResult.getInstance();
		}
		
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult)
	{
		if(method.getTestMethod().isTest())
		{
			if(STATUS_ASSERTION_FAIL)
			{
				testResult.setStatus(ITestResult.FAILURE);
				testResult.setThrowable(new Exception("Test failed because of incomplete action !!!"));
			}
			softAssert.assertAll();
		}
		/*System.out.println(testResult.getMethod());
		try {
			softAssert.assertAll();
		} catch (AssertionError e) {
			testResult.setThrowable(e);
			testResult.setStatus(ITestResult.FAILURE);
		}*/
	}
	
	public static void assertFail(String message)
	{
		ExtentListeners.insertScreenshotIntoReport();
		softAssert.fail(message);
		ExtentListeners.STATUS_ASSERTION_FAIL = true;
	}
	
	public static void insertScreenshotIntoReport()
	{		
		try
		{
			ExtentManager.captureScreenshot();
			testReport.get().info("<b>" + "<font color=" + "red>" + "Screenshot :" + "</font>" + "</b>", MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotFullPath).build());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}