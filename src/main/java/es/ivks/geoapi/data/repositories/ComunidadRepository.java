package es.ivks.geoapi.data.repositories;

import es.ivks.geoapi.data.entities.ComunidadEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author: guillem.casas
 * @version: 15/02/2021
 **/
public interface ComunidadRepository extends PagingAndSortingRepository<ComunidadEntity, Long> {
}
