Feature: Logout
  In order to protect my account
  As a logged-in user
  I want to log out and return to the login page

  @regression
  Scenario: Successful logout after a valid login
    Given the user is on the login page
    When the user logs in with username "tomsmith" and password "SuperSecretPassword!"
    And the user clicks logout
    Then the login page is displayed again
    And a message is shown containing "You logged out of the secure area!"