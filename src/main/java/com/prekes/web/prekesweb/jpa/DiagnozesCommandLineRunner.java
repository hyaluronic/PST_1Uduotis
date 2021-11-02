package com.prekes.web.prekesweb.jpa;

import com.prekes.web.prekesweb.model.Diagnozes;
import com.prekes.web.prekesweb.repository.DiagnozesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// CommandLineRunner is invoked on application start

@Component
public class DiagnozesCommandLineRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DiagnozesCommandLineRunner.class);

    @Autowired
    private DiagnozesRepository repository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("~~~~~~ DiagnozesCommandLineRunner ~~~~~~");
        repository.save(new Diagnozes(1, 6, "Liga1", "01.01"));
        repository.save(new Diagnozes(2, 7, "Liga2", "01.02"));
        repository.save(new Diagnozes(3, 8, "Liga1", "01.03"));
        repository.save(new Diagnozes(4, 6, "Liga4", "01.04"));
        repository.save(new Diagnozes(1, 7, "Liga5", "01.05"));

        for (Diagnozes o : repository.findAll()) {
            log.info(o.toString());
        }
    }

}
