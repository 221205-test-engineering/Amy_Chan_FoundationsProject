Feature: Login

  Scenario: Login with correct credentials
    Given The employee is on the login page
    When  The employee types "<username>" into username input
    When The employee types "<password>" into password input
    When The employee clicks on the login button
    Then the employee should be on the <role> page
    Then The employee should see their name <fname> <lname> on the home page