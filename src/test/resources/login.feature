Feature: login
  I should be able to login with a unique user name and a password.

  Scenario: Successful login
    Given I am at the home page
    When I go to the login page
    And I enter a valid name
    And I enter a valid password
    And I submit
    Then I should be at the profile page