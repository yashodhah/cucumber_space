@Core @Title
Feature: Title master data

  @Add
  Scenario: Create a new Title
    Given I navigate to the Titles page
    And I add new Title details
    And I save Title
    Then New Title should be created
    And I approve Title
    Then Title should be approved

  @Edit
  Scenario: Edit the created Title
    When I click edit Title
    And I update Title details
    And I save Title
    Then Title should be updated
    And I approve Title
    Then Title should be approved

  @Approvals
  Scenario: Basic Approval cycle
    When I perform basic approval cycle in Title list
    Then approval cycle should be success

  Scenario: Clean up Title
    And I close Title tab

