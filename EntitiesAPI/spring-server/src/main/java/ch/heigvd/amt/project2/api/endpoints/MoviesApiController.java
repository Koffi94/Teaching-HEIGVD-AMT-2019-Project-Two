package ch.heigvd.amt.project2.api.endpoints;

import ch.heigvd.amt.project2.api.MoviesApi;
import ch.heigvd.amt.project2.api.model.CinemaFull;
import ch.heigvd.amt.project2.api.model.MovieFull;
import ch.heigvd.amt.project2.entities.CinemaEntity;
import ch.heigvd.amt.project2.entities.MovieEntity;
import ch.heigvd.amt.project2.repositories.IMovieRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static ch.heigvd.amt.project2.api.util.Transformer.toCinemaFull;
import static ch.heigvd.amt.project2.api.util.Transformer.toMovieFull;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-16T19:36:34.802Z")
@RestController
public class MoviesApiController implements MoviesApi {

    @Autowired
    IMovieRepository movieRepository;

    @Override
    public ResponseEntity<List<MovieFull>> getMovies(@ApiParam(value = "") @Valid @RequestParam(value = "pageId", required = false) Integer pageId,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        List<MovieFull> movies = new ArrayList<>();
        if(pageId == null) {
            pageId = 0;
        }

        if(pageSize == null) {
            pageSize = 10;
        }

        Pageable pageable = PageRequest.of(pageId,pageSize);
        Page<MovieEntity> page = movieRepository.findAll(pageable);
        for (MovieEntity movieEntity : page.toList()) {
            movies.add(toMovieFull(movieEntity));
        }
        return ResponseEntity.ok(movies);
    }
}
