package ch.heigvd.amt.project2.api.endpoints;

import ch.heigvd.amt.project2.api.ScreeningsApi;
import ch.heigvd.amt.project2.api.model.ScreeningFull;
import ch.heigvd.amt.project2.entities.ScreeningEntity;
import ch.heigvd.amt.project2.repositories.IScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ch.heigvd.amt.project2.api.util.Transformer.toScreeningFull;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-16T19:36:34.802Z")

@RestController
public class ScreeningsApiController implements ScreeningsApi {

    @Autowired
    IScreeningRepository screeningRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Override
    public ResponseEntity<List<ScreeningFull>> getScreenings() {
        Long userId = (Long) httpServletRequest.getAttribute("userId");
        if(userId != null) {
            List<ScreeningFull> screenings = new ArrayList<>();
            for (ScreeningEntity screeningEntity : screeningRepository.findAllById(Collections.singleton(userId))) {
                screenings.add(toScreeningFull(screeningEntity));
            }
            return ResponseEntity.ok(screenings);
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
