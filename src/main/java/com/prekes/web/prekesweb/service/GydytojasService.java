package com.prekes.web.prekesweb.service;

import com.prekes.web.prekesweb.model.Diagnozes;
import com.prekes.web.prekesweb.model.Gydytojas;
import com.prekes.web.prekesweb.repository.GydytojasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GydytojasService {

    @Autowired
    DiagnozesService diagnozesService;
    @Autowired
    private GydytojasRepository repository;

    public List<Gydytojas> findAll() {
        return (List<Gydytojas>) repository.findAll();
    }

    public Gydytojas findById(int id) {
        return repository.findById(id).get();
    }

    public List<Gydytojas> findByVardas(String vardas) {
        List<Gydytojas> gydytojai = new ArrayList<>();
        for (Gydytojas o : repository.findAll()) {
            if (o.getVardas().equals(vardas))
                gydytojai.add(o);
        }
        return gydytojai;
    }

    public List<Diagnozes> findDiagnozesByGydytojoId(int gydytojoId) {
        return diagnozesService.findByGydytojoId(gydytojoId);
    }

    public void update(Gydytojas gydytojas) {
        repository.save(gydytojas);
    }

    public Gydytojas add(Gydytojas gydytojas) {
        return repository.save(gydytojas);
    }
//
//    public int findMaxId() {
//        Iterable<Gydytojas> gydytojai = repository.findAll();
//        int max = 0;
//        for(Gydytojas o : gydytojai) {
//            if(o.getId() > max) max = o.getId();
//        }
//        return max;
//    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public void delete(Gydytojas gydytojas) {
        repository.delete(gydytojas);
    }
}
