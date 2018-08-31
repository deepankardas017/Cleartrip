package com.ca.cleartrip.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

import org.testng.annotations.DataProvider;

import com.jayway.jsonpath.JsonPath;

public class TestDataProvider {


	private static String testCaseDatapath;

	private static String testDataFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator

			+ "main" + File.separator + "resources" + File.separator + "testData" + File.separator;

	@DataProvider(name = "flightTestDataProvider")

	public synchronized static Object[][] testData(Method m) throws IOException {

		Object[][] testData = new Object[1][1];
		testData[0][0] = testDataReader(m.getName(), "flightTestData");
		return testData;

	}
	
	@DataProvider(name = "hotelTestDataProvider")

	public synchronized static Object[][] hoteltestData(Method m) throws IOException {

		Object[][] testData = new Object[1][1];
		testData[0][0] = testDataReader(m.getName(), "flightTestData");
		return testData;

	}

	private synchronized static LinkedHashMap<String, String> testDataReader(String testName, String testFile) throws IOException {
	
		String json = new FilesUtil().fileContentToString(testDataFilePath + testFile + ".json");
		LinkedHashMap<String, LinkedHashMap<String, String>> testCasesWithData = JsonPath.parse(json).read("$");
		if (testCasesWithData.keySet().contains(testName)) {
			testCaseDatapath = "$." + testName;
		}

		LinkedHashMap<String, String> testData = JsonPath.parse(json).read(testCaseDatapath);

		return testData;

	}

}
