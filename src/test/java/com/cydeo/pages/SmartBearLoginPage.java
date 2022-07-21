package com.cydeo.pages;

import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SmartBearLoginPage {
    public SmartBearLoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


    public static void loginToSmartBear(WebDriver driver) {
        driver.get(ConfigurationReader.getProperty("smart.bear.url"));
        WebElement inputUsername = driver.findElement(By.xpath("//input[@id='ctl00_MainContent_username']"));
        inputUsername.sendKeys(ConfigurationReader.getProperty("smart.bear.username"));

        WebElement inputPassword = driver.findElement(By.xpath("//input[@id='ctl00_MainContent_password']"));
        inputPassword.sendKeys(ConfigurationReader.getProperty("smart.bear.password"));

        WebElement loginBtn = driver.findElement(By.xpath("//input[@id='ctl00_MainContent_login_button']"));
        loginBtn.click();
    }
    @FindBy (xpath ="//a[.='Order']")
    public WebElement orderBtn ;
}
