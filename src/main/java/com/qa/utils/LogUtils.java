package com.qa.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.listeners.ExtentListeners;

public class LogUtils
{
	public static Logger log = LogManager.getLogger(LogUtils.class);
	
	public static void reportInfo(String message)
	{
		ExtentListeners.testReport.get().info(message);
		log.info(message);
	}
	
	public static void reportPass(String message)
	{
		ExtentListeners.testReport.get().pass(message);
		log.info(message);
	}	
	public static void reportFail(String message)
	{
		String failedFromMethod = Thread.currentThread().getStackTrace()[2].getMethodName();
		String failedFromClass = Thread.currentThread().getStackTrace()[2].getClassName();
		int failedAtLine = Thread.currentThread().getStackTrace()[2].getLineNumber();
		
		ExtentListeners.assertFail(message+" in method : "+failedFromMethod+" of Class : "+failedFromClass+" at Line : "+failedAtLine);
		ExtentListeners.testReport.get().fail(message+" in method : "+failedFromMethod+" of Class : "+failedFromClass+" at Line : "+failedAtLine);
		//ExtentListeners.STATUS_HARD_FAIL = true;
		log.error(message+" in method : "+failedFromMethod+" of Class : "+failedFromClass+" at Line : "+failedAtLine);
	}
	public static void assertionFail(String message)
	{
		ExtentListeners.testReport.get().fail(message);	
		ExtentListeners.assertFail(message);
	}
}
