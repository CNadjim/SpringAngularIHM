package client.login;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginSteps {

    private WebDriver driver;

    private String baseUrl;

    @Before
    public void before() {
        driver = new ChromeDriver();
        baseUrl = "http://localhost:8080/#/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        String currentWindowHandle = this.driver.getWindowHandle();
        ((JavascriptExecutor)this.driver).executeScript("alert('Debut des tests')");
        this.driver.switchTo().alert().accept();
        this.driver.switchTo().window(currentWindowHandle);
    }

    @After
    public void after() {
        if (driver != null) {
            driver.close();
        }
    }


    @Given("^I am at the home page$")
    public void iAmAtTheHomePage() throws Throwable {
        driver.get(baseUrl + "");
        driver.findElement(By.id("more")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("loginBtn")).click();
        Thread.sleep(1000);
    }

    @When("^I go to the login page$")
    public void iAmAtTheLoginPage() throws Throwable {
        Thread.sleep(1000);
    }

    @When("^I enter a valid name$")
    public void iEnterAName() throws Throwable {

        WebElement userName = driver.findElement(By.id("userNameInput"));
        userName.sendKeys("admin");
        Thread.sleep(1000);
    }

    @And("^I enter a valid password$")
    public void iEnterAPassword() throws Throwable {
        WebElement password = driver.findElement(By.id("passwordInput"));
        password.sendKeys("admin");
        Thread.sleep(1000);
    }

    @And("^I submit$")
    public void iSubmit() throws Throwable {
        driver.findElement(By.id("submitBtn")).click();
        Thread.sleep(1000);
    }

    @Then("^I should be at the profile page$")
    public void iShouldBeAtTheRegisteredPage() throws Throwable {
        Thread.sleep(2000);

    }
}