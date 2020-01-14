package ch.heigvd.amt.project2.api.endpoints;

import ch.heigvd.amt.project2.api.SignUpApi;
import ch.heigvd.amt.project2.api.model.UserManage;
import ch.heigvd.amt.project2.entities.UserEntity;
import ch.heigvd.amt.project2.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;

import static ch.heigvd.amt.project2.api.util.Transformer.toUserEntity;

@RestController
public class SignUpApiController implements SignUpApi {

    @Autowired
    UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public SignUpApiController() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public ResponseEntity<Void> createUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UserManage user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        UserEntity newUserEntity = toUserEntity(user);
        userRepository.save(newUserEntity);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/{id}")
                .buildAndExpand(newUserEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
