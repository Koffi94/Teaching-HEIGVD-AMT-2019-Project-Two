# Created by nathanael at 1/19/20
Feature: Update a user
  # Enter feature description here

  Background:
    Given there is an Authentication API available
    Given there is a User API server available
    Given I have obtained an authorization token

  Scenario: Update a user lastname
    Given a user id
    And a new lastname
    When I PATCH the /users/userId endpoint
    Then I receive a 200 status code for the request

  Scenario: update a non existing user
    Given a wrong user ID
    And a new lastname
    When I try to update the user
    Then I receive a 500 status code for the request

  Scenario: delete a user
    And the id of the user to delete
    When I send a deletion request
    Then I receive a 200 status code for confirmation