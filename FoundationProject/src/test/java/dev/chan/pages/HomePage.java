package dev.chan.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p")
    public WebElement welcomeMsg;

    @FindBy(xpath = "//a[1]")
    public WebElement matricesLink;
    @FindBy(xpath = "//a[2]")
    public WebElement testCasesLink;
    @FindBy(xpath = "//a[3]")
    public WebElement reportDefectLink;

    @FindBy(xpath = "//a[4]")
    public WebElement defectOverviewLink;
    @FindBy(xpath = "//a[5]")
    public WebElement logoutBtn;


}
