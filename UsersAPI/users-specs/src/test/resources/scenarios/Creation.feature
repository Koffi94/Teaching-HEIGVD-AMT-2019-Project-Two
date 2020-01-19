# Created by nathanael at 12/30/19
Feature: Creation of users

  Background:
    Given there is a User API server
    Given there is an Authentication API

  Scenario: create a user
    Given I have a user payload
    When I POST it to the /signup endpoint
    Then I receive a 201 status code

  Scenario: get a bearer token
    Given I have a username
    And a password
    When I log into the /login endpoint
    Then I receive a 200 status code
    And a bearer token

  Scenario: wrong credentials
    Given I have a username
    And the wrong password
    When I log into the /login endpoint
    Then I receive a 403 status code
