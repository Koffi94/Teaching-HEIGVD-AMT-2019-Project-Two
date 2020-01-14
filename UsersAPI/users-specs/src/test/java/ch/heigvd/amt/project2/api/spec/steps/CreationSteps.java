package ch.heigvd.amt.project2.api.spec.steps;

import ch.heigvd.amt.project2.ApiException;
import ch.heigvd.amt.project2.ApiResponse;
import ch.heigvd.amt.project2.api.AuthenticationApi;
import ch.heigvd.amt.project2.api.UserApi;
import ch.heigvd.amt.project2.api.model.User;
import ch.heigvd.amt.project2.api.spec.helpers.Environment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.swagger.annotations.Api;
import okhttp3.*;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

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
    private User user;
    private String token;

    private final String NEW_PASSWORD = "test2";
    private final String URL = "http://localhost:8080/api/login";

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public CreationSteps(Environment environment){
        this.environment = environment;
        this.api = environment.getApi();
        this.user = environment.getUser();
        this.token = environment.getToken();
        this.authApi = environment.getAuthenticationApi();
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
        user.setId(null);
        user.setEmail("userJohn@mail.com");
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setUsername("Test2");
        user.setPassword("test");
        user.setRole("admin");
    }

    @When("^I POST it to the /signup endpoint$")
    public void i_POST_it_to_the_signup_endpoint() throws  Throwable {
        try{
            lastApiResponse = authApi.createUserWithHttpInfo(user);
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
        user.setUsername("Test2");
        //assertNotNull(user.getUsername());
    }
    @Given("^a password$")
    public void a_password() throws Throwable {
        user.setPassword("test");
        //assertNotNull(user.getPassword());
    }
    @When("^I log into the /login endpoint$")
    public void i_log_into_the_login_endpoint() throws Throwable {
        // https://www.baeldung.com/okhttp-post
        OkHttpClient client = new OkHttpClient();
        try{
            JsonObject payload = new JsonObject();
            payload.addProperty("username", user.getUsername());
            payload.addProperty("password", user.getPassword());

            RequestBody body = RequestBody.create(MediaType.parse("application/json"), String.valueOf(payload));

            Request request = new Request.Builder()
                    .url(URL)
                    .post(body)
                    .build();

            Call call = client.newCall(request);
            Response response = call.execute();

            environment.setToken(response.header("Authorization"));
            lastStatusCode = response.code();

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    @Then("^a bearer token$")
    public void a_bearer_token() throws Throwable {
        assertNotNull(environment.getToken());
    }

    @Given("^I have a new password$")
    public void i_have_a_new_password() throws Throwable {
        assertNotNull(NEW_PASSWORD); //FIXME test not really useful ?
    }

    @And("^a user id$")
    public void a_user_id() {
        assertNotNull(user.getId());
    } //FIXME

    @When("^I PATCH the /users/userId endpoint$")
    public void i_PATCH_the_users_userId_endpoint() throws Throwable {
        try{
            lastApiResponse = api.changePasswordWithHttpInfo(user.getId(), NEW_PASSWORD);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();

        }catch (ApiException e){
            lastApiResponse = null;
            lastApiCallThrewException = true;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

}
