package client.todoItem;


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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import server.model.TodoItem;
import server.repository.TodoItemRepository;
import server.service.TodoItemService;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.isNull;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoItemScenario {

    private WebDriver driver;

    private String baseUrl;

    @Before
    public void before() {
        driver = new ChromeDriver();
        baseUrl = "http://localhost:8080/#/";
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String currentWindowHandle = this.driver.getWindowHandle();
        ((JavascriptExecutor)this.driver).executeScript("alert('Debut des tests')");
        this.driver.switchTo().alert().accept();
        this.driver.switchTo().window(currentWindowHandle);
/*
        List<TodoItem> todoItems = this.todoItemRepository.findByUserName("user");
        for (TodoItem item : todoItems) {
            this.todoItemRepository.delete(item.getId());
        }*/
    }



    @After
    public void after() {
        if (driver != null) {
            driver.close();
        }
    }



    @Given("^I am already logged$")
    public void iAmAlreadyLogged() throws Throwable {
        driver.get(baseUrl + "login");
        WebElement userName = driver.findElement(By.id("userNameInput"));
        userName.sendKeys("user");
        WebElement password = driver.findElement(By.id("passwordInput"));
        password.sendKeys("password");
        driver.findElement(By.id("submitBtn")).click();
        pause();
    }


    @When("^I go to TodoItem Management page$")
    public void igoToTodoItemManagementPage() throws Throwable {
        driver.findElement(By.id("todoLink")).click();
        pause();
    }

    @And("^I enter a title to my todoItem$")
    public void iEnterATitleToMyTodoItem() throws Throwable {
        WebElement titleInputTodoItem = driver.findElement(By.id("titleInputTodoItem"));
        titleInputTodoItem.sendKeys("Titre de mon TodoItem");
        pause();
    }

    @And("^I enter a description to my todoItem$")
    public void iEnterADescriptionToMyTodoItem() throws Throwable {
        WebElement descriptionInputTodoItem = driver.findElement(By.id("descriptionInputTodoItem"));
        descriptionInputTodoItem.sendKeys("Description de mon TodoItem");
        pause();
    }

    @And("^I click on submit$")
    public void iClickOnSubmit() throws Throwable {
        driver.findElement(By.id("submitTodoItem")).click();
        pause();
    }

    @Then("^I should see my new todoItem added$")
    public void iShouldBeAtTheRegisteredPage() throws Throwable {
        WebElement todoItemCard = driver.findElement(By.className("todoItemCard"));
        assertThat(todoItemCard.isDisplayed(), equalTo(true));
        pause();
    }

    /*
      Scenario: I can delete a TodoItem.
    Given I am already logged
    When I go to TodoItem Management page
    And I click to remove my todoItem
    Then I will have to see my todoItem disappear
     */

    @And("^I add a TodoItem$")
    public void iAddATodoItem() throws Throwable {
        WebElement titleInputTodoItem = driver.findElement(By.id("titleInputTodoItem"));
        titleInputTodoItem.sendKeys("Titre de mon TodoItem");
        WebElement descriptionInputTodoItem = driver.findElement(By.id("descriptionInputTodoItem"));
        descriptionInputTodoItem.sendKeys("Description de mon TodoItem");
        driver.findElement(By.id("submitTodoItem")).click();
        pause();
    }

    @And("^I click to remove my todoItem$")
    public void iClickToRemoveMyTodoItem() throws Throwable {
        driver.findElement(By.name("deleteTodoItem")).click();
        pause();
    }

    @Then("^I will have to see no todoItem$")
    public void iWillHaveToSeeNoTodoItem() throws Throwable {
        pause();
        List<WebElement> todoItemCard = driver.findElements(By.className("todoItemCard"));
        assertThat(todoItemCard.isEmpty(), equalTo(true));
        pause();
    }



    public void pause(){
        try{
            Thread.sleep(500);
        } catch(InterruptedException ie){ }
    }
}