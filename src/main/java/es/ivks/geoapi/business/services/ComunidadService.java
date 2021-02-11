package es.ivks.geoapi.business.services;

import es.ivks.geoapi.business.model.Comunidad;

/**
 * @author: guillem.casas
 * @version: 12/02/2021
 **/
public interface ComunidadService {

    Comunidad getComunidadById(Long id);

    Comunidad saveComunidad(Comunidad comunidad);

    void updateComunidad(Long id, Comunidad comunidad);

    void deleteComunidad(Long id);

}
