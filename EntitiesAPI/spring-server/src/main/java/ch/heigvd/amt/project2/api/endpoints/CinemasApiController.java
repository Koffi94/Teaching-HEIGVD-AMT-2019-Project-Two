package ch.heigvd.amt.project2.api.endpoints;

import ch.heigvd.amt.project2.api.CinemasApi;
import ch.heigvd.amt.project2.api.model.CinemaFull;
import ch.heigvd.amt.project2.entities.CinemaEntity;
import ch.heigvd.amt.project2.repositories.ICinemaRepository;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-16T19:36:34.802Z")

@RestController
public class CinemasApiController implements CinemasApi {

    @Autowired
    ICinemaRepository cinemaRepository;

    @Override
    public ResponseEntity<List<CinemaFull>> getCinemas(@ApiParam(value = "") @Valid @RequestParam(value = "pageId", required = false) Integer pageId,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        List<CinemaFull> cinemas = new ArrayList<>();
        if(pageId == null) {
            pageId = 0;
        }

        if(pageSize == null) {
            pageSize = 10;
        }

        Pageable pageable = PageRequest.of(pageId,pageSize);
        Page<CinemaEntity> page = cinemaRepository.findAll(pageable);
        for (CinemaEntity cinemaEntity : page.toList()) {
            cinemas.add(toCinemaFull(cinemaEntity));
        }
        return ResponseEntity.ok(cinemas);
    }
}
