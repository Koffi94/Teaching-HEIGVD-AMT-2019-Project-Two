package ch.heigvd.amt.project2.api.endpoints;

import ch.heigvd.amt.project2.api.ScreeningApi;
import ch.heigvd.amt.project2.api.model.Screening;
import ch.heigvd.amt.project2.entities.ScreeningEntity;
import ch.heigvd.amt.project2.repositories.IScreeningRepository;
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
public class ScreeningApiController implements ScreeningApi {

    @Autowired
    IScreeningRepository screeningRepository;

    @Override
    public ResponseEntity<Void> addScreening(@Valid Screening screening) {
        ScreeningEntity newScreeningEntity = toScrenningEntity(screening);
        screeningRepository.save(newScreeningEntity);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/screening/{id}")
                .buildAndExpand(newScreeningEntity.getScreening_id()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> deleteScreening(Long screeningId) {
        Optional<ScreeningEntity> newScreeningEntity = screeningRepository.findById(screeningId);
        screeningRepository.delete(newScreeningEntity.get());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> editScreening(Long screeningId, @Valid Screening screening) {
        Optional<ScreeningEntity> newScreeningEntity = screeningRepository.findById(screeningId);
        newScreeningEntity.get().setUser_id(screening.getUserId());
        newScreeningEntity.get().setTime(screening.getTime());
        newScreeningEntity.get().setRoom(screening.getRoom());
        newScreeningEntity.get().setProperty(screening.getProperty());
        screeningRepository.save(newScreeningEntity.get());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Screening> getScreening(Long screeningId) {
        Optional<ScreeningEntity> newScreeningEntity = screeningRepository.findById(screeningId);
        Screening newScreening = toScreening(newScreeningEntity.get());
        return  ResponseEntity.ok(newScreening);
    }
}
