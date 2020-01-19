package ch.heigvd.amt.project2.api.endpoints;

import ch.heigvd.amt.project2.api.LoginApi;
import ch.heigvd.amt.project2.api.model.UserAuth;
import ch.heigvd.amt.project2.entities.UserEntity;
import ch.heigvd.amt.project2.repositories.UserRepository;
import com.auth0.jwt.JWT;
import io.swagger.annotations.ApiParam;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static ch.heigvd.amt.project2.security.SecurityConstants.*;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@RestController
public class LoginApiController implements LoginApi {

    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginApiController() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public ResponseEntity<Void> authenticateUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UserAuth user) {
        UserEntity userEntity = userRepository.findByUsername(user.getUsername());
        if(userEntity != null && bCryptPasswordEncoder.matches(user.getPassword(), userEntity.getPassword())) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("id", userEntity.getUser_id());
            jsonObj.put("role", userEntity.getRole());
            String token = JWT.create()
                    .withSubject(jsonObj.toString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(HMAC512(SECRET.getBytes()));

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(AUTHORIZATION_HEADER, TOKEN_PREFIX + token);
            return ResponseEntity.ok().headers(httpHeaders).build();
        }
        return ResponseEntity.status(403).build();
    }
}
