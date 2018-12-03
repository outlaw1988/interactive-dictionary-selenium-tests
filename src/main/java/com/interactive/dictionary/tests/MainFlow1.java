package com.interactive.dictionary.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.interactive.dictionary.base.BaseTest;
import com.interactive.dictionary.components.Navigation;
import com.interactive.dictionary.pages.AddCategoryPage;
import com.interactive.dictionary.pages.AddLanguagePage;
import com.interactive.dictionary.pages.CategoriesPage;
import com.interactive.dictionary.pages.LanguagesPage;
import com.interactive.dictionary.utils.Utils;

public class MainFlow1 extends BaseTest {

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
	}
	
	@Test(priority = 2)
	public void addCategory() {
		Navigation navigation = new Navigation(driver);
		navigation.getIntoCategories();
		
		CategoriesPage categoriesPage = new CategoriesPage(driver);
		categoriesPage.clickAddCategory();
		
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
		
	}
	
}
