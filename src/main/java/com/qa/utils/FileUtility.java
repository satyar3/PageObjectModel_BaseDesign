package com.qa.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;

public class FileUtility
{

	public static void mergeLogFiles() throws IOException
    { 
        
		//Delete old master log file
		File LogFile = new File("master.log");
		
		if(LogFile.exists())
			LogFile.delete();
		
		// create instance of directory 
        File dir = new File(System.getProperty("user.dir") + "\\logs\\");         
        
        // create obejct of PrintWriter for output file 
        PrintWriter pw = new PrintWriter("master.log"); 
  
        // Get list of all the files in form of String Array 
        String[] fileNamesOld = dir.list(); 
        
        
        
        String[] fileNames = dir.list();
        // loop for reading the contents of all the files  
 
        for (String fileName : fileNames) { 
            //System.out.println("Reading from " + fileName); 
  
            // create instance of file from Name of  the file stored in string Array 
            File f = new File(dir, fileName); 
  
            // create object of BufferedReader 
            BufferedReader br = new BufferedReader(new FileReader(f)); 
            //pw.println("Contents of file " + fileName); 
  
            // Read from current file 
            String line = br.readLine(); 
            while (line != null) { 
  
                // write to the output file 
                pw.println(line); 
                line = br.readLine(); 
            } 
            pw.flush(); 
        } 
        //System.out.println("Reading from all files" +  " in directory " + dir.getName() + " Completed"); 
    }
	
	public static void cleanUpLogFolder() throws IOException
	{
		File logsDir = new File(System.getProperty("user.dir") + "\\logs\\");
		File screenshotsDir = new File(System.getProperty("user.dir") + "\\Screenshots\\");
		
		/*if(logsDir.listFiles().length != 0)
			FileUtils.cleanDirectory(logsDir);*/
		
		if(screenshotsDir.exists())
			FileUtils.deleteDirectory(screenshotsDir);
		
		if (logsDir.exists())
		{
			for (File file : logsDir.listFiles())
			{
				file.delete();
			}
		}
	}
}
