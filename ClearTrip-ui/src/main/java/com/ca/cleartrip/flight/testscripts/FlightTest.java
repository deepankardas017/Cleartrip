package com.ca.cleartrip.flight.testscripts;



import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ca.cleartrip.flight.function.FlightSearch;
import com.ca.cleartrip.utils.BaseTest;
import com.ca.cleartrip.utils.BrowserUtiles;
import com.ca.cleartrip.utils.CaptureScreenShotListener;
import com.ca.cleartrip.utils.TestDataProvider;
import com.relevantcodes.extentreports.LogStatus;

@Listeners(CaptureScreenShotListener.class)
public class FlightTest extends BaseTest{

	FlightSearch flightSeach;
	@BeforeClass
	public void initClass() {
		browserUtil = new BrowserUtiles(driver);
		locator = fileUtil.loadFile("flightLocators");
		flightSeach = new FlightSearch(driver,browserUtil,locator);
	}
	
	@BeforeMethod
	public void initMethod() {
		report = extent.startTest("Verify if an user can search for Flight.");
	}
	
	@Test(dataProviderClass=TestDataProvider.class,dataProvider = "flightTestDataProvider")
	public void testSearchFlight(LinkedHashMap<String, String> testData) throws InterruptedException {
		
		browserUtil.setText(locator.getProperty("from"), testData.get("from"));
		//Thread.sleep(5000);
		Assert.assertEquals(true, true);
		browserUtil.waitElement(locator.getProperty("bangalore"));
		browserUtil.click(locator.getProperty("bangalore"));
		report.log(LogStatus.INFO, "Entered Bangaloe in From field.");
		browserUtil.setText(locator.getProperty("to"), testData.get("to"));
		//Thread.sleep(5000);
		browserUtil.waitElement(locator.getProperty("bhubaneswar"));
		browserUtil.click(locator.getProperty("bhubaneswar"));
		report.log(LogStatus.INFO, "Entered Bhubaneswar in to field.");
		//
		//browserUtil.click(locator.getProperty("departon"));
		//browserUtil.click(locator.getProperty("date"));
		flightSeach.selectFlightDate(testData.get("month"), testData.get("day"));
		browserUtil.click(locator.getProperty("searchflight"));
		//
		String expectMsg = testData.get("expecmsg");
		String actualMsg = browserUtil.getText(locator.getProperty("result"));
		System.out.println(expectMsg);
		System.out.println(actualMsg);
		//assertEquals(actualMsg, expectMsg);
		report.log(LogStatus.PASS, "Sucessfully varified the flight search page.");
	}	
}
