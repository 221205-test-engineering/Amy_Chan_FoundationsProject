package dev.chan.steps;

import dev.chan.pages.LoginPage;
import dev.chan.runners.BugCatcherRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;


public class LoginPositiveImpl {

    public WebDriver driver = BugCatcherRunner.driver;
    public LoginPage loginPage = BugCatcherRunner.loginPage;

    @Given("The employee is on the login page")
    public void the_employee_is_on_the_login_page() {

        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=18");

    }

    @When("The employee types {string} into username input")
    public void the_employee_types_into_username_input(String username) {
        loginPage.usernameField.sendKeys("g8tor");
    }

    @When("The employee types {string} into password input")
    public void the_employee_types_into_password_input(String password) {
        loginPage.passwordField.sendKeys("chomp!");
    }

    @When("The employee clicks on the login button")
    public void the_employee_clicks_on_the_login_button() throws InterruptedException {
        loginPage.loginBtn.click();
        Thread.sleep( 5000);
    }

    @Then("the employee should be on the {string} page")
    public void the_employee_should_be_on_the_role_page (String role) {

        new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10)) // how long in total to wait for the page/DOM to load
                .pollingEvery(Duration.ofSeconds(2)) // during those 10 seconds - we are looking at the DOM every 2 seconds
                .ignoring(ElementNotInteractableException.class) // if any exception occurs - we ignore it and keep polling
                .until(ExpectedConditions.visibilityOf(loginPage.welcomeMsg));
        assertEquals("Manager Home", driver.getTitle());
    }

    @Then("The employee should see their name {string} {string} on the home page")
    public void the_employee_should_see_their_name_fname_lname_on_the_home_page (String fname, String lname) {
        assertEquals("Welcome Patty Pastiche", loginPage.welcomeMsg.getText());
    }

}

