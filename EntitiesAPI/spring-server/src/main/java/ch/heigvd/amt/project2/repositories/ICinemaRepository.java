package ch.heigvd.amt.project2.repositories;

import ch.heigvd.amt.project2.entities.CinemaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ICinemaRepository extends CrudRepository<CinemaEntity, Integer> {

    Page<CinemaEntity> findAll(Pageable pageable);

}
