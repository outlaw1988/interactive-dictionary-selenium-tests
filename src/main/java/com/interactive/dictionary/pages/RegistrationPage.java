package com.interactive.dictionary.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

	private WebDriver driver;
	
	private final String confirmElXpath = "//*[contains(text(),'Registration form')]";
	@FindBy(xpath = confirmElXpath)
	private WebElement confirmEl;
	
	@FindBy(id = "username")
	private WebElement usernameEl;
	
	@FindBy(id = "email")
	private WebElement emailEl;
	
	@FindBy(id = "password")
	private WebElement passwordEl;
	
	@FindBy(id = "passwordConfirm")
	private WebElement passwordConfirmEl;
	
	@FindBy(id = "apply")
	private WebElement applyEl;
	
	@FindBy(id = "success-message")
	private WebElement successMessage;
	
	@FindBy(id = "go-back")
	private WebElement goToLoginPageEl;
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean checkConfirmEl() {
		return driver.findElements(By.xpath(confirmElXpath)).size() > 0;
	}
	
	public void fillForm(String username, String email, String password) {
		usernameEl.sendKeys(username);
		emailEl.sendKeys(email);
		passwordEl.sendKeys(password);
		passwordConfirmEl.sendKeys(password);
	}
	
	public void clickApply() {
		applyEl.click();
	}
	
	public String registrationConfirmation() {
		return successMessage.getText();
	}
	
	public void clickGoToLoginPage() {
		goToLoginPageEl.click();
	}
	
}
