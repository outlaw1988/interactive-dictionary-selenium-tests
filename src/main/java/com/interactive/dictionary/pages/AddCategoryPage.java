package com.interactive.dictionary.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCategoryPage {

	private WebDriver driver;
	
	@FindBy(id = "name")
	private WebElement categoryNameEl;
	
	@FindBy(id = "add-category")
	private WebElement addCategoryButt;
	
	String defaultSrcLanguageId = "defaultSrcLanguage";
	
	String defaultTargetLanguageId = "defaultTargetLanguage";
	
	String defaultTargetSideId = "defaultTargetSide";
	
	String defaultCountdownDurationId = "defaultCountdownDuration";
	
	public AddCategoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setCategoryName(String categoryName) {
		categoryNameEl.sendKeys(categoryName);
	}
	
	public void chooseDefaultLanguage(String element, String mode) {
		
		Select dropdown = null;
		
		if (mode.equals("srcLanguage")) {
			dropdown = new Select(driver.findElement(By.id(defaultSrcLanguageId)));
		} else if (mode.equals("targetLanguage")) {
			dropdown = new Select(driver.findElement(By.id(defaultTargetLanguageId)));
		}
		
		dropdown.selectByVisibleText(element);
	}
	
	public void chooseDefaultTargetSide(String element) {
		Select dropdown = new Select(driver.findElement(By.id(defaultTargetSideId)));
		dropdown.selectByVisibleText(element);
	}
	
	public void chooseDefaultCountdownDuration(String element) {
		Select dropdown = new Select(driver.findElement(By.id(defaultCountdownDurationId)));
		dropdown.selectByVisibleText(element);
	}
	
	public void clickAddCategory() {
		addCategoryButt.click();
	}
	
}
