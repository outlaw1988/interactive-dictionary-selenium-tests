package com.interactive.dictionary.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.interactive.dictionary.utils.ReadConfig;

public class LoginPage {

	private WebDriver driver;
	
	@FindBy(id = "username")
	private WebElement usernameEl;
	
	@FindBy(id = "password")
	private WebElement passwordEl;
	
	@FindBy(id = "sign-in")
	private WebElement signinEl;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		System.out.println("Constructor called...");
		PageFactory.initElements(driver, this);
		
	}
	
	public void enterPage() {
		this.driver.get(ReadConfig.getProperty("url"));
	}
	
	public void login() {
		usernameEl.sendKeys(ReadConfig.getProperty("username"));
		passwordEl.sendKeys(ReadConfig.getProperty("password"));
		
		signinEl.click();
	}
	
}
