package dev.chan.runners;

import dev.chan.pages.HomePage;
import dev.chan.pages.LoginPage;
import dev.chan.pages.MatrixPage;
import io.cucumber.java.it.Ma;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/dev/chan/features", glue = "dev.chan.steps")
public class BugCatcherRunner {
    public static WebDriver driver;

    public static LoginPage loginPage;
    public static HomePage homePage;

    public static MatrixPage matrixPage;

    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        matrixPage = new MatrixPage(driver);
    }

    @AfterClass
    public static void teardown() {
        driver.quit();
    }
}
