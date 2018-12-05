package com.interactive.dictionary.tests;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.interactive.dictionary.base.BaseTest;
import com.interactive.dictionary.components.Navigation;
import com.interactive.dictionary.pages.AddFreeSetPage;
import com.interactive.dictionary.pages.ExamPage;
import com.interactive.dictionary.pages.ExamSummaryPage;
import com.interactive.dictionary.pages.FreeSetsPage;
import com.interactive.dictionary.pages.WordsPreviewPage;
import com.interactive.dictionary.utils.Utils;

public class MainFlow2 extends BaseTest {
	
	ArrayList<String> srcWords;
	ArrayList<String> targetWords;
	ArrayList<String> answersList;

	@Test(priority = 1)
	public void addFreeSet() {
		Navigation navigation = new Navigation(driver);
		navigation.getIntoFreeSets();
		
		FreeSetsPage freeSets = new FreeSetsPage(driver);
		freeSets.addFreeSet();
		
		AddFreeSetPage addFreeSet = new AddFreeSetPage(driver);
		addFreeSet.setSetName(Utils.getStringFromJson("MainFlow2", "setName", "testData"));
		addFreeSet.chooseSrcLanguage(Utils.getStringFromJson("MainFlow2", "srcLanguage", "testData"));
		addFreeSet.chooseTargetLanguage(Utils.getStringFromJson("MainFlow2", "targetLanguage", "testData"));
		addFreeSet.chooseCountdownDuration(Utils.getStringFromJson("MainFlow2", 
				"countdownDuration", "testData"));
		addFreeSet.setUpload(Utils.getStringFromJson("MainFlow2", "filePath", "testData"));
		addFreeSet.clickAddSet();
		
		Assert.assertTrue(freeSets.checkSetExist(Utils.getStringFromJson("MainFlow2", 
												"setName", "testData")));
		Assert.assertEquals(freeSets.getWordsNum(Utils.getStringFromJson("MainFlow2", "setName", "testData")), 
				Integer.parseInt(Utils.getStringFromJson("MainFlow2", "wordsNum", "expectedData")));
		freeSets.clickBox(Utils.getStringFromJson("MainFlow2", "setName", "testData"));
	}
	
	@Test(priority = 2)
	public void wordsPreview() {
		
		srcWords = Utils.getArrayFromJson("MainFlow2", "srcWords", "testData");
		targetWords = Utils.getArrayFromJson("MainFlow2", "targetWords", "testData");
		
		WordsPreviewPage previewPage = new WordsPreviewPage(driver);
		Assert.assertEquals(previewPage.checkLabels(1), Utils.getStringFromJson("MainFlow2", 
														"srcLanguage", "expectedData"));
		Assert.assertEquals(previewPage.checkLabels(2), Utils.getStringFromJson("MainFlow2", 
														"targetLanguage", "expectedData"));
		Assert.assertTrue(previewPage.checkElementsInTableMatch(srcWords, 1));
		Assert.assertTrue(previewPage.checkElementsInTableMatch(targetWords, 2));
		
		previewPage.clickPerformExam();
	}
	
	@Test(priority = 3)
	public void performExam() {
		ExamPage exam = new ExamPage(driver);
		
		ArrayList<String> targetWordsExam = new ArrayList<String>(targetWords);
		
		answersList = Utils.getArrayFromJson("MainFlow2", "answersList", "testData");
		targetWordsExam.set(2, Utils.getStringFromJson("MainFlow2", "fail1", "testData"));
		targetWordsExam.set(6, Utils.getStringFromJson("MainFlow2", "fail2", "testData"));
		targetWordsExam.set(9, Utils.getStringFromJson("MainFlow2", "fail3", "testData"));
		targetWordsExam.set(13, Utils.getStringFromJson("MainFlow2", "fail4", "testData"));
		
		for (int i = 0; i < srcWords.size(); i++) {
			int index = exam.enterTheWord(srcWords, targetWordsExam);
			exam.clickCheckButt();
			Assert.assertTrue(exam.checkSuccOrFailMessage(index, answersList));
			exam.clickNextButt();
		}
	}
	
	@Test(priority = 4)
	public void examSummary() {
		ExamSummaryPage examSummary = new ExamSummaryPage(driver);
		Assert.assertEquals(examSummary.getLastResult(), 
				Utils.getStringFromJson("MainFlow2", "lastResult", "expectedData"));
		
		Assert.assertEquals(examSummary.getBestResult(), 
				Utils.getStringFromJson("MainFlow2", "bestResult", "expectedData"));
		
		Assert.assertEquals(examSummary.checkLanguageLabel(1), Utils.getStringFromJson("MainFlow2", 
								"srcLanguage", "expectedData"));
		Assert.assertEquals(examSummary.checkLanguageLabel(2), Utils.getStringFromJson("MainFlow2", 
								"targetLanguage", "expectedData"));
		
		Assert.assertTrue(examSummary.checkTableContent(srcWords, targetWords, answersList));
		
		examSummary.clickGoBack();
	}
	
	@Test(priority = 5)
	public void checkSetResults() {
		FreeSetsPage freeSetsPage = new FreeSetsPage(driver);
		Assert.assertEquals(freeSetsPage.getLastResult(Utils.getStringFromJson("MainFlow2", 
				"setName", "testData")), Utils.getStringFromJson("MainFlow2", "lastResult", 
						"expectedData"));
		
		Assert.assertEquals(freeSetsPage.getBestResult(Utils.getStringFromJson("MainFlow2", 
				"setName", "testData")), Utils.getStringFromJson("MainFlow2", "bestResult", 
						"expectedData"));
	}
	
}
