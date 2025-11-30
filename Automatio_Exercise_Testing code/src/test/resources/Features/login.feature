Feature: Login

  Background:
    Given the user is on the home page
    And the user navigates to the login page

  @TC06
  Scenario: Login with valid credentials
    When the user logs in with email "1122334455@gmail.com" and password "1122334455"
    Then the user should be logged in
    And the logout link should be visible

  @TC07
  Scenario: Login with incorrect password
    When the user logs in with email "1122334455@gmail.com" and password "wrongpassword"
    Then an incorrect credentials error should be visible
    And the user should stay on the login page

  @TC12
  Scenario: Login after account deletion
    When the user logs in with email "deleteduser@gmail.com" and password "1122334455"
    Then an incorrect credentials error should be visible

  @TC14
  Scenario: Login with empty fields
    When the user logs in with email "" and password ""
    Then the user should stay on the login page

  @TC22 @bug
  Scenario: Login with spaces around credentials
    When the user logs in with email " 1122334455@gmail.com " and password " 1122334455 "
    Then the user should be logged in

  @TC23 @bug
  Scenario: Login with uppercase email
    When the user logs in with email "1122334455@GMAIL.COM" and password "1122334455"
    Then the user should be logged in

  @TC27
  Scenario: Login with Enter key
    When the user enters email "1122334455@gmail.com" and password "1122334455" and submits with Enter key
    Then the user should be logged in

  @TC31 @bug
  Scenario: No rate limiting on repeated failed login attempts
    When the user attempts to login 15 times with email "1122334455@gmail.com" and wrong passwords
    Then an incorrect credentials error should be visible after each attempt
