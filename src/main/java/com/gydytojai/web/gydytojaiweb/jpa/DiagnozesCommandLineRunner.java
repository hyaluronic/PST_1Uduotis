package com.gydytojai.web.gydytojaiweb.jpa;

import com.gydytojai.web.gydytojaiweb.model.Diagnozes;
import com.gydytojai.web.gydytojaiweb.repository.DiagnozesRepository;
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
        repository.save(new Diagnozes(1, 24, "Liga1", "01.01"));
        repository.save(new Diagnozes(2, 21, "Liga2", "01.02"));
        repository.save(new Diagnozes(3, 21, "Liga3", "01.03"));
        repository.save(new Diagnozes(1, 23, "Liga4", "01.04"));
        repository.save(new Diagnozes(4, 23, "Liga1", "01.04"));
        repository.save(new Diagnozes(3, 21, "Liga2", "01.03"));
        repository.save(new Diagnozes(2, 22, "Liga3", "01.04"));
        repository.save(new Diagnozes(4, 22, "Liga4", "01.03"));
        repository.save(new Diagnozes(5, 34, "Liga5", "01.04"));
        repository.save(new Diagnozes(1, 27, "Liga5", "01.05"));
        repository.save(new Diagnozes(1, 26, "Liga1", "01.02"));
        repository.save(new Diagnozes(2, 27, "Liga2", "01.02"));
        repository.save(new Diagnozes(3, 28, "Liga1", "03.03"));
        repository.save(new Diagnozes(2, 26, "Liga4", "02.04"));
        repository.save(new Diagnozes(1, 27, "Liga5", "02.05"));
        repository.save(new Diagnozes(1, 26, "Liga1", "03.02"));
        repository.save(new Diagnozes(5, 27, "Liga3", "03.02"));
        repository.save(new Diagnozes(3, 28, "Liga2", "02.03"));
        repository.save(new Diagnozes(1, 26, "Liga4", "01.04"));
        repository.save(new Diagnozes(1, 27, "Liga1", "01.05"));

        for (Diagnozes o : repository.findAll()) {
            log.info(o.toString());
        }
    }

}
