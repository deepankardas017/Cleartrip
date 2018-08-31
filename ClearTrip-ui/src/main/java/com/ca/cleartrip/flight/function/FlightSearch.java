package com.ca.cleartrip.flight.function;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ca.cleartrip.utils.BrowserUtiles;

public class FlightSearch {

	WebDriver driver;
	BrowserUtiles browserutil;
	Properties locator;

	public FlightSearch(){}
	public FlightSearch(WebDriver driver, BrowserUtiles browserutil, Properties locator){
		this.driver = driver;
		this.browserutil = browserutil;
		this.locator = locator;
	}

	public void selectFlightDate(String month, String day){
		while(true){
			String getmonth = browserutil.getText(locator.getProperty("month"));//driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[1]/div/div/span[1]")).getText();
			if(month.equals(getmonth)){
				driver.findElement(By.xpath("//div[div[div[span[text()='"+month+"']]]]/descendant::a[text()='"+day+"']")).click();
				break;
			}	
			else{
				browserutil.click(locator.getProperty("next"));
			}
		}
	}

}
