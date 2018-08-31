package com.ca.cleartrip.utils;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseTest {

	public WebDriver driver;
	public FilesUtil fileUtil;
	public Properties configProperty,locator;
	public BrowserUtiles browserUtil;
	public ExtentReports extent = ExtentManager.getInstance();
	public ExtentTest report;
	
	String projectPath = System.getProperty("user.dir");
	String driverPath  = File.separator+"src"+File.separator+"main"+File.separator+"resources"+
			File.separator+"drivers"+File.separator;
	
	public BaseTest(){
		fileUtil =  new FilesUtil();
		configProperty = fileUtil.loadFile("config");
	}
	
	@BeforeTest
	public void initTest() {
		initializeDriver(configProperty.getProperty("browserType"));
		driver.get(configProperty.getProperty("appURL"));
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void flushReport(){
		extent.endTest(report);
		extent.flush();
	}
	
	public WebDriver initializeDriver(String browserType) {

		switch(browserType.toLowerCase()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", projectPath+driverPath+"chromedriver.exe");
			this.driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", projectPath+driverPath+"geckodriver.exe");
			this.driver = new FirefoxDriver();
			break;
		}
		return this.driver;
	}
}
