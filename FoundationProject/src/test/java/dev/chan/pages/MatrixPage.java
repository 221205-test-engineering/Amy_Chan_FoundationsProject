package dev.chan.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MatrixPage {

    public WebDriver driver;

    public MatrixPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//*[@id=\"collapsible-trigger-1671644826966\"]/button")
    public WebElement showAirlineMatrixBtn;
    //*[@id="collapsible-content-1671644826966"]/div/table/tbody/tr[2]/td[6]/button

    @FindBy(xpath="//*[@id=\"collapsible-content-1671645238313\"]/div/table/tbody/tr[1]/td[6]/button")
    public WebElement editAirlineFirstRuleBtn;
}
