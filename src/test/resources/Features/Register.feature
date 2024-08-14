Feature: Register Action

  Scenario: As a new user, I want to navigate to the account creation page
    Given the user is on the homepage
    When the user clicks on Create an Account link
    Then the "Create New Customer Account" page is displayed

  Scenario Outline: As a new user, I want to successfully create an account
    Given the user is on the Create New Customer Account page
    When the user enters valid account details
      | firstName   | lastName   | password   |
      | <firstName> | <lastName> | <password> |
    And the user submits the account creation form
    Then a success message is displayed

    Examples:
      | firstName | lastName | password    |
      | Erta      | Xhixho   | Password123 |

  Scenario: As a newly created user, I want to sign out after account creation
    Given the user has successfully created an account
    When the user clicks on Sign Out button in User profile
    Then the user is logged out