
Feature: Login Funktion
  Damit ein Benutzer auf geschützte Bereiche zugreifen kann
  Möchte ich mich mit gültigen Zugangsdaten anmelden

  @smoke
  Scenario: Erfolgreicher Login
    Given Benutzer ist auf der Login-Seite
    When Benutzer "tomsmith" mit Passwort "SuperSecretPassword!" einloggt
    Then wird das Dashboard angezeigt

  @regression
  Scenario: Successful Login
    Given the user is on the login page
    When the user logs in with username "tomsmith" and password "SuperSecretPassword!"
    Then the dashboard is displayed

  @smoke
  Scenario: Login with invalid credentials
    Given the user is on the login page
    When the user logs in with username "invalidUser" and password "wrongPass"
    Then an error message is shown containing "Your username is invalid!"