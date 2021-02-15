package es.ivks.geoapi.business.mappers;

import es.ivks.geoapi.business.model.Comunidad;
import es.ivks.geoapi.data.entities.ComunidadEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author: guillem.casas
 * @version: 15/02/2021
 **/
@Mapper(componentModel = "spring")
public interface ComunidadMapper {

    @Mapping(source = "updatedDate", target = "updated", dateFormat = "YYYY.MM")
    Comunidad comunidadEntityToComunidad(ComunidadEntity comunidadEntity);

    ComunidadEntity comunidadToComunidadEntity(Comunidad comunidad);

}
