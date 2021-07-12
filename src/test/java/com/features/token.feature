@token
Feature: Generate login token

  @test-2 @token
  Scenario: As a test engineer I can get a login token
    When I create a post call to get login token
    Then I should see that a new token is generated successfully