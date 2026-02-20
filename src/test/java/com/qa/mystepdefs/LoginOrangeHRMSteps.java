package com.qa.mystepdefs;

import io.cucumber.java.en.*;
import pages.DriverFactory;
import pages.LoginPageOrangeHRM;
import util.ConfigReader;

public class LoginOrangeHRMSteps {

	private LoginPageOrangeHRM loginPageOrangeHRM;

	@Given("I am on the login page")
	public void i_am_on_the_login_page() {
		DriverFactory.getDriver().get(ConfigReader.get("OrangeHrmUrl"));
		loginPageOrangeHRM=new LoginPageOrangeHRM();
	}

	@Then("the username and password is displayed")
	public void the_username_and_password_is_displayed() {
		loginPageOrangeHRM.isUserNameDisplayed();
		loginPageOrangeHRM.isPasswordDisplayed();
	}
	@Given("i enter {string} and {string}")
	public void i_enter_and(String username, String password) {
		loginPageOrangeHRM.enterUsernameAndPassword(username, password);
	}
	@When("i clic on login button")
	public void i_clic_on_login_button() {
		loginPageOrangeHRM.clickOnLoginButton();
	}
	@Then("the login is successful")
	public void the_login_is_successful() {

	}
}
