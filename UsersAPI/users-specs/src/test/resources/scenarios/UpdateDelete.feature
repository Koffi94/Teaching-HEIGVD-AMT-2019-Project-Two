# Created by nathanael at 1/19/20
Feature: Update a user
  # Enter feature description here

  Background:
    Given there is an Authentication API
    Given there is a User API server

  Scenario: Update a user lastname
    Given I have obtained an authorization token
    And a user id
    And a new lastname
    When I PATCH the /users/userId endpoint
    Then I receive a 200 status code for the request

  Scenario: update a non existing user
    Given I have obtained an authorization token
    And a wrong user ID
    And a new lastname
    When I try to update the user
    Then I receive a 500 status code for the request