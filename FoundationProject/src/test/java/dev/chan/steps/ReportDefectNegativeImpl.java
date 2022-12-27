package dev.chan.steps;

import dev.chan.runners.BugCatcherRunner;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class ReportDefectNegativeImpl {
    public WebDriver driver = BugCatcherRunner.driver;

    @Then("No confirmation dialog appears")
    public void no_confirmation_dialog_appears() {
        boolean noDialog = false;
        try{
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        }catch  (TimeoutException e){
            noDialog = true;
            System.out.println(e.getMessage());
        }
        assertTrue(noDialog);
    }
}
