package dev.chan.steps;


import dev.chan.pages.HomePage;
import dev.chan.pages.LoginPage;
import dev.chan.pages.MatrixPage;
import dev.chan.runners.BugCatcherRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MatrixImpl {

    public WebDriver driver = BugCatcherRunner.driver;
    public LoginPage loginPage = BugCatcherRunner.loginPage;
    public HomePage homePage = BugCatcherRunner.homePage;
    public MatrixPage matrixPage = BugCatcherRunner.matrixPage;

    // For Scenario: Create a New Matrix
    @When("The manager chooses to create a new matrix")
    public void the_manager_chooses_to_create_a_new_matrix() {
        homePage.newMatrixBtn.click();
    }

    @When("The manager creates a title for the matrix")
    public void the_manager_creates_a_title_for_the_matrix() {
        homePage.matrixTitleField.sendKeys("Test Title");
    }

    @When("The manager adds requirements to the matrix")
    public void the_manager_adds_requirements_to_the_matrix() {
        homePage.userStoryField.sendKeys("Test Rule");
        homePage.addRequirementBtn.click();
    }

    @When("The manager saves the matrix")
    public void the_manager_saves_the_matrix() {
        homePage.createMatrixBtn.click();
    }

    @Then("An alert with a success message should appear")
    public void an_alert_with_a_success_message_should_appear() {
        new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(ElementNotInteractableException.class)
                .until(ExpectedConditions.alertIsPresent());

        String alertMsg = driver.switchTo().alert().getText();
        System.out.println(alertMsg);
        driver.switchTo().alert().accept();
        assertTrue(alertMsg.contains("Matrix with ID "));
    }

    // For scenario: Update defects
    @Given("The manager is on the matrix homepage")
    public void the_manager_is_on_the_matrix_homepage() {
        homePage.matricesLink.click();
        System.out.println(driver.getTitle());
    }

    @Given("The manager has selected the matrix")
    public void the_manager_has_selected_the_matrix() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(matrixPage.showAirlineMatrixBtn));
        matrixPage.showAirlineMatrixBtn.click();

    }

    @When("The manager adds a defect")
    public void the_manager_adds_a_defect() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(matrixPage.editAirlineFirstRuleBtn));
        matrixPage.editAirlineFirstRuleBtn.click();
        matrixPage.defectIDField.sendKeys("901");
        matrixPage.addDefectBtn.click();
    }

    @When("The manager confirms their changes")
    public void the_manager_confirms_their_changes() {
        matrixPage.saveBtn.click();
    }

    @Then("Then the matrix should saved")
    public void then_the_matrix_should_saved() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        String actualAlert = driver.switchTo().alert().getText();

        assertEquals("Matrix Saved", actualAlert);
        driver.switchTo().alert().accept();
    }


    // For scenario: Update test cases
    @When("The manager adds a Test Cases")
    public void the_manager_adds_a_test_cases() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(matrixPage.editAirlineFirstRuleBtn));
        matrixPage.editAirlineFirstRuleBtn.click();
        matrixPage.testCaseIDField.sendKeys("801");
        matrixPage.addTestCaseBtn.click();
    }

}
