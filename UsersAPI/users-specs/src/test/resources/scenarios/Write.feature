# Created by nathanael at 12/30/19
Feature: Creation of users

  Background:
    Given there a User API server

  Scenario: create a user
    Given I have a user payload
    # TODO modify endpoint to /sign-up
    When I POST it to the /signup endpoint
    Then I receive a 201 status code

  Scenario: Update a user password
    Given I have a new password
    And a user id
    When I PATCH the /users/userId endpoint
    Then I receive a 200 status code