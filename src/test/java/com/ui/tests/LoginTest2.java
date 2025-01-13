package com.ui.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.utility.BrowserUtility;

public class LoginTest2 {

	public static void main(String[] args) {
		WebDriver wd = new ChromeDriver(); // loose coupling: Interface referenceVariable = object of child class
		// above code launches a browser window... Browser session is created

		HomePage homePage= new HomePage(wd);
		
		
		LoginPage loginPage = homePage.goToLoginPage();
		loginPage.doLoginWith("vipowaf930@chansd.com", "Password");
		
		
	}

}
