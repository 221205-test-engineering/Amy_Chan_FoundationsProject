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




    @When("The employee types in g8tor into the username input")
    public void the_employee_types_in_g8tor_into_the_username_input() {
        loginPage.usernameField.sendKeys("g8tor");
    }
    @When("The employee types in chomp!! into the password input")
    public void the_employee_types_in_chomp_into_the_password_input() {
        loginPage.passwordField.sendKeys("chomp!!");
    }

    @Then("The employee should see an alert saying they have the wrong password")
    public void the_employee_should_see_an_alert_saying_they_have_the_wrong_password() {
        new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10)) // how long in total to wait for the page/DOM to load
                .pollingEvery(Duration.ofSeconds(2)) // during those 10 seconds - we are looking at the DOM every 2 seconds
                .ignoring(ElementNotInteractableException.class) // if any exception occurs - we ignore it and keep polling
                .until(ExpectedConditions.alertIsPresent());
        String expectedMsg = "Wrong password for User";
        String alertMsg = driver.switchTo().alert().getText();
        System.out.println(alertMsg);
        assertEquals(expectedMsg, alertMsg);
        driver.switchTo().alert().dismiss();

    }

}
