package com.cydeo.pages;

import com.cydeo.utilities.Driver;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SmartBearOrderPage {

    public SmartBearOrderPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy (id ="ctl00_MainContent_fmwOrder_ddlProduct")
    public WebElement dropDown ;

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






}
