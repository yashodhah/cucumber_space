@Core @Symbol
Feature: Symbol Master Data

  Scenario: Navigate to symbol add form
    When user navigates to symbol list
    And user clicks on grid add button
    Then user navigates to symbol add form

  @Add
  Scenario: Create a new symbol
    Given user navigates to symbol add form
    And user adds symbol details
    And user saves symbol
    Then new symbol should be created
    And user approves the symbol
    Then symbol should be approved

  @Edit
  Scenario: Update an existing symbol
    And user filters symbol
    And user clicks edit symbol
    And user updates symbol details
    And user saves symbol
    Then symbol should be updated
    And user performs advanced approvals
    And user closes the symbol tabs