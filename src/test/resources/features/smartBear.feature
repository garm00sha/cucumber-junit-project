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

    @wip @US_101_3
      Scenario:  Smartbear software order verification
      Then user verifies the name "Susan McLaren" has order on date "01/05/2010"

  @smartBearOrder  @wip
  Scenario: User is able to create new order
    Given  user is on Order page
    When user selects "FamilyAlbum" from product dropdown
    And user enters 4 to quantity
    And user enters "John Wick" to customer name
    And user enters "Kinzie Ave" to street
    And user enters "Chicago" to city
    And user enters "IL" to state
    And user enters 60056
    And user selects "Visa" as card type
    And user enters "1111222233334444" to card number
    And user enters 12/22 to expiration date
    And user clicks Process button
    Then user sees "John Wick" on the list on "View all orders" page
    And user selects "John Wick" entry and deletes it by clicking "Delete selected" button
    And user verifies there is no "John Wick" entry on the list anymore

