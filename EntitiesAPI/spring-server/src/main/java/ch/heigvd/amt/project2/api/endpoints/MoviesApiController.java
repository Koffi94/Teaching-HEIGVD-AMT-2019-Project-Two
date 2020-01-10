package ch.heigvd.amt.project2.api.endpoints;

import ch.heigvd.amt.project2.api.MoviesApi;
import ch.heigvd.amt.project2.api.model.Movie;
import ch.heigvd.amt.project2.entities.MovieEntity;
import ch.heigvd.amt.project2.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static ch.heigvd.amt.project2.api.util.Transformer.toMovie;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-16T19:36:34.802Z")
@RestController
public class MoviesApiController implements MoviesApi {

    @Autowired
    IMovieRepository movieRepository;

    @Override
    public ResponseEntity<List<Movie>> getMovies() {
        List<Movie> movies = new ArrayList<>();
        for (MovieEntity movieEntity : movieRepository.findAll()) {
            movies.add(toMovie(movieEntity));
        }
        return ResponseEntity.ok(movies);
    }
}
