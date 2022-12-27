package dev.chan.steps;

import dev.chan.pages.*;
import dev.chan.runners.BugCatcherRunner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;

public class AssignDefectImpl {
    public WebDriver driver = BugCatcherRunner.driver;
    public HomePage homePage = BugCatcherRunner.homePage;

    public static String firstDefectID = new String();

    @Then("The manager should see pending defects")
    public void the_manager_should_see_pending_defects() {
        assertEquals("Assign Defects", homePage.defectList.getText());
    }
    @When("The manager clicks on the select button for a defect")
    public void the_manager_clicks_on_the_select_button_for_a_defect() {

        homePage.selectDefectBtn.click();
    }
    @Then("The defect description should appear in bold")
    public void the_defect_description_should_appear_in_bold() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(homePage.boldText)));
        String fontWeight = homePage.boldText.getCssValue("font-weight");
        boolean isBold = "bold".equals(fontWeight) || "bolder".equals(fontWeight) || Integer.parseInt(fontWeight) >= 700;
        assertTrue(isBold);
    }
    @When("The manager selects a tester from the drop down list")
    public void the_manager_selects_a_tester_from_the_drop_down_list() {
        homePage.employeeList.sendKeys("ryeGuy");
    }
    @When("The manager clicks assign")
    public void the_manager_clicks_assign() {
        firstDefectID = driver.findElement(By.xpath("//*[@id=\"root\"]/table/tbody/tr[1]/td[1]")).getText();
        System.out.println(firstDefectID);
        homePage.assignBtn.click();
    }
    @Then("The defect should disappear from the list")
    public void the_defect_should_disappear_from_the_list() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(homePage.boldText));
        String newFirstDefectID = driver.findElement(By.xpath("//*[@id=\"root\"]/table/tbody/tr[1]/td[1]")).getText();
        System.out.println(newFirstDefectID);
        assertNotEquals(firstDefectID, newFirstDefectID);
    }

}

