Feature: Enter numeric values from money expressions
  In order to avoid parsing in step code
  As a tester
  I want to use a custom parameter type for money

  @smoke
  Scenario: Type a CHF amount into the input field
    Given the user is on the numeric inputs page
    When the user types amount CHF 12.50
    Then the input value should be 12.5