package com.qa.mystepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.*;

import org.testng.Assert;

import pages.DriverFactory;
import pages.LoginPage;
import util.ConfigReader;

public class LoginStepDefinitions {

    private LoginPage loginPage;
    // ScenarioContext
    private Map<String, String> data = new HashMap<>();
    
    @Given("I am on the OpenCart login page")
    public void i_am_on_the_open_cart_login_page() {
        DriverFactory.getDriver().get(ConfigReader.get("url"));
        loginPage = new LoginPage();
        String email="prashantkumarrajput59@gmail.com";
        data.put("email", email);
    }

    @Given("I have entered a valid username and password")
    public void i_have_entered_a_valid_username_and_password(DataTable datatable ) {
    	List<Map<String,String>> dataTables=datatable.asMaps();
    	
    	// here we use hashmap as scenarioContext
    	String getEmail=data.get("email");
    	// Use Data table
    	for (Map<String, String> user : dataTables) {
    		 String email = user.get("username");
    	        String password = user.get("password");
    		loginPage.enterEmail(email);
            loginPage.enterPassword(password);
    	}
        
    }

    @Given("I have entered valid {string} and {string}")
    public void i_have_entered_invalid_and(String username, String password) {
        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
    }

    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
       Assert.assertEquals(loginPage.checkLogoutLink(), true);
    }



    @Then("the login is successfull")
    public void the_login_is_successfull() {
        // Assert that an error message is displayed on the page matching the expected error message
    	Assert.assertEquals(loginPage.checkLogoutLink(), true);
    	}

    @When("I click on the \"Forgotten Password\" link")
    public void i_click_on_the_forgotten_password_link() {
        loginPage.clickForgottenPasswordLink();
    }

    @Then("I should be redirected to the password reset page")
    public void i_should_be_redirected_to_the_password_reset_page() {
        // Assert that the current URL contains the password reset page route
        Assert.assertTrue(loginPage.getForgotPwdPageUrl().contains("account/forgotten"));
    }
}

