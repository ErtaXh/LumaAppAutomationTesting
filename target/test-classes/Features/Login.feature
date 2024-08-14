Feature: Sign In Action

  Background:
    Given the user has already registered to the application
      | firstName | lastName | password     |
      | erta      | xhixho   | Password123! |


  Scenario: As a user, I want to navigate to the sign-in page
    Given the user is on the homepage
    When the user clicks on the Sign In link
    Then the user is on the "Customer Login" page

  Scenario: As a registered user, I want to log in with valid credentials
    Given the newly registered user is on the Sign In page
    When the user logs in with valid credentials
    Then the username is displayed in the top-right corner of the page

#  Scenario: As a logged-in user, I want to sign out
#    Given the user is logged in
#    When the user clicks the Sign Out on the User profile
#    Then the user is logged out