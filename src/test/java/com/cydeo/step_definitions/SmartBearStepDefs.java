package com.cydeo.step_definitions;

import com.cydeo.pages.SmartBearLoginPage;
import com.cydeo.pages.SmartBearOrderPage;
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
    WebDriver driver = Driver.getDriver();

    List<WebElement> allLinks;

    @Given("user is logged in on landing page")
    public void user_is_logged_in_on_landing_page() {
        smartBearLoginPage.loginToSmartBear(driver);


    }
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

    @Given("user is on Order page")
    public void userIsOnOrderPage() {
        smartBearLoginPage.orderBtn.click();
    }

    @When("user fills the order form")
    public void userFillsTheOrderForm() {
        Select dropDown = new Select(smartBearOrderPage.dropDown);
        dropDown.selectByIndex(1);
        smartBearOrderPage.quantityBox.clear();
        smartBearOrderPage.quantityBox.sendKeys("2");
        smartBearOrderPage.calculateBtn.click();
        Faker faker= new Faker();
        String name= faker.name().fullName();
        String streetAddress=faker.address().streetAddress();
        String city = faker.address().cityName();
        String state = faker.address().state();
        String zipCode = faker.address().zipCode();
        String cardNum = faker.finance().creditCard();
        String expDate = String.valueOf(faker.number().numberBetween(1-12,23-26));
        smartBearOrderPage.customerName.sendKeys(name + Keys.TAB + streetAddress + Keys.TAB + city + Keys.TAB+state+Keys.TAB+zipCode);
        //smartBearOrderPage.customerName.sendKeys(Keys.TAB);



    }

    @And("user clicks Process button")
    public void userClicksProcessButton() {
        
    }

    @Then("user sees a new message {string}")
    public void userSeesANewMessage(String arg0) {
    }
}
