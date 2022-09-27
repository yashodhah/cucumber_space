@Core @Employee
Feature: User/Employee

  @Add
  Scenario: New Employee add
    Given I navigate to the employees page
    When I add new employee details
    And I press next button on employees page
    And I add new employee authentication details
    And I press save button on employee authentication page
    Then New employee should be created
    And I approve employee
    Then Employee should be approved

#  Scenario: Edit created Employee
#    When I click edit employee
#    And I edit the employee
#    Then  Employee should be updated
#    And I approve the newly created country
#    Then New country should be approved



