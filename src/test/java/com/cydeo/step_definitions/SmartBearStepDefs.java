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
        Select dropDown = new Select(smartBearOrderPage.dropDown);
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




}
