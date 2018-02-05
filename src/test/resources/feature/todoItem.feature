Feature: Managing TodoItem.
  I can add a TodoItem.
  I can Delete a TodoItem.
  I can update a TodoItem.

  Scenario: I can delete a TodoItem.
    Given I am already logged
    When I go to TodoItem Management page
    And I add a TodoItem
    And I click to remove my todoItem
    Then I will have to see no todoItem

  Scenario: I can add a TodoItem.
    Given I am already logged
    When I go to TodoItem Management page
    And I enter a title to my todoItem
    And I enter a description to my todoItem
    And I click on submit
    Then I should see my new todoItem added


