Feature: User login functionality

  @SmokeTest
  Scenario Outline: Check login is successful with valid user name "<username>" & password "<password>"
    Given user is on login page
    When user enters '<username>' in login name text box
    And user enters '<password>' in password text box
    And clicks on login button
    Then user is navigated to home page

    Examples: SCENARIO OUTLINE DATA
      | username | password |
      | dy       | 123      |
      | dd       | 123      |

#  @SmokeTest
#  Scenario: Check login is successful with valid credentials2
#    Given user is on login page
#    When user enters username and password
#    Then user is navigated to home page

