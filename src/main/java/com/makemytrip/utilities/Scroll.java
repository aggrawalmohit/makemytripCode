package com.makemytrip.utilities;


import org.openqa.selenium.WebElement;

import com.makemytrip.baseclass.BaseClass;



public class Scroll extends BaseClass
{

	public static void Scrolldown()   
	{

	
		long height =Long.parseLong(Utilities.javaScriptExecutor().executeScript("return document.body.scrollHeight").toString()); 
		while(true) 
		{
			Utilities.javaScriptExecutor().executeScript("window.scrollTo(0,document.body.scrollHeight);");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			long newHeight = Long.parseLong(Utilities.javaScriptExecutor().executeScript("return document.body.scrollHeight").toString()); 
			if(newHeight==height) {
				break;
			}
			height=newHeight;
		}
	} 

	

	public static void ScrollUp() 
	{
		
		Utilities.javaScriptExecutor().executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}
	
	//s.executeScript("arguments[0].scrollIntoView();", Element);
	
	public static void ScrollToElement(WebElement element) 
	{
	 Utilities.javaScriptExecutor().executeScript("argument[0].scrollIntoView();",element);
	}
}

