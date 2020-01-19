package ch.heigvd.amt.project2.api.spec.steps;

import ch.heigvd.amt.project2.ApiException;
import ch.heigvd.amt.project2.ApiResponse;
import ch.heigvd.amt.project2.api.UserApi;
import ch.heigvd.amt.project2.api.AuthenticationApi;
import ch.heigvd.amt.project2.api.model.UserAuth;
import ch.heigvd.amt.project2.api.model.UserFull;
import ch.heigvd.amt.project2.api.spec.helpers.Environment;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ReadSteps {

    private Environment environment;
    private UserApi api;
    private AuthenticationApi authApi;
    private String token;

    UserAuth userAuth;
    UserFull user;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    static final String USERNAME = "testUser";
    static final String PASSWORD = "testPasswd";
    static final int USER_ID = 1;
    static final int PAGE_ID = 1;
    static final int PAGE_SIZE = 10;

    public ReadSteps(Environment environment){
        this.environment = environment;
        this.api = environment.getApi();
        this.authApi = environment.getAuthenticationApi();
        this.userAuth = environment.getUserAuth();
        this.token = environment.getToken();
    }

    @Given("^there is a User Api server$")
    public void there_a_User_API_server() throws Throwable {
        assertNotNull(api);
    }

    @When("^I GET the users from the /users endpoint$")
    public void i_GET_the_users_from_the_users_endpoint() throws Throwable {
        try{
            api.getApiClient().setApiKey(token);
            lastApiResponse = api.getUsersWithHttpInfo(PAGE_ID, PAGE_SIZE);
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

    @When("^I GET a user from the /users endpoint$")
    public void i_GET_a_user_from_the_users_endpoint() throws Throwable {
        try{
            api.getApiClient().setApiKey(token);
            lastApiResponse = api.getUserWithHttpInfo(USER_ID);
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

    @Then("^I receive a (\\d+) OK status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }

    @Then("^I receive a (\\d+) http status code$")
    public void i_receive_a_http_status_code(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }

    @Given("^I have logged in$")
    public void i_have_logged_in() throws Throwable {
        userAuth.setUsername(USERNAME);
        userAuth.setPassword(PASSWORD);
        try{
            lastApiResponse = authApi.authenticateUserWithHttpInfo(userAuth);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiResponse = null;
            lastApiCallThrewException = true;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Given("^I have a token$")
    public void i_have_a_token() throws Throwable {
        Map<String, List<String>> headers = lastApiResponse.getHeaders();
        List<String> authorization = headers.get("Authorization");
        this.token = authorization.get(0);
        assertNotNull(this.token);
    }

    @When("^I GET a user that do not exist from the /users endpoint$")
    public void i_GET_a_user_that_do_not_exist_from_the_users_endpoint() throws Throwable {
        try{
            api.getApiClient().setApiKey(token);
            lastApiResponse = api.getUserWithHttpInfo(0);
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
