package ch.heigvd.amt.project2.api.endpoints;

import ch.heigvd.amt.project2.api.CinemasApi;
import ch.heigvd.amt.project2.api.model.Cinema;
import ch.heigvd.amt.project2.entities.CinemaEntity;
import ch.heigvd.amt.project2.repositories.ICinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static ch.heigvd.amt.project2.api.util.Transformer.toCinema;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-16T19:36:34.802Z")

@RestController
public class CinemasApiController implements CinemasApi {

    @Autowired
    ICinemaRepository cinemaRepository;

    @Override
    public ResponseEntity<List<Cinema>> getCinemas() {
        List<Cinema> cinemas = new ArrayList<>();
        for (CinemaEntity cinemaEntity : cinemaRepository.findAll()) {
            cinemas.add(toCinema(cinemaEntity));
        }
        return ResponseEntity.ok(cinemas);
    }
}
