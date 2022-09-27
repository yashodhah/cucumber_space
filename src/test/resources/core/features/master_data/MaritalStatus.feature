@Core @MaritalStatus
Feature: Marital Status master data

  @Add
  Scenario: Create a new Marital Status
    Given I navigate to the Marital Status page
    And I add new Marital Status details
    And I save Marital Status
    Then New Marital Status should be created
    And I approve Marital Status
    Then Marital Status should be approved

  @Edit
  Scenario: Edit the created Marital Status
    When I click edit Marital Status
    And I update Marital Status details
    And I save Marital Status
    Then Marital Status should be updated
    And I approve Marital Status
    Then Marital Status should be approved

  @Approvals
  Scenario: Basic Approval cycle
    When I perform basic approval cycle in Marital Status list
    Then approval cycle should be success

  Scenario: Clean up Marital Status
    And I close Marital Status tab