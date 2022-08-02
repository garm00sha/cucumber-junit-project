package com.cydeo.pages;

import com.cydeo.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SmartBearViewAllOrdersPage {
    public SmartBearViewAllOrdersPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy (xpath = "//*[@id=\"ctl00_MainContent_orderGrid\"]/tbody/tr[6]/td[2]")
    public WebElement actualName;

    @FindBy (xpath = "//*[@id=\"ctl00_MainContent_orderGrid\"]/tbody/tr[6]/td[5]")
    public WebElement actualOrderDate;


    public void getName_verification_failed(String expectedName) {
        Assert.assertEquals("Name verification failed", expectedName, actualName.getText());
    }

    public void getOrder_date_verification_failed(String expectedOrderDate) {
        Assert.assertEquals("Order date verification failed", expectedOrderDate, actualOrderDate.getText());
    }

    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]//td[2]")
    public WebElement resultNameOnFirstRowFirstCell;
}
