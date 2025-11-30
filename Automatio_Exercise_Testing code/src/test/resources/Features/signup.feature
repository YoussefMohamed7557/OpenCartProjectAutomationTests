Feature: User signup

  Background:
    Given the user is on the home page
    And the user navigates to the login page

  @TC02
  Scenario: Navigate to signup page
    Then the signup and login sections should be visible

  @TC03
  Scenario: Register new user successfully
    When the user signs up with a unique valid name and email
    And the user completes the account creation form with valid data
    Then the account should be created successfully
    And the user should be logged in after signup

  @TC04
  Scenario: Register user with invalid email format
    When the user signs up with name "TestUser" and email "invalidemailformat"
    Then the user should stay on the signup or login page

  @TC05
  Scenario: Register user with existing email
    When the user signs up with name "TestUser" and email "testuser0001@gmail.com"
    Then an email already exists error should be visible

  @TC10
  Scenario: Signup with missing required email field
    When the user signs up with name "emptyuser" and email ""
    Then the user should stay on the signup or login page

  @TC11 @bug
  Scenario: Signup with weak password
    When the user signs up with a unique valid name and email
    And the user completes the account creation form with a weak password
    Then a weak password error should be visible

  @TC18
  Scenario: Signup with missing name field
    When the user signs up with name "" and email generated dynamically
    Then the user should stay on the signup or login page

  @TC19
  Scenario: Signup without password on account creation
    When the user signs up with name "NoPassUser" and email generated dynamically
    And the user completes the account creation form without password
    Then the account should not be created
    And the user should stay on the signup or login page

  @TC20
  Scenario: Signup with max length data
    When the user signs up with max length valid data
    Then the account should be created successfully
    And the user should be logged in after signup

  @TC21 @bug
  Scenario: Signup with special characters in name
    When the user signs up with special characters in name
    Then the account should be created successfully
    And the logged in username should be sanitized

  @TC30
  Scenario: Multiple clicks on Create Account button
    When the user signs up with a unique valid name and email
    And the user fills the account creation form and clicks create account multiple times
    Then the account should be created or no weak password error should be visible
