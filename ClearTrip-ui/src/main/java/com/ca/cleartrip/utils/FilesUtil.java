package com.ca.cleartrip.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FilesUtil {

	FileInputStream inputStream;
	Properties properties;
	String projectPath = System.getProperty("user.dir");
	String locatorPath = File.separator+"src"+File.separator+"main"+File.separator+
			"resources"+File.separator+"objectRepository"+File.separator;
	String configPath = File.separator+"src"+File.separator+"main"+File.separator+
			"resources"+File.separator;
	public Properties loadFile(String fileName)
	{
		try {
			if(fileName.contains("config")) {
				inputStream = new FileInputStream(projectPath+configPath+fileName+".properties");
			}
			else {
				inputStream = new FileInputStream(projectPath+locatorPath+fileName+".properties");
			}
			properties = new Properties();
			properties.load(inputStream);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public String fileContentToString(String filePath)
	{
	    StringBuilder contentBuilder = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
	    {
	    
	        String sCurrentLine;
	        while ((sCurrentLine = br.readLine()) != null)
	        {
	            contentBuilder.append(sCurrentLine).append("\n");
	        }
	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	    return contentBuilder.toString();
	}

}
