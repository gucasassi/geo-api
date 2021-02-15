package es.ivks.geoapi.business.services.impl;

import es.ivks.geoapi.business.mappers.ComunidadMapper;
import es.ivks.geoapi.business.services.ComunidadService;
import es.ivks.geoapi.business.model.Comunidad;
import es.ivks.geoapi.data.entities.ComunidadEntity;
import es.ivks.geoapi.data.repositories.ComunidadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.*;
import org.springframework.stereotype.Service;

/**
 * @author: guillem.casas
 * @version: 12/02/2021
**/
@Slf4j
@Service
@RequiredArgsConstructor
public class ComunidadServiceImpl implements ComunidadService {

    private final ComunidadMapper mapper;
    private final ComunidadRepository repository;

    @Override
    public Comunidad getComunidadById(Long id) throws NotFoundException {

        log.info("Initializing getComunidadById with id value {}", id);

        return mapper.comunidadEntityToComunidad(
                repository.findById(id).orElseThrow(NotFoundException::new)
        );

    }

    @Override
    public Comunidad saveComunidad(Comunidad comunidad) {

        log.info("Initializing saveComunidad {}", comunidad);

        ComunidadEntity comunidadEntity = mapper.comunidadToComunidadEntity(comunidad);

        return mapper.comunidadEntityToComunidad(
          repository.save(comunidadEntity)
        );

    }

    @Override
    public void updateComunidad(Long id, Comunidad comunidad) throws NotFoundException {

        log.info("Initializing updateComunidad with id {} with new data {}", id, comunidad);

        ComunidadEntity comunidadEntity = repository.findById(id).orElseThrow(NotFoundException::new);
        comunidadEntity.setName(comunidad.getName());

        repository.save(comunidadEntity);

    }

    @Override
    public void deleteComunidad(Long id) throws NotFoundException {

        log.info("Initializing deleteComunidad with id {} ", id);

        ComunidadEntity comunidadEntity = repository.findById(id).orElseThrow(NotFoundException::new);
        repository.delete(comunidadEntity);

    }

}
