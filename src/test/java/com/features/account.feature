@account
Feature: Create a new account

  @test-1
  Scenario: As a test engineer I can create a new account
    When I create a new account using API call
    Then I should see that a new account is created successfully