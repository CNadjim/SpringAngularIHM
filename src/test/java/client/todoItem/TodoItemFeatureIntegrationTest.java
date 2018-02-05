package client.todoItem;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Profile;
import server.model.TodoItem;
import server.repository.TodoItemRepository;
import server.service.TodoItemService;

import javax.inject.Inject;
import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/feature/todoItem.feature")
@Profile(value = {"development", "production"})
public class TodoItemFeatureIntegrationTest {

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
    }

}