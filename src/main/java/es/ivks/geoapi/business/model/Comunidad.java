package es.ivks.geoapi.business.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: guillem.casas
 * @version: 12/02/2021
**/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comunidad {

    private Long id;
    private String name;
    private String updated;

}
