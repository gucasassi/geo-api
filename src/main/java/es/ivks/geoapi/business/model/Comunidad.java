package es.ivks.geoapi.business.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;

/**
 * @author: guillem.casas
 * @version: 12/02/2021
**/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "updated" })
public class Comunidad {

    @Null(message = "The id attribute of Comunidad must be null")
    private Long id;

    @NotEmpty(message = "The name attribute of Comunidad is mandatory")
    private String name;

    @Null(message = "The updated attribute of Comunidad must be null")
    private String updated;

}
