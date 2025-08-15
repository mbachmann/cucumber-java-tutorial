Feature: Set multiple checkboxes via DataTable
  In order to quickly configure options
  As a user
  I want to set several checkboxes to the desired state

  @smoke
  Scenario: Configure checkboxes by index
    Given the user is on the checkboxes page
    When the user sets the checkboxes to:
      | index | state     |
      | 1     | checked   |
      | 2     | unchecked |
    Then the checkboxes should be:
      | index | state   |
      | 1     | checked |
      | 2     | unchecked |