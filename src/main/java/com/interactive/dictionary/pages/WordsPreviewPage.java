package com.interactive.dictionary.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WordsPreviewPage {

	private WebDriver driver;
	
	@FindBy(id = "perform-exam")
	private WebElement performExamEl;
	
	@FindBy(id = "go-back")
	private WebElement goBackEl;
	
	private String tableXpath = "//table[@class='table table-striped table-hover']";
	
	public WordsPreviewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String checkLabels(int index) {
		String xpath = tableXpath + "/thead/tr[2]/th[" + index + "]";
		return driver.findElement(By.xpath(xpath)).getText();
	}
	
	public boolean checkElementsInTableMatch(ArrayList<String> words, int side) {
		int offset = 1;
		for (int i = offset; i < (words.size() + offset); i++) {
			String xpath = tableXpath + "/tbody/tr[" + i + "]/td[" + side + "]";
			String word = driver.findElement(By.xpath(xpath)).getText();
			
			if (!word.equals(words.get(i - offset))) {
				return false;
			}
		}
		
		return true;
	}
	
	public void clickPerformExam() {
		performExamEl.click();
	}
	
	public void clickGoBack() {
		goBackEl.click();
	}
	
}
