Feature: Login with multiple credentials using Scenario Outline
  In order to verify different login outcomes
  As a tester
  I want to run the same scenario for multiple username/password pairs

  @smoke
  Scenario Outline: Attempt login with different credentials
    Given the user is on the login page
    When the user logs in with username "<username>" and password "<password>"
    Then the flash message should contain "<expectedMessage>"

    Examples:
      | username  | password               | expectedMessage                    |
      | tomsmith  | SuperSecretPassword!   | You logged into a secure area!     |
      | tomsmith  | wrong                  | Your password is invalid!          |
      | wrong     | SuperSecretPassword!   | Your username is invalid!          |