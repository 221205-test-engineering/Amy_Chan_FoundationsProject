package dev.chan.steps;

import dev.chan.pages.LoginPage;
import dev.chan.runners.BugCatcherRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class LoginNegativeImpl {

    public WebDriver driver = BugCatcherRunner.driver;
    public LoginPage loginPage = BugCatcherRunner.loginPage;



    // Steps for scenario: correct username, wrong password
    @When("The employee types in g8tor into the username input")
    public void the_employee_types_in_g8tor_into_the_username_input() {
        loginPage.usernameField.clear();
        loginPage.usernameField.sendKeys("g8tor");
    }
    @When("The employee types in chomp!! into the password input")
    public void the_employee_types_in_chomp_into_the_password_input() {
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("chomp!!");
    }

    @Then("The employee should see an alert saying they have the wrong password")
    public void the_employee_should_see_an_alert_saying_they_have_the_wrong_password() {
        new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(ElementNotInteractableException.class)
                .until(ExpectedConditions.alertIsPresent());
        String expectedMsg = "Wrong password for User";
        String alertMsg = driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();

//        System.out.println(alertMsg);
        assertEquals(expectedMsg, alertMsg);

    }

    // Steps for scenario: wrong username, correct password
    @When("The employee types in sicEmDawgs into the username input")
    public void the_employee_types_in_sic_em_dawgs_into_the_username_input() {
        loginPage.usernameField.clear();
        loginPage.usernameField.sendKeys("sicEmDawgs");
    }
    @When("The employee types in natchamps into the password input")
    public void the_employee_types_in_natchamps_into_the_password_input() {
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("natchamps");
    }
    @Then("The employee should see an alert saying no user with that username found")
    public void the_employee_should_see_an_alert_saying_no_user_with_that_username_found() {
        new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(ElementNotInteractableException.class)
                .until(ExpectedConditions.alertIsPresent());

        String expectedMsg = "Username not found";
        String alertMsg = driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();

//        System.out.println(alertMsg);
        assertEquals(expectedMsg, alertMsg);

    }


}
