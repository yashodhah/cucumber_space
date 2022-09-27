@Core @_Country
Feature: Country/City master data

  Background:
    Given I navigate to the countries page

  @Add
  Scenario: Create new country
    And I add new country details
    And I save country
    Then New country should be created
    And I approve country
    Then Country should be approved

  @Edit
  Scenario: Edit created country
    When I click edit country
    And I update country details
    And I save country
    Then Country should be updated
    And I approve country
    Then Country should be approved

  @Approvals
  Scenario: Basic Approval cycle
    When I perform basic approval cycle in country list
    Then approval cycle should be success

  @City @Add
  Scenario: Create new city
    Then I navigate to the city page
    And I add new city details
    And I save city
    Then New city should be created
    And I approve city
    Then City should be approved

  @City @Edit
  Scenario: Edit created city
    When I click edit city
    And I update city details
    And I save city
    Then City should be updated
    And I approve city
    Then City should be approved

  Scenario: Clean up Country
    And I press close city
    And I close country tab


