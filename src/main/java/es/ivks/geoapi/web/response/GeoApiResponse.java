package es.ivks.geoapi.web.response;

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
public class GeoApiResponse {

    private String updatedDate;
    private Integer size;
    private Object data;

}
