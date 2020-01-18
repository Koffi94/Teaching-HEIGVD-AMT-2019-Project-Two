package ch.heigvd.amt.project2.repositories;

import ch.heigvd.amt.project2.api.model.ScreeningFull;
import ch.heigvd.amt.project2.entities.ScreeningEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IScreeningRepository extends CrudRepository<ScreeningEntity, Integer> {

    Page<ScreeningEntity> findByUserId(Integer userId, Pageable pageable);
    Page<ScreeningEntity> findAll(Pageable pageable);
}
