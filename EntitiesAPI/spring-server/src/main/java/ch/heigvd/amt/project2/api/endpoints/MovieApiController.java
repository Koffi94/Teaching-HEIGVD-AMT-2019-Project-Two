package ch.heigvd.amt.project2.api.endpoints;

import ch.heigvd.amt.project2.api.MovieApi;
import ch.heigvd.amt.project2.api.model.MovieManage;
import ch.heigvd.amt.project2.entities.MovieEntity;
import ch.heigvd.amt.project2.repositories.IMovieRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<Void> addMovie(@ApiParam(value = "Movie object that needs to be added to the DB" ,required=true )  @Valid @RequestBody MovieManage movie) {
        MovieEntity newMovieEntity = toMovieEntity(movie);
        movieRepository.save(newMovieEntity);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/movie/{id}")
                .buildAndExpand(newMovieEntity.getMovieId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> deleteMovie(@ApiParam(value = "The movie id that needs to be deleted",required=true) @PathVariable("movie_id") Long movieId) {
        Optional<MovieEntity> newMovieEntity = movieRepository.findById(movieId);
        movieRepository.delete(newMovieEntity.get());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> editMovie(@ApiParam(value = "Movie id that needs to be edited",required=true) @PathVariable("movie_id") Long movieId,@ApiParam(value = "Movie object that needs to be edited" ,required=true )  @Valid @RequestBody MovieManage movie) {
        Optional<MovieEntity> newMovieEntity = movieRepository.findById(movieId);
        newMovieEntity.get().setTitle(movie.getTitle());
        newMovieEntity.get().setRelease_date(movie.getReleaseDate());
        newMovieEntity.get().setCategory(movie.getCategory());
        movieRepository.save(newMovieEntity.get());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<MovieManage> getMovie(@ApiParam(value = "The movie id that needs to be fetched",required=true) @PathVariable("movie_id") Long movieId) {
        Optional<MovieEntity> newMovieEntity = movieRepository.findById(movieId);
        if(!newMovieEntity.isPresent()) {
            return ResponseEntity.status(404).build();
            // throw new EntityNotFoundException(1, "Entity not found");
        }
        MovieManage newMovie = toMovieManage(newMovieEntity.get());
        return  ResponseEntity.ok(newMovie);
    }
}
