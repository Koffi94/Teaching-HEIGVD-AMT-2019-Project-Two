package ch.heigvd.amt.project2.repositories;

import ch.heigvd.amt.project2.entities.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface IMovieRepository extends CrudRepository<MovieEntity, Integer> {

    Page<MovieEntity> findAll(Pageable pageable);
}
