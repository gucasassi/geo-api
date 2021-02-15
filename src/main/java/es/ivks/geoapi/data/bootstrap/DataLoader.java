package es.ivks.geoapi.data.bootstrap;

import es.ivks.geoapi.data.entities.ComunidadEntity;
import es.ivks.geoapi.data.repositories.ComunidadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author: guillem.casas
 * @version: 15/02/2021
**/
@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final ComunidadRepository comunidadRepository;

    public DataLoader(ComunidadRepository comunidadRepository) {
        this.comunidadRepository = comunidadRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadComunidades();
    }

    private void loadComunidades() {

        if(comunidadRepository.count() == 0){

            ComunidadEntity catalonia = ComunidadEntity.builder().name("Catalunya").build();
            comunidadRepository.save(catalonia);

        }

        log.info("Loaded Communities: " + comunidadRepository.count());

    }

}
