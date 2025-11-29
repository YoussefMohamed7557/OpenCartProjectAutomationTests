Feature: Login

  Background:
    Given the browser is open

  @TC06
  Scenario: Login valid credentials
    Given the user is on the login page
    When the user enters valid credentials
    And the user submits the login form
    Then the user should be logged in
    And the "Logged in as" text should be visible

  @TC07
  Scenario: Invalid login
    Given the user is on the login page
    When the user enters invalid credentials
    And the user submits the login form
    Then an authentication error should be displayed

  @TC12
  Scenario: Login after deletion
    Given the user account has been deleted
    When the user attempts to login with deleted credentials
    Then login should fail with account not found message

  @TC14
  Scenario: Empty login
    Given the user is on the login page
    When the user submits the login form with empty fields
    Then validation errors should be shown for required fields

  @TC22
  Scenario: Login with spaces
    Given the user is on the login page
    When the user enters credentials with leading or trailing spaces
    And the user submits the login form
    Then login should normalize input and proceed or show invalid credentials

  @TC23
  Scenario: Uppercase login
    Given the user is on the login page
    When the user enters the username in uppercase and valid password
    And the user submits the login form
    Then login should be case-insensitive for username if supported

  @TC27
  Scenario: Enter key login
    Given the user is on the login page
    When the user fills credentials and presses Enter
    Then the user should be logged in

  @TC31
  Scenario: Brute force login (no rate limiting)
    Given the user is on the login page
    When the user attempts multiple rapid failed logins
    Then the application should either rate limit or record failed attempts
