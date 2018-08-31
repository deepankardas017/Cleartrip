package com.ca.cleartrip.utils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserUtiles {

	public static WebDriver driver;
	WebDriverWait wait;

	public BrowserUtiles() {};
	public BrowserUtiles(WebDriver driver){
		this.driver = driver;
	}

	public synchronized void click(String key) {
		System.out.println(key);
		getElement(key).click();
	}

	public synchronized String getText(String key) {
		String text = getElement(key).getText();
		return text;
	}

	public synchronized void setText(String key,String data) {
		getElement(key).sendKeys(data);
	}

	public synchronized static void takeScreenshot(String fileName){
		try {
			
			File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(image, new File(System.getProperty("user.dir")+"/report/screenShots/"+fileName+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized void date(String month, int day){
		String monthloc= "//span[text()='"+month+"']";
		String dayloc = "//div[div[div[span[text()='"+month+"']]]]/descendant::a[text()='"+day+"']";

		while(true){
			String getmonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[1]/div/div/span[1]")).getText();
			if(month.equals(getmonth)){

				driver.findElement(By.xpath("//div[div[div[span[text()='"+month+"']]]]/descendant::a[text()='"+day+"']")).click();
				break;
			}	
			else{
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[2]/div/a")).click();
			}
		}
	}

	public synchronized void waitElement(String key) {
		wait = new WebDriverWait(driver, 30);
		String locatorType = key.split(":", 2)[0];
		String locator = key.split(":", 2)[1];

		switch(locatorType) {
		case "XPATH":
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
			break;
		case "ID":
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
			break;
		case "NAME":
			wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locator)));
			break;
		case "LINKTEXT":
			wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator)));
			break;
		case "PARTIALLINKTEXT":
			wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locator)));
			break;
		case "TAGNAME":
			wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locator)));
			break;
		case "CSS":
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
			break;
		case "CLASS":
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className(locator)));
			break;
		default:
			System.out.println("Wrong locator, please correct the type of locator.");	
		}
	}

	public synchronized WebElement getElement(String locaterWithType) {

		WebElement webelement = null;
		String locaterType = locaterWithType.split(":", 2)[0];
		System.out.println(locaterType);
		String locater = locaterWithType.split(":", 2)[1];
		System.out.println(locater);
		switch(locaterType){
		case "XPATH":
			webelement = driver.findElement(By.xpath(locater));
			break;
		case "ID":
			webelement = driver.findElement(By.id(locater));
			break;
		case "NAME":
			webelement = driver.findElement(By.name(locater));
			break;
		case "LINKTEXT":
			webelement = driver.findElement(By.linkText(locater));
			break;
		case "PARTIALLINKTEXT":
			webelement = driver.findElement(By.partialLinkText(locater));
			break;
		case "TAGNAME":
			webelement = driver.findElement(By.tagName(locater));
			break;
		case "CSS":
			webelement = driver.findElement(By.cssSelector(locater));
			break;
		case "CLASS":
			webelement = driver.findElement(By.className(locater));
			break;
		default:
			System.out.println("Wrong locator, please correct the type of locator.");

		}

		return webelement;

	}

	public synchronized String getLocatort(String locaterWithType) {
		String locator = locaterWithType.split(":")[1];
		return locator;	
	}

}
