# Created by nathanael at 12/30/19
Feature: Retrieving users

  Background:
    Given there is a User Api server
    Given there is an Authentication API
    #Given I created a user
    Given I have logged in

  Scenario: Retrieving all users
    Given I have a token
    When I GET the users from the /users endpoint
    Then I receive a 200 OK status code

  Scenario: Retrieving a user
    Given I have a token
    When I GET a user from the /users endpoint
    Then I receive a 200 http status code