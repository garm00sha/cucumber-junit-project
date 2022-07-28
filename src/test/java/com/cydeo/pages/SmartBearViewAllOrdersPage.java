package com.cydeo.pages;

import com.cydeo.utilities.Driver;
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
}
