package commakemytrip.pages;

import java.util.List;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.makemytrip.baseclass.BaseClass;
import com.makemytrip.utilities.Utilities;

public class FlightResultPage extends BaseClass{

	public static JavascriptExecutor js = (JavascriptExecutor)driver; 
	
	@FindBy(xpath=".//label[@for=\"filter_stop0\"]/span[@class=\"box\"]")
	WebElement nonStopChkBox;
	
	@FindBy(xpath=".//label[@for=\"filter_stop1\"]/span[@class=\"box\"]")
	WebElement oneStopChkBox;
	
	@FindBy(xpath=".//div[@class=\"splitVw-footer-left \"]/descendant::p[@class=\"actual-price\"]")
	WebElement departurePriceAtBottom;
	
	@FindBy(xpath=".//div[@class=\"splitVw-footer-right \"]/descendant::p[@class=\"actual-price\"]")
	WebElement returnPriceAtBottom;
	
	@FindBy(xpath=".//span[@class=\"splitVw-total-fare\"]/span")
	WebElement totalPrice;
	
	
	
	public FlightResultPage() 
	{
		PageFactory.initElements(driver, this);
	}
	
	public int departureFlightCount() 
	{
	List<WebElement> list = driver.findElements(By.xpath(".//div[@id=\"ow_domrt-jrny\"]/descendant::div[@class=\"fli-list splitVw-listing\"]"));
	 return list.size();
	}
	
	public int returnFlightCount() 
	{
	List<WebElement> list = driver.findElements(By.xpath(".//div[@id=\"rt-domrt-jrny\"]/descendant::div[@class=\"fli-list splitVw-listing\"]"));
	 return list.size();
	}
	
	public void clickOnNonStop() 
	{
		Utilities.clickOn(nonStopChkBox);
	}

	public void clickOnOneStop() 
	{
		Utilities.clickOn(oneStopChkBox);
	}

	public void selectRandomflight() 
	{
		int departureFlightNo = Integer.parseInt(prop.getProperty("departureFlightNo"));
		int returnFlightNo = Integer.parseInt(prop.getProperty("returnFlightNo"));
		WebElement returnFlightelement= driver.findElement(By.xpath("(.//div[@id=\"rt-domrt-jrny\"]/descendant::div[@class=\"fli-list splitVw-listing\"])["+returnFlightNo+"]"));
		WebElement departureFlightElement = driver.findElement(By.xpath("(.//div[@id=\"ow_domrt-jrny\"]/descendant::div[@class=\"fli-list splitVw-listing\"])["+departureFlightNo+"]"));
		if(departureFlightNo>0 && departureFlightNo<=10) 
		{
			if(returnFlightNo>0 && returnFlightNo<=10) 
			{
				Utilities.javaScriptExecutor().executeScript("arguments[0].click();", departureFlightElement);
				Utilities.javaScriptExecutor().executeScript("arguments[0].click();", returnFlightelement);
			}
		}


		else {
			System.out.println("please enter flight count within 1-10");
		}

	}
    
	public void checkTicketPrice() 
	{
		int departureFlightNo = Integer.parseInt(prop.getProperty("departureFlightNo"));
		int returnFlightNo = Integer.parseInt(prop.getProperty("returnFlightNo"));
		
		long  returnFlightPrice=Long.parseLong(driver.findElement(By.xpath("(.//div[@id=\"rt-domrt-jrny\"]"
		+ "/descendant::div[@class=\"fli-list splitVw-listing\"])["+returnFlightNo+"]/label/descendant::p[@class=\"actual-price\"]/span")).getText().replaceAll("[^\\d]", ""));
		
		long departureFlightPrice = Long.parseLong(driver.findElement(By.xpath("(.//div[@id=\"ow_domrt-jrny\"]/descendant::div[@class=\"fli-list splitVw-listing\"])["+departureFlightNo+"]/descendant::p[@class=\"actual-price\"]")).getText().replaceAll("[^\\d]", ""));
		
		Assert.assertEquals(returnFlightPrice, Long.parseLong((returnPriceAtBottom.getText().replaceAll("[^\\d]", ""))));
		Assert.assertEquals(departureFlightPrice, Long.parseLong(departurePriceAtBottom.getText().replaceAll("[^\\d]", "")));
		
		Assert.assertEquals(returnFlightPrice+departureFlightPrice, Long.parseLong(totalPrice.getText().replaceAll("[^\\d]", "")));
		
	}
}
