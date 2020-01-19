package ch.heigvd.amt.project2.api.spec.steps;

import ch.heigvd.amt.project2.ApiException;
import ch.heigvd.amt.project2.ApiResponse;
import ch.heigvd.amt.project2.api.AuthenticationApi;
import ch.heigvd.amt.project2.api.UserApi;
import ch.heigvd.amt.project2.api.model.UserAuth;
import ch.heigvd.amt.project2.api.model.UserManage;
import ch.heigvd.amt.project2.api.spec.helpers.Environment;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UpdateDeleteSteps {

    private Environment environment;
    private UserApi api;
    private AuthenticationApi authApi;
    private String token;
    private int user_id;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    static final String USERNAME = "testUser";
    static final String PASSWORD = "testPasswd";

    UserAuth userAuth;
    UserManage userManage;

    public UpdateDeleteSteps(Environment environment){
        this.environment = environment;
        this.api = environment.getApi();
        this.authApi = environment.getAuthenticationApi();
        this.userAuth = environment.getUserAuth();
        this.token = environment.getToken();
        this.userManage = environment.getUserManage();
    }

    @Given("^there is an Authentication API available$")
    public void there_is_an_Authentication_API_available() throws Throwable {
        assertNotNull(authApi);
    }
    @Given("^there is a User API server available$")
    public void there_is_a_User_API_server_available() throws Throwable {
        assertNotNull(api);
    }

    @Given("^I have obtained an authorization token$")
    public void i_have_obtained_an_authorization_token() throws Throwable {
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

        Map<String, List<String>> headers = lastApiResponse.getHeaders();
        List<String> authorization = headers.get("Authorization");
        this.token = authorization.get(0);
        assertNotNull(this.token);
    }

    @Given("^a user id$")
    public void a_user_id() throws Throwable {
        this.user_id = 1;
    }

    @Given("^a new lastname$")
    public void a_new_lastname() throws Throwable {
        userManage.setEmail("user@gmail.com");
        userManage.setFirstname("John");
        userManage.setLastname("Yoshi");
        userManage.setUsername(USERNAME);
        userManage.setPassword(PASSWORD);
        userManage.setRole("admin");

    }

    @When("^I PATCH the /users/userId endpoint$")
    public void i_PATCH_the_users_userId_endpoint() throws Throwable {
        try{
            api.getApiClient().setApiKey(token);
            lastApiResponse = api.editUserWithHttpInfo(user_id, userManage);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        }catch (ApiException e) {
            lastApiResponse = null;
            lastApiCallThrewException = true;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^I receive a (\\d+) status code for the request$")
    public void i_receive_a_status_code_for_the_request(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }

    @Given("^a wrong user ID$")
    public void a_wrong_user_ID() throws Throwable {
        user_id = 0;
    }

    @When("^I try to update the user$")
    public void i_try_to_update_the_user() throws Throwable {
        try {
            api.getApiClient().setApiKey(token);
            lastApiResponse = api.editUserWithHttpInfo(user_id, userManage);
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

    @Given("^the id of the user to delete$")
    public void the_id_of_the_user_to_delete() throws Throwable {
        user_id = 1;
    }
    @When("^I send a deletion request$")
    public void i_send_a_deletion_request() throws Throwable {
        try{
            api.getApiClient().setApiKey(token);
            lastApiResponse = api.deleteUserWithHttpInfo(user_id);
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

    @Then("^I receive a (\\d+) status code for confirmation$")
    public void i_receive_a_status_code_for_confirmation(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }
}