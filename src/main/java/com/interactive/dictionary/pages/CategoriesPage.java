package com.interactive.dictionary.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.interactive.dictionary.utils.Utils;

public class CategoriesPage {

	private WebDriver driver;
	
	private final String confirmElXpath = "//*[contains(text(),'Categories available')]";
	@FindBy(xpath = confirmElXpath)
	private WebElement confirmEl;
	
	@FindBy(id = "add-category")
	private WebElement addCategoryEl;
	
	public CategoriesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean checkConfirmEl() {
		return driver.findElements(By.xpath(confirmElXpath)).size() > 0;
	}
	
	public void clickAddCategory() {
		addCategoryEl.click();
	}
	
	public boolean checkCategoryExist(String categoryName) {
		String xpath = "//span[contains(text(),'" + categoryName + "')]";
		return driver.findElements(By.xpath(xpath)).size() > 0;
	}
	
	public void clickBox(String categoryName) {
		String xpath = "//span[contains(text(),'" + categoryName + "')]";
		driver.findElement(By.xpath(xpath)).click();
	}
	
}
