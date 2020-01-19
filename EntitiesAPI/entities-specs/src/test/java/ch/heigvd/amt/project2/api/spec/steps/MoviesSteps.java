package ch.heigvd.amt.project2.api.spec.steps;

import ch.heigvd.amt.api.model.MovieManage;
import ch.heigvd.amt.project2.ApiException;
import ch.heigvd.amt.project2.ApiResponse;
import ch.heigvd.amt.project2.JSON;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import ch.heigvd.amt.project2.api.MovieApi;
import ch.heigvd.amt.project2.api.spec.helpers.Environment;
import okhttp3.*;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class MoviesSteps {

    private MovieApi movieApi;
    private MovieManage movieManage;
    private String token;
    private int movie_id;


    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public MoviesSteps(Environment environment){
        movieApi = environment.getMovieApi();
        movieManage = environment.getMovieManage();
        token = environment.getToken();
    }

    @Given("^there is a Movie API$")
    public void there_is_an_entities_API() throws Throwable {
        assertNotNull(movieApi);
    }

    @Given("^there is a user API$")
    public void there_is_a_user_API() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^I have an authentication token$")
    public void i_have_an_authentication_token() throws Throwable {
        assertNotNull(token);
    }

    @When("^I request a list of movies from the /movies endpoint$")
    public void i_request_a_list_of_movies_from_the_movies_endpoint() throws Throwable {
        try{
            movieApi.getApiClient().setApiKey(token);
            lastApiResponse = movieApi.getMoviesWithHttpInfo(1,5);
            lastApiException = null;
            lastApiCallThrewException = false;
            lastStatusCode = lastApiResponse.getStatusCode();
        }catch (ApiException e){
            lastApiResponse = null;
            lastApiException = e;
            lastApiCallThrewException = true;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }

    @Given("^I have a movie ID$")
    public void i_have_a_movie_ID() throws Throwable {
       movie_id = 1;
    }

    @When("^I request that movie from the /movie/movie_id endpoint$")
    public void i_request_that_movie_from_the_movie_movie_id_endpoint() throws Throwable {
        try{
            lastApiResponse = movieApi.getMovieWithHttpInfo(movie_id);
            lastApiException = null;
            lastApiCallThrewException = false;
            lastStatusCode = lastApiResponse.getStatusCode();
        }catch (ApiException e){
            lastApiResponse = null;
            lastApiException = e;
            lastApiCallThrewException = true;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^a response$")
    public void a_response() throws Throwable {
        assertNotNull(lastApiResponse.getData());
    }

    @Given("^I have the ID of a movie that is not in the database$")
    public void i_have_the_ID_of_a_movie_that_is_not_in_the_database() throws Throwable {
        movie_id = 0;
    }

    @When("^I request that movie that is not there from the /movie/movie_id endpoint$")
    public void i_request_that_movie_that_is_not_there_from_the_movie_movie_id_endpoint() throws Throwable {
        try{
            lastApiResponse = movieApi.getMovieWithHttpInfo(movie_id);
            lastApiException = null;
            lastApiCallThrewException = false;
            lastStatusCode = lastApiResponse.getStatusCode();
        }catch (ApiException e){
            lastApiResponse = null;
            lastApiException = e;
            lastApiCallThrewException = true;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Given("^I have a new movie$")
    public void i_have_a_new_movie() throws Throwable {
        movieManage.setTitle("Cucumber Menace");
        movieManage.setCategory("Horror");
        movieManage.setReleaseDate("2020");
    }

    @When("^I send this movie to the /movie/movie_id endpoint$")
    public void i_send_this_movie_to_the_movie_movie_id_endpoint() throws Throwable {
        try{
            lastApiResponse = movieApi.addMovieWithHttpInfo(movieManage);
            lastApiException = null;
            lastApiCallThrewException = false;
            lastStatusCode = lastApiResponse.getStatusCode();
        }catch (ApiException e){
            lastApiResponse = null;
            lastApiException = e;
            lastApiCallThrewException = true;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Given("^I have the ID of the movie I want to delete$")
    public void i_have_the_ID_of_the_movie_I_want_to_delete() throws Throwable {
        movie_id = 2;
    }

    @When("^I send DELETE to the /movie/movie_id endpoint$")
    public void i_send_DELETE_to_the_movie_movie_id_endpoint() throws Throwable {
        try{
            lastApiResponse = movieApi.deleteMovieWithHttpInfo(movie_id);
            lastApiException = null;
            lastApiCallThrewException = false;
            lastStatusCode = lastApiResponse.getStatusCode();
        }catch (ApiException e){
            lastApiResponse = null;
            lastApiException = e;
            lastApiCallThrewException = true;
            lastStatusCode = lastApiException.getCode();
        }
    }
}
