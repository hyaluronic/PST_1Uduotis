package com.gydytojai.web.gydytojaiweb.service;

import com.gydytojai.web.gydytojaiweb.repository.DiagnozesRepository;
import com.gydytojai.web.gydytojaiweb.model.Diagnozes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnozesService {

    @Autowired
    GydytojasService gydytojasService;

    @Autowired
    private DiagnozesRepository repository;

    public List<Diagnozes> findAll() {
        return (List<Diagnozes>) repository.findAll();
    }

    public Diagnozes findById(int id) {
        return repository.findById(id).get();
    }

    public List<Diagnozes> findByGydytojoId(int gydytojoId) {
        return repository.findByGydytojoId(gydytojoId);
    }

    public List<Diagnozes> findByPacientoId(int pacientoId) {
        return repository.findByPacientoId(pacientoId);
    }

    public List<Diagnozes> findByDiagnoze(String diagnoze) {
        return repository.findByDiagnoze(diagnoze);

    }

    public List<Diagnozes> findByData(String date) {
        return repository.findByData(date);
    }

    public int findMaxId() {
        Iterable<Diagnozes> diagnozes = repository.findAll();
        int max = 0;
        for (Diagnozes o : diagnozes) {
            if (o.getId() > max) max = o.getId();
        }
        return max;
    }

    public void update(Diagnozes diagnozes) {
        repository.save(diagnozes);
    }

    public Diagnozes add(Diagnozes diagnozes) {
        repository.save(diagnozes);
        return diagnozes;
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public void delete(Diagnozes diagnozes) {
        repository.delete(diagnozes);
    }
}
