package com.makemytrip.utilities;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.makemytrip.baseclass.BaseClass;

public class Utilities extends BaseClass 
{
	public static WebDriverWait wait ;
	public static Calendar cal;
	public static JavascriptExecutor js ;

	public static String xpathGeneratorForDate(int daysToAdd) 
	{

		cal= Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
		cal.add(Calendar.DAY_OF_MONTH, daysToAdd);
		String[] returnDate = formatter.format(cal.getTime()).split("/");
		String date = returnDate[0];
		String month = returnDate[1];
		String year = returnDate[2];
		String xPath = ".//*[contains(@aria-label,\""+month+" "+date+" "+year+"\")]";
		return xPath;

	}

	public static void clickOn(WebElement element) 
	{
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public static void enterText(WebElement element,String text) 
	{
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
	}

	public static void selectCityFromDynamicSearch(String cityname) 
	{
		List<WebElement> city = driver.findElements(By.xpath(".//li[@role=\"option\"]/descendant::div[@class=\"calc60\"]/p"));
		for(WebElement value : city) 
		{
			//System.out.println(value.getText());
			if((value.getAttribute("innerText")).contains(cityname)){
				value.click();
				break;
			}
		}
	}

	public static void waitFortobeclickable(WebElement element,String data ) 
	{
		while (!element.getAttribute("innerText").toLowerCase().contains(data.toLowerCase())) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static JavascriptExecutor javaScriptExecutor() 
	{
		 js = (JavascriptExecutor)driver;
		 return js;
	}
}
