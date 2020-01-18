package ch.heigvd.amt.project2.api.util;

import ch.heigvd.amt.project2.api.UserApi;
import ch.heigvd.amt.project2.api.model.UserAuth;
import ch.heigvd.amt.project2.api.model.UserFull;
import ch.heigvd.amt.project2.api.model.UserManage;
import ch.heigvd.amt.project2.entities.UserEntity;

public class Transformer {
    static public UserEntity toUserEntity(UserFull user) {
        UserEntity entity = new UserEntity();
        entity.setUser_id(user.getId());
        entity.setFirstname(user.getFirstname());
        entity.setLastname(user.getLastname());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setEmail(user.getEmail());
        entity.setRole(user.getRole());
        return entity;
    }

    static public UserEntity toUserEntity(UserManage user) {
        UserEntity entity = new UserEntity();
        entity.setFirstname(user.getFirstname());
        entity.setLastname(user.getLastname());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setEmail(user.getEmail());
        entity.setRole(user.getRole());
        return entity;
    }

    static public UserFull toUserFull(UserEntity entity) {
        UserFull user = new UserFull();
        user.setId(entity.getUser_id());
        user.setFirstname(entity.getFirstname());
        user.setLastname(entity.getLastname());
        user.setUsername(entity.getUsername());
        user.setPassword(entity.getPassword());
        user.setEmail(entity.getEmail());
        user.setRole(entity.getRole());
        return user;
    }

    static public UserManage toUserManage(UserEntity entity) {
        UserManage user = new UserManage();
        user.setFirstname(entity.getFirstname());
        user.setLastname(entity.getLastname());
        user.setUsername(entity.getUsername());
        user.setPassword(entity.getPassword());
        user.setEmail(entity.getEmail());
        user.setRole(entity.getRole());
        return user;
    }
}
