package es.ivks.geoapi.business.services;

import es.ivks.geoapi.business.model.Comunidad;
import org.springframework.data.crossstore.ChangeSetPersister;

/**
 * @author: guillem.casas
 * @version: 12/02/2021
 **/
public interface ComunidadService {

    Comunidad getComunidadById(Long id) throws ChangeSetPersister.NotFoundException;

    Comunidad saveComunidad(Comunidad comunidad);

    void updateComunidad(Long id, Comunidad comunidad) throws ChangeSetPersister.NotFoundException;

    void deleteComunidad(Long id) throws ChangeSetPersister.NotFoundException;

}
