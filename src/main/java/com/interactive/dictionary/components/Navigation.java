package com.interactive.dictionary.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Navigation {

	private WebDriver driver;
	
	@FindBy(id = "categories")
	private WebElement categoriesEl;
	
	@FindBy(id = "languages")
	private WebElement languagesEl;
	
	@FindBy(id = "free-sets")
	private WebElement freesetsEl;
	
	@FindBy(id = "user")
	private WebElement userEl;
	
	@FindBy(id = "logout")
	private WebElement logoutEl;
	
	public Navigation(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void getIntoLanguages() {
		languagesEl.click();
	}
	
}
