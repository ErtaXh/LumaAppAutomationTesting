Feature: Page Filters on Women's Jackets

  Background:
    Given the user has already logged in to the application
      | firstName | lastName | password     |
      | erta      | xhixho   | Password123! |

  Scenario: Filter jackets by color
    When the user navigates to the Women section, hovers over the Tops dropdown, and clicks on Jacket
    And the user selects a color from the Color dropdown
    Then all displayed products should have the selected color bordered in red

  Scenario: Filter jackets by price range
    When the user is on the jackets page with color filter applied
    And the user selects the first price range from the Price dropdown
    Then each displayed product should have a price within the selected range