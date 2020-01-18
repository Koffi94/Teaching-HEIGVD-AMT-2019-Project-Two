package ch.heigvd.amt.project2.api.endpoints;

import ch.heigvd.amt.project2.api.ScreeningsApi;
import ch.heigvd.amt.project2.api.model.CinemaFull;
import ch.heigvd.amt.project2.api.model.ScreeningFull;
import ch.heigvd.amt.project2.api.model.ScreeningManage;
import ch.heigvd.amt.project2.entities.CinemaEntity;
import ch.heigvd.amt.project2.entities.ScreeningEntity;
import ch.heigvd.amt.project2.repositories.IScreeningRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ch.heigvd.amt.project2.api.util.Transformer.toCinemaFull;
import static ch.heigvd.amt.project2.api.util.Transformer.toScreeningFull;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-16T19:36:34.802Z")

@RestController
public class ScreeningsApiController implements ScreeningsApi {

    @Autowired
    IScreeningRepository screeningRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Override
    public ResponseEntity<List<ScreeningFull>> getScreenings(@ApiParam(value = "") @Valid @RequestParam(value = "pageId", required = false) Integer pageId,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        try {
            List<ScreeningFull> screenings = new ArrayList<>();
            if(pageId == null) {
                pageId = 0;
            }

            if(pageSize == null) {
                pageSize = 10;
            }

            Pageable pageable = PageRequest.of(pageId,pageSize);
            Page<ScreeningEntity> page;
            if(httpServletRequest.getAttribute("role").equals("admin")) {
                page = (Page<ScreeningEntity>) screeningRepository.findAll(pageable);
            } else {
                page = screeningRepository.findByUserId((Integer) httpServletRequest.getAttribute("id"), pageable);
            }
            for (ScreeningEntity screeningEntity : page.toList()) {
                screenings.add(toScreeningFull(screeningEntity));
            }
            return ResponseEntity.ok(screenings);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }
}
