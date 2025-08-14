Feature: Forgot Password
  In order to recover my account
  As a user who forgot the password
  I want to request a password reset

  Scenario: Request reset email
    Given the user is on the login page
    When the user navigates to the forgot password page
    And the user requests a password reset for "someone@example.com"
    Then a confirmation is shown containing "Your e-mail's been sent!"
