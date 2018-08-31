package com.ca.cleartrip.utils;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	private static ExtentReports extent;

	private ExtentManager() {
	}

	public static ExtentReports getInstance() {
		if (extent == null) {
			extent = new ExtentReports(System.getProperty("user.dir") + "/report/CleartripTestReport.html", true);
			extent.loadConfig(new File(System.getProperty("user.dir") + "/src/main/resources/extent-config.xml"));
			extent.addSystemInfo("Selenium Version", "3.6").addSystemInfo("Java Version", "1.8").addSystemInfo("Environment", "QA");
		}
		return extent;
	}
}