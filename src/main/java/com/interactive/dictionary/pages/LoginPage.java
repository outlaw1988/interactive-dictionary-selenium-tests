package com.interactive.dictionary.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.interactive.dictionary.utils.Utils;

public class LoginPage {

	private WebDriver driver;
	
	@FindBy(id = "username")
	private WebElement usernameEl;
	
	@FindBy(id = "password")
	private WebElement passwordEl;
	
	@FindBy(id = "sign-in")
	private WebElement signinEl;
	
	private final String signupId = "sign-up";
	@FindBy(id = signupId)
	private WebElement signupEl;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterPage(String url) {
		this.driver.get(url);
	}
	
	public void login(String username, String password) {
		usernameEl.sendKeys(username);
		passwordEl.sendKeys(password);
		
		signinEl.click();
	}
	
	public void clickSignUp() {
		Utils.waitUntilVisibleById(driver, signupId);
		signupEl.click();
	}
	
}
