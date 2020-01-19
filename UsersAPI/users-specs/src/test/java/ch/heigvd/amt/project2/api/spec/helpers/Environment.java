package ch.heigvd.amt.project2.api.spec.helpers;

import ch.heigvd.amt.project2.api.AuthenticationApi;
import ch.heigvd.amt.project2.api.UserApi;
import ch.heigvd.amt.project2.api.model.UserAuth;
import ch.heigvd.amt.project2.api.model.UserFull;
import ch.heigvd.amt.project2.api.model.UserManage;

import java.io.IOException;
import java.util.Properties;

/**
 * Based on the Environment class of Fruits, written by Olivier Liechti
 * @author Nathanaël Mizutani
 */
public class Environment {

    private UserApi api = new UserApi();
    private UserFull userFull = new UserFull();
    private UserManage userManage = new UserManage();
    private UserAuth userAuth = new UserAuth();
    private AuthenticationApi authenticationApi = new AuthenticationApi();
    private String token;

    public Environment() throws IOException {
        Properties properties = new Properties();

        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("ch.heigvd.amt.project2.server.url");
        api.getApiClient().setBasePath(url);
    }

    public UserApi getApi(){
        return api;
    }

    public UserFull getUserFull(){
        return userFull;
    }

    public UserManage getUserManage() {
        return userManage;
    }

    public UserAuth getUserAuth() {
        return userAuth;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }

    public AuthenticationApi getAuthenticationApi(){
        return authenticationApi;
    }

}
