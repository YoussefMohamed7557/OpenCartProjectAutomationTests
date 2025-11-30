Feature: Account deletion

  Background:
    Given the user is on the home page
    And the user navigates to the login page
    And the user logs in with email "abanoub03@gmail.com" and password "1122334455"

  @TC09
  Scenario: Delete user account successfully
    When the user clicks the delete account button
    Then the account deleted message should be visible
    And the user clicks the continue button
    And the user should be on the home page again
