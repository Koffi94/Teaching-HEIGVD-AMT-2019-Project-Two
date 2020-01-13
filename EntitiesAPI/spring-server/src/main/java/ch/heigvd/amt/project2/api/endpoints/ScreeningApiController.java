package ch.heigvd.amt.project2.api.endpoints;

import ch.heigvd.amt.project2.api.ScreeningApi;
import ch.heigvd.amt.project2.api.model.ScreeningManage;
import ch.heigvd.amt.project2.entities.ScreeningEntity;
import ch.heigvd.amt.project2.repositories.IScreeningRepository;
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
public class ScreeningApiController implements ScreeningApi {

    @Autowired
    IScreeningRepository screeningRepository;

    @Override
    public ResponseEntity<Void> addScreening(@ApiParam(value = "Screening object that needs to be added to the DB" ,required=true )  @Valid @RequestBody ScreeningManage screening) {
        ScreeningEntity newScreeningEntity = toScrenningEntity(screening);
        screeningRepository.save(newScreeningEntity);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/screening/{id}")
                .buildAndExpand(newScreeningEntity.getScreening_id()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> deleteScreening(@ApiParam(value = "The screening id that needs to be deleted",required=true) @PathVariable("screening_id") Long screeningId) {
        Optional<ScreeningEntity> newScreeningEntity = screeningRepository.findById(screeningId);
        screeningRepository.delete(newScreeningEntity.get());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> editScreening(@ApiParam(value = "Screening id that needs to be edited",required=true) @PathVariable("screening_id") Long screeningId,@ApiParam(value = "Screening object that needs to be edited" ,required=true )  @Valid @RequestBody ScreeningManage screening) {
        Optional<ScreeningEntity> newScreeningEntity = screeningRepository.findById(screeningId);
        newScreeningEntity.get().setUser_id(screening.getUserId());
        newScreeningEntity.get().setTime(screening.getTime());
        newScreeningEntity.get().setRoom(screening.getRoom());
        newScreeningEntity.get().setProperty(screening.getProperty());
        screeningRepository.save(newScreeningEntity.get());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ScreeningManage> getScreening(@ApiParam(value = "The screening id that needs to be fetched",required=true) @PathVariable("screening_id") Long screeningId) {
        Optional<ScreeningEntity> newScreeningEntity = screeningRepository.findById(screeningId);
        ScreeningManage newScreening = toScreeningManage(newScreeningEntity.get());
        return  ResponseEntity.ok(newScreening);
    }
}
