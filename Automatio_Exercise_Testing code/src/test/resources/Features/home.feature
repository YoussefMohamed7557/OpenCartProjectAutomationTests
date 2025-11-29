Feature: Home Page

  Background:
    Given the browser is open

  @TC01
  Scenario: Verify Home Page Loads
    Given the browser is open
    When the user navigates to the home page
    Then the home page should be displayed
    And the main header or logo should be visible

  @TC24
  Scenario: Navigate to Contact Us
    Given the browser is open
    And the user is on the home page
    When the user clicks the Contact Us button
    Then the Contact Us page should be displayed
    And the contact form should be visible

  @TC25
  Scenario: Navigate to Products
    Given the browser is open
    And the user is on the home page
    When the user clicks the Products link
    Then the Products page should be displayed
    And at least one product listing should be visible

  @TC26
  Scenario: Logo Click Returns Home
    Given the browser is open
    And the user is on a non-home page
    When the user clicks the site logo
    Then the user should be redirected to the home page
    Then the main header or logo should be visible
