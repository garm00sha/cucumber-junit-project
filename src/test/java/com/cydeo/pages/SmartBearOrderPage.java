package com.cydeo.pages;

import com.cydeo.utilities.Driver;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SmartBearOrderPage {

    public SmartBearOrderPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
    public WebElement dropDown;

    @FindBy(xpath = "//input[@id='ctl00_MainContent_fmwOrder_txtQuantity']")
    public WebElement quantityBox;

    @FindBy(xpath = "(//input[@class='btn_dark'])[1]")
    public WebElement calculateBtn;

    @FindBy(xpath = "//input[@id='ctl00_MainContent_fmwOrder_txtName']")
    public WebElement customerName;

    @FindBy(xpath = "//input[@id='ctl00_MainContent_fmwOrder_cardList_0']")
    public WebElement visa;
    @FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
    public WebElement processBtn;

    @FindBy(tagName = "strong")
    public WebElement actualMsg;

    @FindBy(xpath = "//table[@id='ctl00_MainContent_fmwOrder']//input[@id='ctl00_MainContent_fmwOrder_TextBox6']")
    public WebElement cardNumInputBox;


    public void note_messageVerification(String expMsg) {
        Assert.assertEquals("Message verification for placing new order - FAILED!",
                expMsg,
                actualMsg.getText());
    }

    @FindBy(xpath = "//input[@id='ctl00_MainContent_fmwOrder_TextBox2']")
    public WebElement streetInputBox;

    @FindBy(css = "#ctl00_MainContent_fmwOrder_TextBox3")
    public WebElement cityInputBox;

    @FindBy(css = "#ctl00_MainContent_fmwOrder_TextBox4")
    public WebElement stateInputBox;

    @FindBy(css = "#ctl00_MainContent_fmwOrder_TextBox5")
    public WebElement zipCodeInputBox;

    @FindBy(xpath = "(//input[@type='radio'])[1]")
    public WebElement cardType;

    @FindBy(xpath = "table[@id='ctl00_MainContent_fmwOrder_cardList']")
    public List<WebElement> cardTypes;

    public void choosingCardType(String expectedCardType, List<WebElement> cardTypes) {
        for (WebElement each : cardTypes) {
            if (each.getAttribute("value").equalsIgnoreCase(expectedCardType)) {
                each.click();
            }
        }
    }

    @FindBy(css = "#ctl00_MainContent_fmwOrder_TextBox6")
    public WebElement CardNumInputBox;

    @FindBy(css = "#ctl00_MainContent_fmwOrder_TextBox1")
    public WebElement expireDateInputBox;

    @FindBy(linkText = "View all orders")
    public WebElement viewAllOrder;

    //TODO: locator for checkbox next to... Finish assertions


}
