package com.featureSteps;

import com.User;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
    @Before
    public void beforeTest(Scenario scenario) {
        if (scenario.getSourceTagNames().contains("@token"))
            User.token = null;
    }

    @After
    public void afterTest() {
    }
}
