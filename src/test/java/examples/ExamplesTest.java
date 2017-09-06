package examples;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.*;

@RunWith(Karate.class)
@CucumberOptions(features = "src/test/java/examples/characters/characters.feature",
         monochrome = false, format = {"pretty", "json:target/cucumber.json"})

public class ExamplesTest {
    // this will run all *.feature files that exist in sub-directories
    // refer to https://github.com/intuit/karate#naming-conventions
    // for folder-structure recommendations and naming conventions
}