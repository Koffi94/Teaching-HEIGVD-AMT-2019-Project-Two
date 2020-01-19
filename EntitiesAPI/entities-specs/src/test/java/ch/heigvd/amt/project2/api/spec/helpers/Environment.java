package ch.heigvd.amt.project2.api.spec.helpers;


import ch.heigvd.amt.api.model.*;
import ch.heigvd.amt.project2.api.CinemaApi;
import ch.heigvd.amt.project2.api.MovieApi;
import ch.heigvd.amt.project2.api.ScreeningApi;
import com.auth0.jwt.JWT;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

/**
 * Based on the Environment class of Fruits, written by Olivier Liechti
 * @author Nathanaël Mizutani
 */
public class Environment {
    private CinemaApi cinemaApi = new CinemaApi();
    private ScreeningApi screeningApi = new ScreeningApi();
    private MovieApi movieApi = new MovieApi();

    private CinemaManage cinemaManage = new CinemaManage();
    private CinemaFull cinemaFull = new CinemaFull();
    private MovieManage movieManage = new MovieManage();
    private MovieFull movieFull = new MovieFull();
    private ScreeningFull screeningFull = new ScreeningFull();
    private ScreeningManage screeningManage = new ScreeningManage();

    private static final String SECRET = "woWkw9K?Y8U+dn7";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private String token;

    private String createTestToken(){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id", 0);
        jsonObj.put("role", "admin");
        String token = JWT.create()
                .withSubject(jsonObj.toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));

        return (TOKEN_PREFIX + token);
    }

    public Environment() throws IOException {
        Properties properties = new Properties();

        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("ch.heigvd.amt.project2.server.url");
        movieApi.getApiClient().setBasePath(url);
        screeningApi.getApiClient().setBasePath(url);
        cinemaApi.getApiClient().setBasePath(url);
        token = createTestToken();
    }

    public CinemaApi getCinemaApi() {
        return cinemaApi;
    }

    public ScreeningApi getScreeningApi() {
        return screeningApi;
    }

    public MovieApi getMovieApi() {
        return movieApi;
    }

    public CinemaManage getCinemaManage() {
        return cinemaManage;
    }

    public CinemaFull getCinemaFull() {
        return cinemaFull;
    }

    public MovieManage getMovieManage() {
        return movieManage;
    }

    public MovieFull getMovieFull() {
        return movieFull;
    }

    public ScreeningFull getScreeningFull() {
        return screeningFull;
    }

    public ScreeningManage getScreeningManage() {
        return screeningManage;
    }

    public String getToken() {
        return token;
    }
}
