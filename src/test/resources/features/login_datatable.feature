Feature: Login attempts via DataTable
  In order to validate login outcomes
  As a tester
  I want to try multiple username/password combinations from a table

  @smoke
  Scenario: Try several combinations and verify the message
    Given this user is on the login page
    When this user tries to login with:
      | username  | password               | expectedMessage                    |
      | tomsmith  | SuperSecretPassword!   | You logged into a secure area!     |
      | tomsmith  | wrong                  | Your password is invalid!          |
      | wrong     | SuperSecretPassword!   | Your username is invalid!          |

    Then all login attempts produced the expected messages