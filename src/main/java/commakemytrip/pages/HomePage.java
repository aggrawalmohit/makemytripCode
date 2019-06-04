package commakemytrip.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.makemytrip.baseclass.BaseClass;
import com.makemytrip.utilities.Utilities;

public class HomePage extends BaseClass{
	
	@FindBy(xpath=".//*[@class=\"chNavText darkGreyText\" and contains(text(),\"Flights\")]")
	WebElement flightsLink;
	
	@FindBy(xpath= ".//*[ contains(text(),\"Round Trip\")]")
	WebElement roundTripButton;
	
	@FindBy(xpath= ".//input[@id=\"fromCity\"]")
	WebElement fromButton;
	
	@FindBy(xpath= ".//input[@placeholder=\"To\"]")
	WebElement toButton;
	
	@FindBy(xpath= ".//*[contains(text(),\"DEPARTURE\")]")
	WebElement departureDate;
	
	@FindBy(xpath= ".//*[contains(text(),\"RETURN\")]")
	WebElement returnDateLink;
	
	@FindBy(xpath= ".//*[@placeholder=\"From\"]")
	WebElement fromTextBox;
	
	@FindBy(xpath= ".//*[@placeholder=\"To\"]")
	WebElement toTextBox;

	@FindBy(xpath= ".//a[contains(text(),\"Search\")]")
	WebElement searchButton;
	
	public HomePage() 
	{
		PageFactory.initElements(driver, this);
	}
	
	public void selectDepartureDate() 
	{
		
		String xPath = Utilities.xpathGeneratorForDate(1);
		Utilities.clickOn(departureDate);
		WebElement departureDateElement = driver.findElement(By.xpath(xPath));
		Utilities.clickOn(departureDateElement);
		
		
	}
	
	public void selectReturnDate() 

	{
		
		String xPath= Utilities.xpathGeneratorForDate(Integer.parseInt(prop.getProperty("departureDaysToadd")));
		WebElement returnDateElement = driver.findElement(By.xpath(xPath));
		Utilities.clickOn(returnDateElement);

	}

	public void clickOnRoundTripButton() 
	{
		Utilities.clickOn(roundTripButton);
	}

	public void clickOnFromButton() 
	{
		fromButton.click();

	}
	
	public void clickOnToSelectButton() 
	{
		toButton.click();

	}

	public void enterFromCityName() 
	{
		
	Utilities.enterText(fromTextBox,prop.getProperty("fromCity"));
	
	}

	public void enterToCityName() 
	{
		Utilities.enterText(toTextBox, prop.getProperty("toCity"));
	}
	
	public void clickOnFromCityInList() 
	{
		Utilities.selectCityFromDynamicSearch(prop.getProperty("fromCity"));
	}
	
	public void clickOnToCityInList() 
	{
		Utilities.selectCityFromDynamicSearch(prop.getProperty("toCity"));
	}

	public FlightResultPage clickOnSearchButton()
	{
		Utilities.clickOn(searchButton);
		return new FlightResultPage();
	}
}
