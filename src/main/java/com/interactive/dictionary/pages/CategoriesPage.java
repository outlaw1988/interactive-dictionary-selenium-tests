package com.interactive.dictionary.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CategoriesPage {

	private WebDriver driver;
	
	private final String confirmElXpath = "//*[contains(text(),'Categories available')]";
	@FindBy(xpath = confirmElXpath)
	private WebElement confirmEl;
	
	public CategoriesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean checkConfirmEl() {
		return driver.findElements(By.xpath(confirmElXpath)).size() > 0;
	}
	
}
