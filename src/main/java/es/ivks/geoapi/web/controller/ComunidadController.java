package es.ivks.geoapi.web.controller;

import es.ivks.geoapi.business.services.ComunidadService;
import es.ivks.geoapi.business.model.Comunidad;
import es.ivks.geoapi.web.response.GeoApiResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

/**
 * @author: guillem.casas
 * @version: 12/02/2021
**/
@RestController
@RequestMapping("/api/v1/comunidades")
public class ComunidadController {

    ComunidadService comunidadService;

    public ComunidadController(ComunidadService comunidadService) {
        this.comunidadService = comunidadService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeoApiResponse> getComunidadById(@PathVariable("id") Long id) throws ChangeSetPersister.NotFoundException {

        Comunidad comunidad = comunidadService.getComunidadById(id);
        GeoApiResponse apiResponse = GeoApiResponse.builder()
                                                    .updatedDate(comunidad.getUpdated())
                                                    .size(1)
                                                    .data(comunidad)
                                                    .build();

        return new ResponseEntity<>(apiResponse, OK);

    }

    @PostMapping
    public ResponseEntity<GeoApiResponse> saveComunidad(@Validated @RequestBody Comunidad comunidad){

        Comunidad savedComunidad = comunidadService.saveComunidad(comunidad);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/comunidades/" + savedComunidad.getId());

        return new ResponseEntity<>(headers, CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<GeoApiResponse> updateComunidad(@PathVariable("id") Long id, @Validated @RequestBody Comunidad comunidad) throws ChangeSetPersister.NotFoundException {

        comunidadService.updateComunidad(id, comunidad);
        return new ResponseEntity<>(NO_CONTENT);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeoApiResponse> deleteComunidad(@PathVariable("id") Long id) throws ChangeSetPersister.NotFoundException {

        comunidadService.deleteComunidad(id);
        return new ResponseEntity<>(NO_CONTENT);

    }

}
