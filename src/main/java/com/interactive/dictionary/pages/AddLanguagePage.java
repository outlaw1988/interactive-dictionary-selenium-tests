package com.interactive.dictionary.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddLanguagePage {

	private WebDriver driver;
	
	@FindBy(id = "name")
	private WebElement languageNameEl;
	
	@FindBy(id = "add-language")
	private WebElement addLanguageEl;
	
	public AddLanguagePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void fillLanguageName(String languageName) {
		languageNameEl.sendKeys(languageName);
	}
	
	public void clickAddLanguage() {
		addLanguageEl.click();
	}
}
