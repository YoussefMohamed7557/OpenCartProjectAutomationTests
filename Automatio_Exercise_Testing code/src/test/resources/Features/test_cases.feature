@TC07
Feature: Test Cases page navigation

  Scenario: Navigate to Test Cases page successfully
    Given the user is on the home page for test cases
    When the user clicks on the Test Cases link
    Then the Test Cases page URL should be correct