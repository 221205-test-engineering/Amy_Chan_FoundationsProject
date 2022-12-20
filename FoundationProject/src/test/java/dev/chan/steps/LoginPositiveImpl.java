package dev.chan.steps;

import dev.chan.pages.LoginPage;
import dev.chan.runners.BugCatcherRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class LoginPositiveImpl {

    public WebDriver driver = BugCatcherRunner.driver;
    public LoginPage loginPage = BugCatcherRunner.loginPage;

    @Given("The employee is on the login page")
    public void the_employee_is_on_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("The employee types {string} into username input")
    public void the_employee_types_into_username_input(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("The employee types {string} into password input")
    public void the_employee_types_into_password_input(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("The employee clicks on the login button")
    public void the_employee_clicks_on_the_login_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the employee should be on the <role> page")
    public void the_employee_should_be_on_the_role_page () {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("The employee should see their name <fname> <lname> on the home page")
    public void the_employee_should_see_their_name_fname_lname_on_the_home_page () {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}

