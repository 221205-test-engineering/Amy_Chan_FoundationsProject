package dev.chan.steps;

import dev.chan.pages.*;
import dev.chan.runners.BugCatcherRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
public class DefectStatusImpl {

    public WebDriver driver = BugCatcherRunner.driver;
    public LoginPage loginPage = BugCatcherRunner.loginPage;
    public HomePage homePage = BugCatcherRunner.homePage;

    @Given("The tester is on the Home Page")
    public void the_tester_is_on_the_home_page() {
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=18");
        loginPage.usernameField.clear();
        loginPage.usernameField.sendKeys("ryeGuy");
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys("coolbeans");
        loginPage.loginBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(homePage.welcomeMsg));
    }
    @Then("The tester can can see only defects assigned to them")
    public void the_tester_can_can_see_only_defects_assigned_to_them() {
        // check if all defects are assigned to ryeGuy
        int totalDefects = homePage.allDefectList.size();

        for (int i = 1; i <= totalDefects; i++ ){
            WebElement li = driver.findElement(By.xpath("//*[@id=\"root\"]/ul/li["+ i +"]"));
            li.click();
        }

        List<WebElement> totalAssignedList = driver.findElements(By.xpath("//*[text()='ryeGuy']"));
        int totalAssigned = totalAssignedList.size();
        assertEquals(totalDefects, totalAssigned);
    }

    public String ogStatus = "";
    public String newStatus = "";
    @When("The tester changes to defect to any status")
    public void the_tester_changes_to_defect_to_any_status() {

        homePage.changeStatusBtn.click();
        ogStatus = homePage.originalStatus.getText();
        System.out.println("Original status:" + ogStatus);
        for(int i=0; i<homePage.statusBtns.size(); i++){
            WebElement statusBtn = homePage.statusBtns.get(i);
            if(!statusBtn.getText().equals(ogStatus)){
                newStatus = statusBtn.getText();
                System.out.println("New status:" + newStatus);
                statusBtn.click();
                break;
            }
        }

    }
    @Then("The tester should see the defect has a different status")
    public void the_tester_should_see_the_defect_has_a_different_status() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.refreshed
                (ExpectedConditions.textToBePresentInElement
                        (driver.findElement(By.xpath("(//p/b[2])[1]")),newStatus)));
        assertNotEquals(ogStatus, newStatus);
    }


}
