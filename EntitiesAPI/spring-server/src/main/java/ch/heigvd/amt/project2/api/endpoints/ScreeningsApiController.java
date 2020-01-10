package ch.heigvd.amt.project2.api.endpoints;

import ch.heigvd.amt.project2.api.ScreeningsApi;
import ch.heigvd.amt.project2.api.model.Screening;
import ch.heigvd.amt.project2.entities.ScreeningEntity;
import ch.heigvd.amt.project2.repositories.IScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static ch.heigvd.amt.project2.api.util.Transformer.toScreening;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-16T19:36:34.802Z")

@RestController
public class ScreeningsApiController implements ScreeningsApi {

    @Autowired
    IScreeningRepository screeningRepository;

    @Override
    public ResponseEntity<List<Screening>> getScreenings() {
        List<Screening> screenings = new ArrayList<>();
        for (ScreeningEntity screeningEntity : screeningRepository.findAll()) {
            screenings.add(toScreening(screeningEntity));
        }
        return ResponseEntity.ok(screenings);
    }
}
