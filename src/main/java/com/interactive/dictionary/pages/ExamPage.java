package com.interactive.dictionary.pages;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExamPage {

	private WebDriver driver;
	
	@FindBy(id = "src_word")
	private WebElement srcWord;
	
	@FindBy(id = "answer")
	private WebElement answer;
	
	@FindBy(id = "check-button")
	private WebElement checkButt;
	
	@FindBy(id = "next-button")
	private WebElement nextButt;
	
	@FindBy(id = "succ_or_fail")
	private WebElement succOrFailEl;
	
	public ExamPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public int enterTheWord(ArrayList<String> srcWords, ArrayList<String> targetWords) {
		
		int desiredIndex = -1;
		
		for (int i = 0; i < srcWords.size(); i++) {

			if (srcWords.get(i).equals(srcWord.getText())) {
				desiredIndex = i;
			}			
		}
		
		answer.sendKeys(targetWords.get(desiredIndex));
		
		return desiredIndex;
	}
	
	public void clickCheckButt() {
		checkButt.click();
	}
	
	public void clickNextButt() {
		nextButt.click();
	}
	
	public boolean checkSuccOrFailMessage(int index, ArrayList<String> answersList) {
		int answerKey = Integer.parseInt(answersList.get(index));
		if (answerKey == 0) {
			return succOrFailEl.getText().contains("WRONG");
		} else if (answerKey == 1) {
			return succOrFailEl.getText().contains("OK");
		}
		return false;
	}
	
}
