package dev.chan.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[1]")
    public WebElement usernameField;

    @FindBy(xpath = "//input[2]")
    public WebElement passwordField;

    @FindBy(xpath = "//button")
    public WebElement loginBtn;

    @FindBy(xpath = "//p")
    public WebElement welcomeMsg;

}
