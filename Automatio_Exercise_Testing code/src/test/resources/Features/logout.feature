Feature: Logout

  Background:
    Given the user is on the home page
    And the user navigates to the login page
    And the user logs in with email "1122334455@gmail.com" and password "1122334455"

  @TC08
  Scenario: Logout user successfully
    When the user clicks the logout button
    Then the user should be on the login page
    And the login message should be visible

  @TC13
  Scenario: Logout and use back button
    When the user clicks the logout button
    And the user navigates back and refreshes the page
    Then the user should be logged out

