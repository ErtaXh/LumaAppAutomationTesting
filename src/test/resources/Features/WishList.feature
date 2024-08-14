Feature: Wish List Management

  Background:
    Given the user has already logged in to the application
      | firstName | lastName | password     |
      | erta      | xhixho   | Password123! |

  Scenario: Remove Price Filter
    Given the user is on Jacket Page with filters applied
    When the user removes the Price filter
    Then the number of displayed items should increase

  Scenario: Add the items in the Wish List
    Given the user is on Jacket Page with color filter applied
    When the user adds the 2 items in the Wish List
    Then the success message is displayed
    And on User Profile is displayed the correct number of items 2