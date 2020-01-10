package ch.heigvd.amt.project2.repositories;

import ch.heigvd.amt.project2.entities.MovieEntity;
import org.springframework.data.repository.CrudRepository;

public interface IMovieRepository extends CrudRepository<MovieEntity, Long> {

}
