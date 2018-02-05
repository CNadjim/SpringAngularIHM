package client.security;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.Profile;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/feature/security.feature")
@Profile(value = {"development", "production"})
public class SecurityFeatureIntegrationTest {

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
    }

}