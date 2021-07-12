package com.featureSteps;

import com.EnvData;
import com.User;
import com.api.factories.PrescriptionFactory;
import com.api.factories.TokenFactory;
import com.api.models.PrescriptionModel;
import com.github.javafaker.Faker;
import com.utils.Helpers;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

public class PrescriptionSteps {
    private Response prescriptionRes, getPrescrionRes;
    private String updateNotes = new Faker().funnyName().name();

    @Given("^I have an access token to my account$")
    public void iHaveAnAccessTokenToMyAccount() {
        if (User.token == null) {
            Response response = new TokenFactory().create();
            Assert.assertEquals(200, response.statusCode());
            User.token = Helpers.parseJson(response).get("token").getAsString();
        }
    }

    @Given("^I have a prescription in my account$")
    public void iHaveAPrescriptionInMyAccount() {
        Response response = new PrescriptionFactory().create();
        Assert.assertEquals(201, response.getStatusCode());
        EnvData.setPrescriptionId(Helpers.parseJson(response).get("id").getAsInt());
        EnvData.setDoctorId(Helpers.parseJson(response).get("doctor").getAsJsonObject().get("id").getAsInt());
    }

    @When("^I create a post call to make new prescription$")
    public void iCreateAPostCallToMakeNewPrescription() {
        prescriptionRes = new PrescriptionFactory().create();
        Assert.assertEquals(201, prescriptionRes.getStatusCode());
        EnvData.setPrescriptionId(Helpers.parseJson(prescriptionRes).get("id").getAsInt());
        EnvData.setDoctorId(Helpers.parseJson(prescriptionRes).get("doctor").getAsJsonObject().get("id").getAsInt());
    }

    @Then("^I should see that a new prescription is generated successfully$")
    public void iShouldSeeThatANewPrescriptionIsGeneratedSuccessfully() {
        Assert.assertEquals(773, Helpers.parseJson(prescriptionRes).get("patient").getAsInt());
    }

    @When("^I create a get call to get new prescription$")
    public void iCreateAGetCallToGetNewPrescription() {
        getPrescrionRes = new PrescriptionFactory().get(EnvData.getPrescriptionId() + "/");
        Assert.assertEquals(200, getPrescrionRes.getStatusCode());
    }

    @Then("^I should verify that the prescription information is correct$")
    public void iShouldVerifyThatThePrescriptionInformationIsCorrect() {
        Assert.assertEquals(EnvData.getDoctorId(), Helpers.parseJson(getPrescrionRes).get("doctor").getAsJsonObject().get("id").getAsInt());
    }

    @When("^I make an update call to make update on prescription$")
    public void iMakeAnUpdateCallToMakeUpdateOnPrescription() {
        PrescriptionModel prescription = (PrescriptionModel) Helpers.getModel("prescription.json", PrescriptionModel.class);
        prescription.additional_notes = updateNotes;
        String payload = prescription.toJson();
        new PrescriptionFactory().update(payload, EnvData.getPrescriptionId() + "/");
    }

    @Then("^I should verify that the prescription information was updated successfully$")
    public void iShouldVerifyThatThePrescriptionInformationWasUpdatedSuccessfully() {
        Response response = new PrescriptionFactory().get(EnvData.getPrescriptionId() + "/");
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals(updateNotes, Helpers.parseJson(response).get("additional_notes").getAsString());
    }

    @When("^I make an api delete call to delete the prescription$")
    public void iMakeAnApiDeleteCallToDeleteThePrescription() {
        Response response = new PrescriptionFactory().delete(EnvData.getPrescriptionId() + "/");
        Assert.assertEquals(204, response.statusCode());
    }

    @Then("^I should verify that the prescription was successfully deleted$")
    public void iShouldVerifyThatThePrescriptionWasSuccessfullyDeleted() {
        Response response = new PrescriptionFactory().get(EnvData.getPrescriptionId() + "/");
        Assert.assertEquals(404, response.statusCode());
        Assert.assertEquals("Not found.", Helpers.parseJson(response).get("detail").getAsString());
    }
}
