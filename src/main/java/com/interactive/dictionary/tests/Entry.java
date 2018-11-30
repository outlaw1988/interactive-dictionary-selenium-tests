package com.interactive.dictionary.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.interactive.dictionary.base.BaseTest;
import com.interactive.dictionary.pages.CategoriesPage;
import com.interactive.dictionary.pages.LoginPage;
import com.interactive.dictionary.utils.ReadConfig;

//import com.interactive.dictionary.base.BaseTest;


public class Entry extends BaseTest {

	@Test(priority = 1)
	public void EnterAndLogin() {
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterPage(ReadConfig.getProperty("url"));
		loginPage.login(username, password);
		
		CategoriesPage categoriesPage = new CategoriesPage(driver);
		Assert.assertTrue(categoriesPage.checkConfirmEl());
	}
	
}
