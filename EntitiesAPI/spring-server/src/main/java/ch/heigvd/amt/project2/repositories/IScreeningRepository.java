package ch.heigvd.amt.project2.repositories;

import ch.heigvd.amt.project2.entities.ScreeningEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface IScreeningRepository extends CrudRepository<ScreeningEntity, Long> {

    Page<ScreeningEntity> findByUserId(Long userId, Pageable pageable);

}
