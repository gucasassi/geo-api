package es.ivks.geoapi.business.services.impl;

import es.ivks.geoapi.business.services.ComunidadService;
import es.ivks.geoapi.business.model.Comunidad;
import org.springframework.stereotype.Service;

/**
 * @author: guillem.casas
 * @version: 12/02/2021
**/
@Service
public class ComunidadServiceImpl implements ComunidadService {

    @Override
    public Comunidad getComunidadById(Long id) {
        // TODO: Implement repository
        return Comunidad.builder().build();
    }

    @Override
    public Comunidad saveComunidad(Comunidad comunidad) {
        // TODO: Implement repository
        return Comunidad.builder().build();
    }

    @Override
    public void updateComunidad(Long id, Comunidad comunidad) {
        // TODO: Implement repository
    }

    @Override
    public void deleteComunidad(Long id) {
        // TODO: Implement repository
    }

}
