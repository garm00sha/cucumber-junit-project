Feature:Smartbear software link verification

  Background:
    Given user is logged in on landing page

  @smartBear  @US_101_1
  Scenario: User sees all the links displayed on the landing page
    When user sees links displayed on the page
    Then user verifies "6" links are on the page
    And the names of links are following:
      |View all orders  |
      |View all products|
      |Order            |
      |Logout           |
      |Check All        |
      |Uncheck All      |


  @smartBearOrder  @US_101_2
    Scenario: User is able to create new order
      Given  user is on Order page
      When user fills the order form
      And user clicks Process button
      Then user sees a new message "New order has been successfully added."
