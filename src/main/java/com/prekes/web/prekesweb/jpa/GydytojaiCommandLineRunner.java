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
        repository.save(new Gydytojas("Aaa", "370111"));
        repository.save(new Gydytojas("Bbb", "370222"));
        repository.save(new Gydytojas("Ccc", "370333"));
        repository.save(new Gydytojas("Ddd", "370444"));
        repository.save(new Gydytojas("Eee", "370555"));
        repository.save(new Gydytojas("Fff", "370666"));
        repository.save(new Gydytojas("Vardas2", "122321"));
        repository.save(new Gydytojas("Vardas3", "52323235"));
        repository.save(new Gydytojas("Vardas4", "370111"));
        repository.save(new Gydytojas("Vardas5", "8323328"));
        repository.save(new Gydytojas("Vardas6", "370666"));
        repository.save(new Gydytojas("Vardas7", "370333"));
        repository.save(new Gydytojas("Vardas3", "370444"));
        repository.save(new Gydytojas("Vardas4", "370222"));
        repository.save(new Gydytojas("Vardas5", "370666"));

        for (Gydytojas o : repository.findAll()) {
            log.info(o.toString());
        }
    }

}
