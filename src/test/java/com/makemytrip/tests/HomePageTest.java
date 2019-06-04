package com.makemytrip.tests;


import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.makemytrip.baseclass.BaseClass;
import com.makemytrip.utilities.Scroll;


import commakemytrip.pages.FlightResultPage;
import commakemytrip.pages.HomePage;

public class HomePageTest extends BaseClass{
	
	private static FlightResultPage frp = null;
	@BeforeTest
	public void setup() 
	{
		initialise();
	}
	
	@Test(priority = 1)
	public void homepagetest() 
	{
		HomePage hm = new HomePage();
		hm.clickOnRoundTripButton();
		hm.clickOnFromButton();
		hm.enterFromCityName();
		
		hm.clickOnFromCityInList();
		hm.enterToCityName();
		hm.clickOnToCityInList();
		hm.selectDepartureDate();
		hm.selectReturnDate();
		driver.manage().deleteAllCookies();
		 frp= hm.clickOnSearchButton();
		
	}
	
	@Test(priority = 2)
	public void chkTotalNoOfFlights() {
		Scroll.Scrolldown();
		System.out.println(frp.departureFlightCount());
		System.out.println(frp.returnFlightCount());
		Scroll.ScrollUp();
		
	}
	@Test(priority = 3)
	public void nonStopFlightTest() {
		frp.clickOnNonStop();
		Scroll.Scrolldown();
		System.out.println("non stop flights -----"+frp.departureFlightCount());
		System.out.println("non stop flights -----"+frp.returnFlightCount());
		
	}
	
@Test(priority = 4)
public void oneStopFlightTest()
{
		Scroll.ScrollUp();
		frp.clickOnNonStop();
		frp.clickOnOneStop();
		Scroll.Scrolldown();
		System.out.println("one stop flights -----"+frp.departureFlightCount());
		System.out.println("one stop flights -----"+frp.returnFlightCount());
}
		
	@Test(priority = 5)
	public void randomFlightTest() {
		Scroll.ScrollUp();
		frp.selectRandomflight();
		
	}
		
	@Test(priority = 6)
	public void flightPriceTest() {
	frp.checkTicketPrice();
		
	}
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
