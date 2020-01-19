package ch.heigvd.amt.project2.api.spec.steps;

import ch.heigvd.amt.project2.ApiException;
import ch.heigvd.amt.project2.ApiResponse;
import ch.heigvd.amt.project2.api.AuthenticationApi;
import ch.heigvd.amt.project2.api.UserApi;
import ch.heigvd.amt.project2.api.model.UserAuth;
import ch.heigvd.amt.project2.api.model.UserFull;
import ch.heigvd.amt.project2.api.model.UserManage;
import ch.heigvd.amt.project2.api.spec.helpers.Environment;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Based on the CreationSteps class of Fruits, written by Olivier Liechti
 * @author Nathanaël Mizutani
 */
public class CreationSteps {

    private Environment environment;
    private UserApi api;
    private AuthenticationApi authApi;
    private UserFull userFull;
    private UserManage userManage;
    private UserAuth userAuth;
    private String token;

    private final String NEW_PASSWORD = "test2";
    private final String PASSWORD = "testPasswd";
    private final String USERNAME = "testUser";

    private String testUsername = USERNAME;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;
    // To be able to run the test multiples times without cleaning the DB after each run
    private Random rnd = new Random();

    public CreationSteps(Environment environment){
        this.environment = environment;
        this.api = environment.getApi();
        this.userManage = environment.getUserManage();
        this.userAuth = environment.getUserAuth();
        this.token = environment.getToken();
        this.authApi = environment.getAuthenticationApi();

        rnd.setSeed(System.currentTimeMillis());
    }

    @Given("^there is a User API server$")
    public void there_is_a_User_API_server() throws Throwable {
        assertNotNull(api);
    }

    @Given("^there is an Authentication API$")
    public void there_is_an_Authentication_API(){
        assertNotNull(authApi);
    }

    @Given("^I have a user payload$")
    public void i_have_a_user_payload() throws Throwable {
        //int number = rnd.nextInt();

        //userManage.setEmail("user" + number +"@mail.com");
        userManage.setEmail("user@gmail.com");
        userManage.setFirstname("John");
        userManage.setLastname("Doe");
        //userManage.setUsername(this.testUsername + number);
        userManage.setUsername(this.testUsername);
        userManage.setPassword(PASSWORD);
        userManage.setRole("admin");
    }

    @When("^I POST it to the /signup endpoint$")
    public void i_POST_it_to_the_signup_endpoint() throws  Throwable {
        try{
            lastApiResponse = authApi.createUserWithHttpInfo(userManage);
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

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }

    @Given("^I have a username$")
    public void i_have_a_username() throws Throwable {
        userAuth.setPassword(PASSWORD);
    }
    @Given("^a password$")
    public void a_password() throws Throwable {
        userAuth.setUsername(this.testUsername);
    }
    @When("^I log into the /login endpoint$")
    public void i_log_into_the_login_endpoint() throws Throwable {
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
    @Then("^a bearer token$")
    public void a_bearer_token() throws Throwable {
        Map<String, List<String>> headers = lastApiResponse.getHeaders();
        List<String> token = headers.get("Authorization");
        assertNotNull(token.get(0));
    }

    @Given("^the wrong password$")
    public void the_wrong_password() throws Throwable {
        userAuth.setPassword("wrong");
    }

    @Given("^I have a new password$")
    public void i_have_a_new_password() throws Throwable {
        assertNotNull(NEW_PASSWORD); //FIXME test not really useful ?
    }

    @And("^a user id$")
    public void a_user_id() {
        assertNotNull(userFull.getId());
    } //FIXME

    @When("^I PATCH the /users/userId endpoint$")
    public void i_PATCH_the_users_userId_endpoint() throws Throwable {
        /*try{
            lastApiResponse = api.getUserWithHttpInfo(1);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();

        }catch (ApiException e){
            lastApiResponse = null;
            lastApiCallThrewException = true;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }*/
    }

}
