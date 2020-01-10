package ch.heigvd.amt.project2.api.endpoints;

import ch.heigvd.amt.project2.api.MovieApi;
import ch.heigvd.amt.project2.api.model.Movie;
import ch.heigvd.amt.project2.entities.MovieEntity;
import ch.heigvd.amt.project2.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

import static ch.heigvd.amt.project2.api.util.Transformer.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-16T19:36:34.802Z")
@RestController
public class MovieApiController implements MovieApi {

    @Autowired
    IMovieRepository movieRepository;

    @Override
    public ResponseEntity<Void> addMovie(@Valid Movie movie) {
        MovieEntity newMovieEntity = toMovieEntity(movie);
        movieRepository.save(newMovieEntity);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/movie/{id}")
                .buildAndExpand(newMovieEntity.getMovie_id()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> deleteMovie(Long movieId) {
        Optional<MovieEntity> newMovieEntity = movieRepository.findById(movieId);
        movieRepository.delete(newMovieEntity.get());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> editMovie(Long movieId, @Valid Movie movie) {
        Optional<MovieEntity> newMovieEntity = movieRepository.findById(movieId);
        newMovieEntity.get().setTitle(movie.getTitle());
        newMovieEntity.get().setRelease_date(movie.getReleaseDate());
        newMovieEntity.get().setCategory(movie.getCategory());
        movieRepository.save(newMovieEntity.get());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Movie> getMovie(Long movieId) {
        Optional<MovieEntity> newMovieEntity = movieRepository.findById(movieId);
        Movie newMovie = toMovie(newMovieEntity.get());
        return  ResponseEntity.ok(newMovie);
    }
}
