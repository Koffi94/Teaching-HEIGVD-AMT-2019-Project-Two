package ch.heigvd.amt.project2.api.endpoints;

import ch.heigvd.amt.project2.api.UserApi;
import ch.heigvd.amt.project2.api.model.UserManage;
import ch.heigvd.amt.project2.entities.UserEntity;
import ch.heigvd.amt.project2.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

import static ch.heigvd.amt.project2.api.util.Transformer.toUserManage;

@RestController
public class UserApiController implements UserApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserApiController() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public ResponseEntity<Void> deleteUser(@ApiParam(value = "The user id that needs to be deleted",required=true) @PathVariable("user_id") Integer userId) {
        if(httpServletRequest.getAttribute("role").equals("admin") || ((Integer) httpServletRequest.getAttribute("id") == userId)) {
            Optional<UserEntity> newUserEntity = userRepository.findById(userId);
            userRepository.delete(newUserEntity.get());
            return ResponseEntity.ok().build();
        } else {
            return  ResponseEntity.status(404).build();
        }
    }

    @Override
    public ResponseEntity<Void> editUser(@ApiParam(value = "User id that needs to be edited",required=true) @PathVariable("user_id") Integer userId,@ApiParam(value = "User object that needs to be edited" ,required=true )  @Valid @RequestBody UserManage user) {

        if(httpServletRequest.getAttribute("role").equals("admin") || ((Integer) httpServletRequest.getAttribute("id") == userId)) {
            Optional<UserEntity> newUserEntity = userRepository.findById(userId);
            newUserEntity.get().setFirstname(user.getFirstname());
            newUserEntity.get().setLastname(user.getLastname());
            newUserEntity.get().setUsername(user.getUsername());
            newUserEntity.get().setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            newUserEntity.get().setEmail(user.getEmail());
            newUserEntity.get().setRole(user.getRole());

            userRepository.save(newUserEntity.get());
            return ResponseEntity.ok().build();
        } else {
            return  ResponseEntity.status(404).build();
        }
    }

    @Override
    public ResponseEntity<UserManage> getUser(@ApiParam(value = "The user id that needs to be fetched",required=true) @PathVariable("user_id") Integer userId) {
        if(httpServletRequest.getAttribute("role").equals("admin") || ((Integer) httpServletRequest.getAttribute("id") == userId)) {
            Optional<UserEntity> newUserEntity = userRepository.findById(userId);
            if (!newUserEntity.isPresent()) {
                return ResponseEntity.status(404).build();
                // throw new EntityNotFoundException(1, "Entity not found");
            }
            UserManage newUser = toUserManage(newUserEntity.get());
            return ResponseEntity.ok(newUser);
        } else {
            return  ResponseEntity.status(404).build();
        }
    }
}
