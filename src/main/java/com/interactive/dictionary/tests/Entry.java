package com.interactive.dictionary.tests;

import org.testng.annotations.Test;

import com.interactive.dictionary.base.BaseTest;
import com.interactive.dictionary.pages.LoginPage;

public class Entry extends BaseTest {

	@Test
	public void EnterAndLogin() {
		
		LoginPage loginPage = new LoginPage(this.driver);
		loginPage.enterPage();
		loginPage.login();
	}
	
}
