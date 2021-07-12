@prescription
Feature: Prescription feature

  Background:
    Given I have an access token to my account

  @test-3
  Scenario: As a test engineer I can create a new prescription
    When I create a post call to make new prescription
    Then I should see that a new prescription is generated successfully

  @test-4
  Scenario: As a test engineer I can view newly created prescription
    Given I have a prescription in my account
    When I create a get call to get new prescription
    Then I should verify that the prescription information is correct

  @test-5
  Scenario: As a test engineer I can update prescription details
    Given I have a prescription in my account
    When I make an update call to make update on prescription
    Then I should verify that the prescription information was updated successfully

  @test-6
  Scenario: As a test engineer I can delete my existing prescription
    Given I have a prescription in my account
    When I make an api delete call to delete the prescription
    Then I should verify that the prescription was successfully deleted