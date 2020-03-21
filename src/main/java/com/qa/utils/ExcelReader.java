package com.qa.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader
{
	static Workbook book;
	static Sheet sheet;

	public static Object[][] geAllDataFromExcel(String sheetPath, String sheetName)
	{

		FileInputStream file = null;
		try
		{
			file = new FileInputStream(sheetPath);
		}
		catch (FileNotFoundException e)
		{
			LogUtils.log.error("Unable to find the Excel Sheet !!!");
		}
		try
		{
			book = WorkbookFactory.create(file);
		}
		catch (InvalidFormatException e)
		{
			LogUtils.log.error("Not a valid Excel Sheet !!!");
		}
		catch (IOException e)
		{
			LogUtils.log.error("Unable to open the Excel Sheet !!!");
		}
		
		sheet = book.getSheet(sheetName);

		//Treating rows are separate and all columns as one entity
		Object[][] data = new Object[sheet.getLastRowNum()][1];

		int temp = 0;
		for (int i = 0; i < sheet.getLastRowNum(); i++)
		{
			Hashtable<String, String> table = new Hashtable<String, String>();
			String cellValue;
			
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++)
			{
				try
				{
					cellValue = sheet.getRow(i + 1).getCell(k).toString();
				}
				catch (Exception n)
				{
					cellValue = "";
				}
				
				table.put(sheet.getRow(0).getCell(k).toString(), cellValue);
			}
			
			data[temp][0] = table;
			temp++;
		}
		
		return data;		
	}
	
	public static Hashtable<String, String> geSpecificDataFromExcel(String sheetPath, String sheetName, String methodName)
	{
		FileInputStream file = null;
		
		try
		{
			file = new FileInputStream(sheetPath);
		}
		catch (FileNotFoundException e)
		{
			LogUtils.log.error("Unable to find the Excel Sheet !!!");
		}
		try
		{
			book = WorkbookFactory.create(file);
		}
		catch (InvalidFormatException e)
		{
			LogUtils.log.error("Not a valid Excel Sheet !!!");
		}
		catch (IOException e)
		{
			LogUtils.log.error("Unable to open the Excel Sheet !!!");
		}
		
		sheet = book.getSheet(sheetName);
		Hashtable<String, String> table = new Hashtable<String, String>();

		for (int i = 0; i < sheet.getLastRowNum(); i++)
		{			
			String cellValue;
			
			if(sheet.getRow(i+1).getCell(0).toString().equalsIgnoreCase(methodName))
			{
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++)
				{
					try
					{
						cellValue = sheet.getRow(i + 1).getCell(k).toString();
					}
					catch (Exception n)
					{
						cellValue = "";
					}
					
					table.put(sheet.getRow(0).getCell(k).toString(), cellValue);
				}
				
				break;
			}
		}
		
		return table;		
	}
}
