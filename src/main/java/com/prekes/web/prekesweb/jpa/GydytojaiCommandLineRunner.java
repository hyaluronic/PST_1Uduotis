package com.prekes.web.prekesweb.jpa;

import com.prekes.web.prekesweb.model.Gydytojas;
import com.prekes.web.prekesweb.repository.GydytojasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// CommandLineRunner is invoked on application start

@Component
public class GydytojaiCommandLineRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(GydytojaiCommandLineRunner.class);

    @Autowired
    private GydytojasRepository repository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("~~~~~~ GydytojasCommandLineRunner ~~~~~~");
        repository.save(new Gydytojas("Vardas1", "22"));
        repository.save(new Gydytojas("Vardas2", "11"));
        repository.save(new Gydytojas("Vardas3", "55"));
        repository.save(new Gydytojas("Vardas4", "66"));
        repository.save(new Gydytojas("Vardas5", "88"));

        for (Gydytojas o : repository.findAll()) {
            log.info(o.toString());
        }
    }

}
