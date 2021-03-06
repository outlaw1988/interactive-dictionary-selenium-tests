package com.interactive.dictionary.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.interactive.dictionary.utils.Utils;

public class ExamSummaryPage {

	private WebDriver driver;
	private String browser;
	
	private final String lastResultId = "last-result";
	@FindBy(id = lastResultId)
	private WebElement lastResultEl;
	
	private final String bestResultId = "best-result";
	@FindBy(id = bestResultId)
	private WebElement bestResultEl;
	
	@FindBy(id = "go-back")
	private WebElement goBackEl;
	
	private final String tableXpath = "//table[@class='table table-striped table-hover']";
	
	public ExamSummaryPage(WebDriver driver, String browser) {
		this.driver = driver;
		this.browser = browser;
		PageFactory.initElements(driver, this);
	}
	
	public String getLastResult() {
		Utils.waitUntilVisibleById(driver, lastResultId);
		return lastResultEl.getText();
	}
	
	public String getBestResult() {
		Utils.waitUntilVisibleById(driver, bestResultId);
		return bestResultEl.getText();
	}
	
	public String checkLanguageLabel(int index) {
		String xpath = tableXpath + "/thead/tr[2]/th[" + index + "]";
		return driver.findElement(By.xpath(xpath)).getText();
	}
	
	public void clickGoBack() {
		goBackEl.click();
	}
	
	public boolean checkTableContent(ArrayList<String> srcWords, ArrayList<String> targetWords, 
									ArrayList<String> answersList) {
		int offset = 1;
		
		int sizeOff = srcWords.size() + offset;
		
		for (int i = offset; i < sizeOff; i++) {
			
			String leftFieldXpath = tableXpath + "/tbody/tr[" + i + "]/td[1]";
			WebElement leftField = driver.findElement(By.xpath(leftFieldXpath));
			
			if (!leftField.getText().equals(srcWords.get(i - offset))) {
				return false;
			}
			
			String rightFieldXpath = tableXpath + "/tbody/tr[" + i + "]/td[2]";
			WebElement rightField = driver.findElement(By.xpath(rightFieldXpath));
			
			if (!rightField.getText().equals(targetWords.get(i - offset))) {
				return false;
			}
			
			int answer = Integer.parseInt(answersList.get(i - offset));
			
//			System.out.println("Matching passed");
//			System.out.println("Color left field: " + leftField.getCssValue("color"));
//			System.out.println("Color right field: " + rightField.getCssValue("color"));
			
			if (answer == 0) {
				
				if (browser.equals("firefox")) {
					// RED
					//System.out.println("Browser: firefox");
					if (!leftField.getCssValue("color").equals("rgb(255, 0, 0)")) {
						System.out.println("Color != rgb(255, 0, 0)");
						return false;
					}
					
					if (!rightField.getCssValue("color").equals("rgb(255, 0, 0)")) {
						return false;
					}
					
				} else if (browser.equals("chrome") || browser.equals("opera")) {
					
					if (!leftField.getCssValue("color").equals("rgba(255, 0, 0, 1)")) {
						return false;
					}
					
					if (!rightField.getCssValue("color").equals("rgba(255, 0, 0, 1)")) {
						return false;
					}
					
				}
				
			} else if (answer == 1) {
				
				if (browser.equals("firefox")) {
					// GREEN
					if (!leftField.getCssValue("color").equals("rgb(0, 128, 0)")) {
						return false;
					}
					
					if (!rightField.getCssValue("color").equals("rgb(0, 128, 0)")) {
						return false;
					}
					
				} else if (browser.equals("chrome") || browser.equals("opera")) {
					
					if (!leftField.getCssValue("color").equals("rgba(0, 128, 0, 1)")) {
						return false;
					}
					
					if (!rightField.getCssValue("color").equals("rgba(0, 128, 0, 1)")) {
						return false;
					}
					
				}
				
				
			}
			
//			System.out.println("Colors passed");
			
		}
		
		return true;
	}
	
}
