package com.interactive.dictionary.tests;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.interactive.dictionary.base.BaseTest;
import com.interactive.dictionary.components.Navigation;
import com.interactive.dictionary.pages.AddCategoryPage;
import com.interactive.dictionary.pages.AddSetPage;
import com.interactive.dictionary.pages.CategoriesPage;
import com.interactive.dictionary.pages.ExamPage;
import com.interactive.dictionary.pages.ExamSummaryPage;
import com.interactive.dictionary.pages.SetPage;
import com.interactive.dictionary.pages.WordsPreviewPage;
import com.interactive.dictionary.utils.Utils;

public class MainFlow1 extends BaseTest {
	
	private ArrayList<String> srcWords;
	private ArrayList<String> targetWords;
	private ArrayList<String> answersList;

	@Test(priority = 1)
	public void addCategory() {
		test.info("Run navigation");
		
		Navigation navigation = new Navigation(driver);
		navigation.getIntoCategories();
		
		CategoriesPage categoriesPage = new CategoriesPage(driver);
		categoriesPage.clickAddCategory();
		
		test.info("Add category page");
		
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);
		addCategoryPage.setCategoryName(Utils.getStringFromJson("MainFlow1", "categoryName", "testData"));
		addCategoryPage.chooseDefaultLanguage(Utils.getStringFromJson("MainFlow1", 
											"language1", "testData"), "srcLanguage");
		addCategoryPage.chooseDefaultLanguage(Utils.getStringFromJson("MainFlow1", 
											"language2", "testData"), "targetLanguage");
		addCategoryPage.chooseDefaultTargetSide(Utils.getStringFromJson("MainFlow1", 
											"defaultTargetSide", "testData"));
		addCategoryPage.chooseDefaultCountdownDuration(Utils.getStringFromJson("MainFlow1", 
											"defaultCountdownDuration", "testData"));
		addCategoryPage.clickAddCategory();
		
		Assert.assertTrue(categoriesPage.checkSetExist(Utils.getStringFromJson("MainFlow1", 
											"categoryName", "expectedData")));
		
		categoriesPage.clickBox(Utils.getStringFromJson("MainFlow1", "categoryName", "testData"));
	}
	
	@Test(priority = 3)
	public void addSet() {
		SetPage setPage = new SetPage(driver);
		setPage.clickAddSet();
		
		AddSetPage addSetPage = new AddSetPage(driver);
		addSetPage.setSetName(Utils.getStringFromJson("MainFlow1", "setName", "testData"));
		
		srcWords = Utils.getArrayFromJson("MainFlow1", "srcWords", "testData");
		targetWords = Utils.getArrayFromJson("MainFlow1", "targetWords", "testData");
		
		addSetPage.putWordsIntoTable(srcWords, "left");
		addSetPage.putWordsIntoTable(targetWords, "right");
		addSetPage.clickAddSet();
		
		Assert.assertTrue(setPage.checkSetExist(Utils.getStringFromJson("MainFlow1", 
												"setName", "expectedData")));
		Assert.assertEquals(setPage.getWordsNum(), srcWords.size());
		
		setPage.clickBox(Utils.getStringFromJson("MainFlow1", "setName", "testData"));
	}
	
	@Test(priority = 4)
	public void wordsPreview() {
		WordsPreviewPage previewPage = new WordsPreviewPage(driver);
		Assert.assertEquals(previewPage.checkLabels(1), Utils.getStringFromJson("MainFlow1", 
														"language1", "expectedData"));
		Assert.assertEquals(previewPage.checkLabels(2), Utils.getStringFromJson("MainFlow1", 
														"language2", "expectedData"));
		Assert.assertTrue(previewPage.checkElementsInTableMatch(srcWords, 1));
		Assert.assertTrue(previewPage.checkElementsInTableMatch(targetWords, 2));
		
		previewPage.clickPerformExam();
	}
	
	@Test(priority = 5)
	public void performingExam() {
		ExamPage exam = new ExamPage(driver);
		
		ArrayList<String> targetWordsExam = new ArrayList<String>(targetWords);
		
		answersList = Utils.getArrayFromJson("MainFlow1", "answersList", "testData");
		targetWordsExam.set(3, Utils.getStringFromJson("MainFlow1", "fail1", "testData"));
		targetWordsExam.set(7, Utils.getStringFromJson("MainFlow1", "fail2", "testData"));
		
		for (int i = 0; i < srcWords.size(); i++) {
			int index = exam.enterTheWord(srcWords, targetWordsExam);
			exam.clickCheckButt();
			Assert.assertTrue(exam.checkSuccOrFailMessage(index, answersList));
			exam.clickNextButt();
		}
	}
	
	@Test(priority = 6)
	public void examSummary() {
		ExamSummaryPage examSummary = new ExamSummaryPage(driver, browser);
		Assert.assertEquals(examSummary.getLastResult(), 
				Utils.getStringFromJson("MainFlow1", "lastResult", "expectedData"));
		
		Assert.assertEquals(examSummary.getBestResult(), 
				Utils.getStringFromJson("MainFlow1", "bestResult", "expectedData"));
		
		Assert.assertEquals(examSummary.checkLanguageLabel(1), Utils.getStringFromJson("MainFlow1", 
								"language1", "expectedData"));
		Assert.assertEquals(examSummary.checkLanguageLabel(2), Utils.getStringFromJson("MainFlow1", 
								"language2", "expectedData"));
		
		Assert.assertTrue(examSummary.checkTableContent(srcWords, targetWords, answersList));
		
		examSummary.clickGoBack();
	}
	
	@Test(priority = 7)
	public void checkSetResults() {
		SetPage setPage = new SetPage(driver);
		Assert.assertEquals(setPage.getLastResult(Utils.getStringFromJson("MainFlow1", 
				"setName", "testData")), Utils.getStringFromJson("MainFlow1", "lastResult", 
						"expectedData"));
		
		Assert.assertEquals(setPage.getBestResult(Utils.getStringFromJson("MainFlow1", 
				"setName", "testData")), Utils.getStringFromJson("MainFlow1", "bestResult", 
						"expectedData"));
	}
	
}
