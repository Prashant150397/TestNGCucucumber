Feature: Cart Functionality for OpenCart E-commerce Website
  
  As a user of the OpenCart website
  I want to be able to log in with my account
  So that I can access my account-related features and manage my orders

  Background: 
    Given I am on the login page

  @Smoke
  @Regression
  Scenario: Verify the Username and password is displayed
    Then the username and password is displayed
    
  @Smoke
  @Regression
  Scenario Outline: Login is successful
    Given i enter "<username>" and "<password>"
    When i clic on login button
    Then the login is successful
    
  Examples:
  |username|password|
  |Admin|admin123|
  
