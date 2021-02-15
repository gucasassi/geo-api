package es.ivks.geoapi.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@JsonPropertyOrder({"updatedDate", "size", "data"})
public class GeoApiResponse {

    @JsonProperty("updated_date")
    private String updatedDate;
    private Integer size;
    private Object data;

}
