Feature: Shopping Cart Management

  Background:
    Given the user has already logged in to the application
      | firstName | lastName | password     |
      | erta      | xhixho   | Password123! |

  Scenario: Add all items to the Shopping Cart
    Given the user is on Jacket Page with filters applied
    When user adds all displayed items to the Shopping Cart
    Then the success Add to Cart message is displayed

  Scenario: Open the Shopping Cart Page

    Given the user has added all the items in the cart
    When the user clicks on the Shopping Cart link
    Then the user is navigated to the "Shopping Cart" Page
    And the Order Total price is correct


