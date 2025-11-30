Feature: Home page navigation

  @TC01
  Scenario: Home page loads successfully
    Given the user is on the home page
    Then the home page should be displayed with logo, login link, and featured items

  @TC24
  Scenario: Navigate to Contact Us page
    Given the user is on the home page
    When the user navigates to the Contact Us page
    Then the Contact Us page should be displayed

  @TC25
  Scenario: Navigate to Products page
    Given the user is on the home page
    When the user navigates to the Products page
    Then the Products page should be displayed

  @TC26
  Scenario: Click logo from inner page returns home
    Given the user is on the home page
    And the user navigates to the Products page
    When the user clicks the home logo
    Then the user should be on the home page again
