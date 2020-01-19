# Created by nathanael at 1/19/20
Feature: Operations about movies
  # Enter feature description here

  Background:
    Given there is an entities API
    Given there is a user API
    Given I am logged in
    Given I have an authentication token

  Scenario: Retrieve a list of movies
    When I request a list of movies from the /movies endpoint
    Then I receive a 200 status code

  Scenario: Get a movie
    Given I have a movie ID
    When I request that movie from the /movie/movie_id endpoint
    Then I receive a 200 status code
    And a response

  Scenario: Get a non existing movie
    Given I have the ID of a movie that is not in the database
    When I request that movie that is not there from the /movie/movie_id endpoint
    Then I receive a 404 status code

    Scenario: Add a movie to the database
      Given I have a new movie
      When I send this movie to the /movie/movie_id endpoint
      Then I receive a 200 status code

  Scenario: Delete a movie
    Given I have the ID of the movie I want to delete
    When I send DELETE to the /movie/movie_id endpoint
    Then I receive a 200 status code