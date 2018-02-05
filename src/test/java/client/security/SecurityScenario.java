package client.security;


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

import static java.util.Objects.isNull;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import java.util.concurrent.TimeUnit;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityScenario {

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

    /* LOGIN TEST START */

    @Given("^I am at the login page$")
    public void iAmAtTheHomePage() throws Throwable {
        driver.get(baseUrl + "login");
        pause();
    }


    @When("^I enter a valid Admin name$")
    public void iEnterAValidAdminName() throws Throwable {
        WebElement userName = driver.findElement(By.id("userNameInput"));
        userName.sendKeys("admin");
        pause();
    }

    @And("^I enter a valid Admin password$")
    public void iEnterAValidAdminPassword() throws Throwable {
        WebElement password = driver.findElement(By.id("passwordInput"));
        password.sendKeys("admin");
        pause();
    }

    @And("^I enter a wrong Admin password$")
    public void iEnterAWrongAdminPassword() throws Throwable {
        WebElement password = driver.findElement(By.id("passwordInput"));
        password.sendKeys("wrongPassword");
        pause();
    }

    @And("^I submit$")
    public void iSubmit() throws Throwable {
        driver.findElement(By.id("submitBtn")).click();
        pause();
    }

    @Then("^I should be at the profile page$")
    public void iShouldBeAtTheRegisteredPage() throws Throwable {
        WebElement title = driver.findElement(By.id("profileMsg"));
        assertThat(title.getText(), equalTo("Profile Page"));
        pause();
    }

    @Then("^I should get a message error$")
    public void iShouldGetAMessageError() throws Throwable {
        WebElement errorMsg = driver.findElement(By.id("errorMsg"));
        assertEquals(errorMsg.isDisplayed(), true );
        pause();
    }


    @When("^I enter a valid User name$")
    public void iEnterAValidUserName() throws Throwable {
        WebElement userName = driver.findElement(By.id("userNameInput"));
        userName.sendKeys("user");
        pause();
    }

    @And("^I enter a valid User password$")
    public void iEnterAValidUserPassword() throws Throwable {
        WebElement password = driver.findElement(By.id("passwordInput"));
        password.sendKeys("password");
        pause();
    }

    @And("^I go to User listing Page$")
    public void iGoToUserListingPage() throws Throwable {
        driver.findElement(By.id("userLink")).click();
        pause();
    }

    @Then("^I should be redirect to 403 page$")
    public void iShouldBeRedirectTo403Page() throws Throwable {
        WebElement errorMsg = driver.findElement(By.id("homeMsg"));
        assertThat(errorMsg.getText(), equalTo("403 UNAUTHORIZED") );
        pause();
    }

    @Then("^I should see User List$")
    public void iShouldSeeUserList() throws Throwable {
        WebElement userTable = driver.findElement(By.id("userTable"));
        assertThat(userTable.isDisplayed(), equalTo(true) );
        pause();
    }



    @And("^I click into logout button$")
    public void iClickIntoLogoutButton() throws Throwable {

        driver.findElement(By.id("more")).click();
        pause();
        driver.findElement(By.id("logoutBtn")).click();
        pause();
    }

    @Then("^I should be redirect to home page$")
    public void iShouldBeRedirectToHomePage() throws Throwable {
        WebElement homeMsg = driver.findElement(By.id("homeMsg"));
        assertThat(homeMsg.getText(), equalTo("Hello Angular") );
        pause();
    }

    @And("^The session should be empty$")
    public void theSessionShouldBeEmpty() throws Throwable {
        driver.findElement(By.id("more")).click();
        pause();
        WebElement loginBtn = driver.findElement(By.id("loginBtn"));
        assertThat(isNull(loginBtn), equalTo(false) );
        pause();
    }


    public void pause(){

        try{
            Thread.sleep(500);
        } catch(InterruptedException ie){ }
    }
}