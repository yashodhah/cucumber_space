@Core @Price_qty_factor
Feature: Price Quantity Factor master data

  @Add
  Scenario: Create new price factor
    Given I navigate to the price and quantity factor page
    And I add new price factor details
    And I save price factor
    Then New price factor should be created
    And I approve the price factor
    Then Price factor should be approved

  @Edit
  Scenario: Update new price factor
    When I click edit price factor
    And I update price factor details
    And I save price factor
    Then Price factor should be updated
    And I approve the price factor
    Then Price factor should be approved

  @Approvals
  Scenario: Basic Approval cycle
    When I perform basic approval cycle in price factor list
    Then approval cycle should be success

  Scenario: Clean up Price Factor
    And I close price factor tab

