package dev.chan.steps;

import dev.chan.pages.*;
import dev.chan.runners.BugCatcherRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static org.junit.Assert.*;

public class ReportDefectPositiveImpl {
    public WebDriver driver = BugCatcherRunner.driver;
    public LoginPage loginPage = BugCatcherRunner.loginPage;
    public HomePage homePage = BugCatcherRunner.homePage;
    public DefectReporterPage defectReporterPage = BugCatcherRunner.defectReporterPage;


    @Given("The employee is on the Defect Reporter Page")
    public void the_employee_is_on_the_defect_reporter_page() {
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=18");
        loginPage.usernameField.clear();
        loginPage.usernameField.sendKeys("ryeGuy");
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("coolbeans");
        loginPage.loginBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(homePage.reportDefectLink));
        homePage.reportDefectLink.click();
    }
    @When("The employee selects todays date")
    public void the_employee_selects_todays_date() {
        LocalDate today = java.time.LocalDate.now(); // YYYY-MM-DD
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = today.format(formatter);
        defectReporterPage.datePicker.sendKeys(date);
    }
    @When("The employee types in Description with")
    public void the_employee_types_in_description_with(String description) {
        defectReporterPage.description.clear();
        defectReporterPage.description.sendKeys(description);
    }
    @When("The employee types in Steps with")
    public void the_employee_types_in_steps_with(String steps) {
        defectReporterPage.reproduceStep.clear();
        defectReporterPage.reproduceStep.sendKeys(steps);
    }
    @When("The employee selects {string} priority")
    public void the_employee_selects_priority(String priority) {
        Actions action = new Actions(driver);
        action.click(defectReporterPage.priority).build().perform();
        if(priority == "Medium"){
            action.sendKeys(Keys.ARROW_LEFT).build().perform();
        } else if ( priority == "LOW"){
            for(int i=0; i<2;i++){
                action.sendKeys(Keys.ARROW_LEFT).build().perform();
            }
        }
    }
    @When("The employee selects {string} severity")
    public void the_employee_selects_severity(String severity) {
        Actions action = new Actions(driver);
        action.click(defectReporterPage.severity).build().perform();
        if(severity == "Medium"){
            action.sendKeys(Keys.ARROW_LEFT).build().perform();
        } else if ( severity == "LOW"){
            for(int i=0; i<2;i++){
                action.sendKeys(Keys.ARROW_LEFT).build().perform();
            }
        }
    }
    @When("The employee clicks the report button")
    public void the_employee_clicks_the_report_button() {
        defectReporterPage.reportBtn.click();
    }
    @Then("There should be a confirmation box")
    public void there_should_be_a_confirmation_box() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        String alertMsg = driver.switchTo().alert().getText();
        assertEquals("Confirm Bug Report?", alertMsg);

    }
    @When("The employee clicks Ok")
    public void the_employee_clicks_ok() {
        driver.switchTo().alert().accept();
    }
    @Then("A modal should appear with a Defect ID")
    public void a_modal_should_appear_with_a_defect_id() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(defectReporterPage.modal));
        assertNotNull(defectReporterPage.defectCreatedMsg);

    }
    @When("The employee clicks close")
    public void the_employee_clicks_close() {
        defectReporterPage.closeBtn.click();
    }
    @Then("The modal should disappear")
    public void the_modal_should_disappear() {

        boolean disappeared = false;
        try {
            boolean displayed = defectReporterPage.modal.isDisplayed();
        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            disappeared = true;
        } catch (StaleElementReferenceException e){
            System.out.println(e.getMessage());
            disappeared =true;
        }

        assertTrue(disappeared);
    }

}
