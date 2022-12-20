package dev.chan.runners;

import dev.chan.pages.LoginPage;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/dev/chan/features", glue = "dev.chan.steps")
public class BugCatcherRunner {
    public static WebDriver driver;

    public static LoginPage loginPage;

    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
    }

    @AfterClass
    public static void teardown() {
        driver.quit();
    }
}
