package com.interactive.dictionary.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class WordsPreviewPage {

	private WebDriver driver;
	
	public WordsPreviewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
}
