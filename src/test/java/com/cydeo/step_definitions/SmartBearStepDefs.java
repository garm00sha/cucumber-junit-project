package com.cydeo.step_definitions;

import com.cydeo.pages.SmartBearLoginPage;
import com.cydeo.pages.SmartBearOrderPage;
import com.cydeo.pages.SmartBearViewAllOrdersPage;
import com.cydeo.utilities.BrowserUtils;
import com.cydeo.utilities.Driver;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class SmartBearStepDefs {
    SmartBearLoginPage smartBearLoginPage=new SmartBearLoginPage();
    SmartBearOrderPage smartBearOrderPage= new SmartBearOrderPage();

    SmartBearViewAllOrdersPage smartBearViewAllOrdersPage = new SmartBearViewAllOrdersPage();

    Select dropDown = new Select(smartBearOrderPage.dropDown);
    WebDriver driver = Driver.getDriver();
    Faker faker= new Faker();
    List<WebElement> allLinks;

    @Given("user is logged in on landing page")
    public void user_is_logged_in_on_landing_page() {
        smartBearLoginPage.loginToSmartBear(driver);
    }


    /**
     * Scenario: User sees all the links displayed on the landing page
     *     When user sees links displayed on the page
     *     Then user verifies "6" links are on the page
     *     And the names of links are following:
     *       |View all orders  |
     *       |View all products|
     *       |Order            |
     *       |Logout           |
     *       |Check All        |
     *       |Uncheck All      |
     */
    @When("user sees links displayed on the page")
    public void user_sees_links_displayed_on_the_page() {
        allLinks = driver.findElements(By.tagName("a"));
        for (WebElement eachLink : allLinks) {
            Assert.assertTrue(eachLink.isDisplayed());
        }
    }




    @Then("user verifies {string} links are on the page")
    public void user_verifies_links_are_on_the_page(String expectedNum) {
        allLinks = driver.findElements(By.tagName("a"));
        Assert.assertTrue(String.valueOf(allLinks.size()).equals(expectedNum));
    }


    @Then("the names of links are following:")
    public void the_names_of_links_are_following(List<String>expectedLinksText) {
        allLinks = driver.findElements(By.tagName("a"));
        List <String> actualLinkTexts=new ArrayList<>();
        for (WebElement eachLink : allLinks) {
            actualLinkTexts.add(eachLink.getText());
        }
        Assert.assertTrue(actualLinkTexts.equals(expectedLinksText));
    }

    //------------------------------------------------------------------------//


    /**
     * Scenario: User is able to create new order
     *       Given  user is on Order page
     *       When user fills the order form
     *       And user clicks Process button
     *       Then user sees a new message "New order has been successfully added."
     */
    @Given("user is on Order page")
    public void userIsOnOrderPage() {
        smartBearLoginPage.orderBtn.click();
    }

    @When("user fills the order form")
    public void userFillsTheOrderForm() {
        // declaring select obj for the dropdown

         // 1.filling Product Information
        // using select method select by index
        dropDown.selectByIndex(1);

        // setting quantity to 2 for albums
        smartBearOrderPage.quantityBox.clear();
        smartBearOrderPage.quantityBox.sendKeys("2");
        smartBearOrderPage.calculateBtn.click();

        // 2.filling address information
         // generating data using java faker for address info
        String name= faker.name().fullName();
        String streetAddress=faker.address().streetAddress();
        String city = faker.address().cityName();
        String state = faker.address().state();
        String zipCode = faker.numerify("#####");
        smartBearOrderPage.customerName.sendKeys(name + Keys.TAB + streetAddress + Keys.TAB + city + Keys.TAB+state+Keys.TAB+zipCode);

        // 3. filling payment information
        // generating data using java faker for payment info
        String cardNum = faker.numerify("################");
        String expDate = String.valueOf(faker.numerify("##/2#"));
        //need to find better expDate faker method

        // choosing visa by clicking radio btn
        smartBearOrderPage.visa.click();
        smartBearOrderPage.cardNumInputBox.sendKeys(cardNum+Keys.TAB+expDate);

        BrowserUtils.waitFor(5);

    }

    @And("user clicks Process button")
    public void userClicksProcessButton() {
        smartBearOrderPage.processBtn.click();
    }

    @Then("user sees a new message {string}")
    public void userSeesANewMessage(String expMsg) {
        smartBearOrderPage.note_messageVerification(expMsg);
    }


    //------------------------------------------------------------------------//


    /**
     * Scenario:  Smartbear software order verification
     * Then user verifies the name "Susan McLaren" has order on date "01/05/2010"
     */
    @Then("user verifies the name {string} has order on date {string}")
    public void user_verifies_the_name_has_order_on_date(String expectedName, String expectedOrderDate) {
        smartBearViewAllOrdersPage.getName_verification_failed(expectedName);
        smartBearViewAllOrdersPage.getOrder_date_verification_failed(expectedOrderDate);
    }
    /**
     *   Scenario: User is able to create new order
     *     Given  user is on Order page
     *     When user selects "FamilyAlbum" from product dropdown
     *     And user enters 4 to quantity
     *     And user enters "John Wick" to customer name
     *     And user enters "Kinzie Ave" to street
     *     And user enters "Chicago" to city
     *     And user enters "IL" to state
     *     And user enters 60056
     *     And user selects "Visa" as card type
     *     And user enters "1111222233334444" to card number
     *     And user enters 12/22 to expiration date
     *     And user clicks Process button
     *     Then user sees "John Wick" on the list on "View all orders" page
     *     And user selects "John Wick" entry and deletes it by clicking "Delete selected" button
     *     And user verifies there is no "John Wick" entry on the list anymore
     */

    @When("user selects {string} from product dropdown")
    public void user_selects_from_product_dropdown(String productName) {
        dropDown.selectByVisibleText(productName);
        Assert.assertEquals(productName, dropDown.getFirstSelectedOption().getText());
    }
    @When("user enters {int} to quantity")
    public void user_enters_to_quantity(Integer quantity) {
        smartBearOrderPage.quantityBox.clear();
        smartBearOrderPage.quantityBox.sendKeys(String.valueOf(quantity));
            }
    @When("user enters {string} to customer name")
    public void user_enters_to_customer_name(String customerName) {
        smartBearOrderPage.customerName.sendKeys(customerName);
    }
    @When("user enters {string} to street")
    public void user_enters_to_street(String string) {

    }
    @When("user enters {string} to city")
    public void user_enters_to_city(String string) {

    }
    @When("user enters {string} to state")
    public void user_enters_to_state(String string) {

    }
    @When("user enters {int}")
    public void user_enters(Integer int1) {

    }
    @When("user selects {string} as card type")
    public void user_selects_as_card_type(String string) {

    }
    @When("user enters {string} to card number")
    public void user_enters_to_card_number(String string) {

    }
    @When("user enters {int}\\/{int} to expiration date")
    public void user_enters_to_expiration_date(Integer int1, Integer int2) {

    }
    @Then("user sees {string} on the list on {string} page")
    public void user_sees_on_the_list_on_page(String string, String string2) {

    }
    @Then("user selects {string} entry and deletes it by clicking {string} button")
    public void user_selects_entry_and_deletes_it_by_clicking_button(String string, String string2) {

    }
    @Then("user verifies there is no {string} entry on the list anymore")
    public void user_verifies_there_is_no_entry_on_the_list_anymore(String string) {

    }
}
