package es.ivks.geoapi.web.controller;

import es.ivks.geoapi.model.Comunidad;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

/**
 * @author: guillem.casas
 * @version: 12/02/2021
**/
@RestController
@RequestMapping("/api/v1/comunidades")
public class ComunidadController {

    @GetMapping("/{id}")
    public ResponseEntity getComunidadById(@PathVariable("id") Long id){
        // TODO: Implement ComunidadService.getComunidadById(id)
        return new ResponseEntity(Comunidad.builder().build(), OK);
    }

    @PostMapping
    public ResponseEntity saveComunidad(@RequestBody Comunidad comunidad){
        // TODO: Implement ComunidadService.saveComunidad(comunidad)
        return new ResponseEntity(CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateComunidad(@PathVariable("id") Long id, @RequestBody Comunidad comunidad){
        // TODO: Implement ComunidadService.updateComunidad(id, comunidad)
        return new ResponseEntity(NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComunidad(@PathVariable("id") Long id){
        // TODO: Implement ComunidadService.deleteComunidad(id)
        return new ResponseEntity(NO_CONTENT);
    }

}
