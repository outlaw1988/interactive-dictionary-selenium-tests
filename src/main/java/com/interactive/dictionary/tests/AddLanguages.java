package com.interactive.dictionary.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.interactive.dictionary.base.BaseTest;
import com.interactive.dictionary.components.Navigation;
import com.interactive.dictionary.pages.AddLanguagePage;
import com.interactive.dictionary.pages.LanguagesPage;
import com.interactive.dictionary.utils.Utils;

public class AddLanguages extends BaseTest {

	@Test(priority = 1)
	public void addLanguages() {
		
		Navigation navigation = new Navigation(driver);
		navigation.getIntoLanguages();
		
		LanguagesPage languagesPage = new LanguagesPage(driver);
		languagesPage.clickAddLanguage();
		
		AddLanguagePage addLanguagePage = new AddLanguagePage(driver);
		addLanguagePage.fillLanguageName(Utils.getStringFromJson("MainFlow1", "language1", "testData"));
		addLanguagePage.clickAddLanguage();
		Assert.assertEquals(languagesPage.checkElementInTable(1), 
				Utils.getStringFromJson("MainFlow1", "language1", "expectedData"));
		
		languagesPage.clickAddLanguage();
		addLanguagePage.fillLanguageName(Utils.getStringFromJson("MainFlow1", "language2", "testData"));
		addLanguagePage.clickAddLanguage();
		Assert.assertEquals(languagesPage.checkElementInTable(2), 
				Utils.getStringFromJson("MainFlow1", "language2", "expectedData"));
		
		navigation.getIntoCategories();
	}
	
}
