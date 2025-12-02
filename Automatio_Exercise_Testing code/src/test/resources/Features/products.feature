@TC08
Feature: Products page navigation and product details

  Scenario: Navigate to All Products page successfully
    Given the user is on the home page for products
    When the user clicks on the Products link
    Then the Products page URL should be correct

  Scenario: Open product details from All Products page
    Given the user is on the Products page
    When the user clicks on the View Product button for the first product
    Then the product information section should be displayed