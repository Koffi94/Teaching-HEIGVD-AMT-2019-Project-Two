package ch.heigvd.amt.project2.repositories;

import ch.heigvd.amt.project2.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findByUsername(String username);
    Page<UserEntity> findAll(Pageable pageable);
}
