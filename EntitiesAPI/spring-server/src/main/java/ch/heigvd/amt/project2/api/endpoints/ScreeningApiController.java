package ch.heigvd.amt.project2.api.endpoints;

import ch.heigvd.amt.project2.api.ScreeningApi;
import ch.heigvd.amt.project2.api.model.ScreeningManage;
import ch.heigvd.amt.project2.entities.CinemaEntity;
import ch.heigvd.amt.project2.entities.MovieEntity;
import ch.heigvd.amt.project2.entities.ScreeningEntity;
import ch.heigvd.amt.project2.repositories.ICinemaRepository;
import ch.heigvd.amt.project2.repositories.IMovieRepository;
import ch.heigvd.amt.project2.repositories.IScreeningRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

import static ch.heigvd.amt.project2.api.util.Transformer.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-16T19:36:34.802Z")
@RestController
public class ScreeningApiController implements ScreeningApi {

    @Autowired
    IScreeningRepository screeningRepository;

    @Autowired
    ICinemaRepository cinemaRepository;

    @Autowired
    IMovieRepository movieRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Override
    public ResponseEntity<Void> addScreening(@ApiParam(value = "Screening object that needs to be added to the DB" ,required=true )  @Valid @RequestBody ScreeningManage screening) {
        try {
            ScreeningEntity screeningEntity = toScrenningEntity(screening);
            screeningEntity.setUserId((Integer) httpServletRequest.getAttribute("id"));
            Optional<CinemaEntity> cinemaEntity = cinemaRepository.findById(screening.getCinemaId());
            Optional<MovieEntity> movieEntity = movieRepository.findById(screening.getMovieId());
            screeningEntity.setCinemaEntity(cinemaEntity.get());
            screeningEntity.setMovieEntity(movieEntity.get());
            screeningRepository.save(screeningEntity);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/screening/{id}")
                    .buildAndExpand(screeningEntity.getScreeningId()).toUri();

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteScreening(@ApiParam(value = "The screening id that needs to be deleted",required=true) @PathVariable("screening_id") Integer screeningId) {
        try {
            Optional<ScreeningEntity> newScreeningEntity = screeningRepository.findById(screeningId);
            if(newScreeningEntity.get().getUserId() != httpServletRequest.getAttribute("id")) {
                throw new Exception("Bad user");
            }
            screeningRepository.delete(newScreeningEntity.get());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @Override
    public ResponseEntity<Void> editScreening(@ApiParam(value = "Screening id that needs to be edited",required=true) @PathVariable("screening_id") Integer screeningId,@ApiParam(value = "Screening object that needs to be edited" ,required=true )  @Valid @RequestBody ScreeningManage screening) {
        try {
            Optional<ScreeningEntity> newScreeningEntity = screeningRepository.findById(screeningId);
            if(newScreeningEntity.get().getUserId() != httpServletRequest.getAttribute("id")) {
                throw new Exception("Bad user");
            }
            newScreeningEntity.get().setTime(screening.getTime());
            newScreeningEntity.get().setRoom(screening.getRoom());
            newScreeningEntity.get().setProperty(screening.getProperty());
            screeningRepository.save(newScreeningEntity.get());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @Override
    public ResponseEntity<ScreeningManage> getScreening(@ApiParam(value = "The screening id that needs to be fetched",required=true) @PathVariable("screening_id") Integer screeningId) {
        try {
            Optional<ScreeningEntity> newScreeningEntity = screeningRepository.findById(screeningId);
            if(newScreeningEntity.get().getUserId() != httpServletRequest.getAttribute("id")) {
                throw new Exception("Bad user");
            }
            ScreeningManage newScreening = toScreeningManage(newScreeningEntity.get());
            return ResponseEntity.ok(newScreening);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}
