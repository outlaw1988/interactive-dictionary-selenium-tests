package com.interactive.dictionary.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LanguagesPage {

	private WebDriver driver;
	
	@FindBy(id = "add-language")
	private WebElement addLanguageButt;
	
	public LanguagesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickAddLanguage() {
		addLanguageButt.click();
	}
}
