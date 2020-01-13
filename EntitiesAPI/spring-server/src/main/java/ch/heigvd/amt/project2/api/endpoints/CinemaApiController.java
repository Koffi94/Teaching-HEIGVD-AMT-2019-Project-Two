package ch.heigvd.amt.project2.api.endpoints;

import ch.heigvd.amt.project2.api.CinemaApi;
import ch.heigvd.amt.project2.api.exceptions.EntityNotFoundException;
import ch.heigvd.amt.project2.api.model.CinemaManage;
import ch.heigvd.amt.project2.entities.CinemaEntity;
import ch.heigvd.amt.project2.repositories.ICinemaRepository;
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
public class CinemaApiController implements CinemaApi {

    @Autowired
    ICinemaRepository cinemaRepository;

    @Override
    public ResponseEntity<Void> addCinema(@ApiParam(value = "Cinema object that needs to be added to the DB" ,required=true )  @Valid @RequestBody CinemaManage cinema) {
        CinemaEntity newCinemaEntity = toCinemaEntity(cinema);
        cinemaRepository.save(newCinemaEntity);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/cinema/{id}")
                .buildAndExpand(newCinemaEntity.getCinemaId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> deleteCinema(@ApiParam(value = "The cinema id that needs to be deleted",required=true) @PathVariable("cinema_id") Long cinemaId) {
        Optional<CinemaEntity> newCinemaEntity = cinemaRepository.findById(cinemaId);
        cinemaRepository.delete(newCinemaEntity.get());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> editCinema(@ApiParam(value = "Cinema id that needs to be edited", required = true) @PathVariable("cinema_id") Long cinemaId, @ApiParam(value = "Cinema object that needs to be edited", required = true) @Valid @RequestBody CinemaManage cinema) {
        Optional<CinemaEntity> newCinemaEntity = cinemaRepository.findById(cinemaId);
        newCinemaEntity.get().setName(cinema.getName());
        newCinemaEntity.get().setCity(cinema.getCity());
        newCinemaEntity.get().setPrice(cinema.getPrice());
        cinemaRepository.save(newCinemaEntity.get());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CinemaManage> getCinema(@ApiParam(value = "The cinema id that needs to be fetched",required=true) @PathVariable("cinema_id") Long cinemaId) {
        Optional<CinemaEntity> newCinemaEntity = cinemaRepository.findById(cinemaId);
        if(!newCinemaEntity.isPresent()) {
            return ResponseEntity.status(404).build();
            // throw new EntityNotFoundException(1, "Entity not found");
        }
        CinemaManage newCinema = toCinemaManage(newCinemaEntity.get());
        return ResponseEntity.ok(newCinema);
    }
}
