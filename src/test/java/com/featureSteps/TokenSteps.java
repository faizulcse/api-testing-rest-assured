package com.featureSteps;

import com.api.factories.TokenFactory;
import com.utils.Helpers;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

public class TokenSteps {
    Response response;

    @When("^I create a post call to get login token$")
    public void iCreateAPostCallToGetLoginToken() {
        response = new TokenFactory().create();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Then("^I should see that a new token is generated successfully$")
    public void iShouldSeeThatANewTokenIsGeneratedSuccessfully() {
        String newToken = Helpers.parseJson(response).get("token").getAsString();
        Assert.assertEquals(40, newToken.length());
    }
}
