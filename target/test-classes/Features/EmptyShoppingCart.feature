Feature: Wish List Management

  Background:
    Given the user has already logged in to the application
      | firstName | lastName | password     |
      | erta      | xhixho   | Password123! |

    Scenario: Delete the first item on shopping cart
      Given the user is in Shopping cart with all items added
      When user deletes the first item on shopping cart
      Then the number of elements in the Shopping Cart table is decreased by one

      Scenario: Empty Shopping cart
        Given the user is in Shopping cart with all items added
        When the user deletes one by one the items added
        Then the Shopping cart number should be empty
