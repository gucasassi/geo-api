package es.ivks.geoapi.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.ivks.geoapi.business.model.Comunidad;
import es.ivks.geoapi.business.services.ComunidadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author: guillem.casas
 * @version: 12/02/2021
**/
@WebMvcTest(ComunidadController.class)
class ComunidadControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ComunidadService comunidadService;

    @Autowired
    ObjectMapper objectMapper;

    private static final String API_COMUNIDADES = "/api/v1/comunidades/";

    @Test
    void getComunidadById_WhenComunidadExist_ThenReturnOK() throws Exception {

        Comunidad comunidad = getValidComunidadObject();

        when(comunidadService.getComunidadById(1L)).thenReturn(comunidad);

        mockMvc.perform(
                get(API_COMUNIDADES + 1L).accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.updated_date").value(comunidad.getUpdated()))
                .andExpect(jsonPath("$.size").value(1))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.id").value(comunidad.getId()))
                .andExpect(jsonPath("$.data.name").value(comunidad.getName()));

    }

    @Test
    void saveComunidad_WhenValidComunidad_ThenReturnIsCreated() throws Exception {

        Comunidad comunidad = Comunidad.builder().name("Euskadi").build();
        String comunidadJSON = objectMapper.writeValueAsString(comunidad);

        when(comunidadService.saveComunidad(comunidad)).thenReturn(comunidad);

        mockMvc.perform(
                post(API_COMUNIDADES).contentType(MediaType.APPLICATION_JSON)
                                             .content(comunidadJSON)
        ).andExpect(status().isCreated());

    }

    @Test
    void updateComunidad_WhenComunidadExist_ThenReturnIsNoContent() throws Exception {

        Comunidad comunidad = Comunidad.builder().name("CATALUNYA").build();
        String comunidadJSON = objectMapper.writeValueAsString(comunidad);

        doNothing().when(comunidadService).updateComunidad(1L, comunidad);

        mockMvc.perform(
                put(API_COMUNIDADES + 01L).contentType(MediaType.APPLICATION_JSON)
                                                             .content(comunidadJSON)
        ).andExpect(status().isNoContent());

    }

    @Test
    void deleteComunidad_WhenComunidadExist_ThenReturnIsNoContent() throws Exception {

        doNothing().when(comunidadService).deleteComunidad(01L);

        mockMvc.perform(
                delete(API_COMUNIDADES + 01L)
        ).andExpect(status().isNoContent());
    }

    private Comunidad getValidComunidadObject() {

        return Comunidad.builder()
                            .id(1L)
                            .name("Catalunya")
                            .updated("2021.02")
                            .build();

    }

}
