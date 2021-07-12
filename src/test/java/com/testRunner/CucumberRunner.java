package com.testRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@CucumberOptions(
        features = {"src/test/java/com/features"},
        glue = {"com/featureSteps"},
        tags = {"@token, @prescription, ~@account"},
        plugin = {"pretty", "json:target/cucumber.json"}
)

@RunWith(Cucumber.class)
public class CucumberRunner {

}