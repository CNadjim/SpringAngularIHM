Feature: Role based security
  I can be notified when I use a bad password.
  I should be able to login with a valid user name and a password.
  I can be notified when I do not have necessary privileges.
  I should be able to logout

  Scenario: Wrong login
    Given I am at the login page
    When I enter a valid Admin name
    And I enter a wrong Admin password
    And I submit
    Then I should get a message error

  Scenario: Successful login
    Given I am at the login page
    When I enter a valid Admin name
    And I enter a valid Admin password
    And I submit
    Then I should be at the profile page

  Scenario: Redirect from restricted page when I haven't rights
    Given I am at the login page
    When I enter a valid User name
    And I enter a valid User password
    And I submit
    And I go to User listing Page
    Then I should be redirect to 403 page

  Scenario: Acces restricted page when I have the rights
    Given I am at the login page
    When I enter a valid Admin name
    And I enter a valid Admin password
    And I submit
    And I go to User listing Page
    Then I should see User List

  Scenario: Logout after login
    Given I am at the login page
    When I enter a valid User name
    And I enter a valid User password
    And I submit
    And I click into logout button
    Then I should be redirect to home page
    And The session should be empty
