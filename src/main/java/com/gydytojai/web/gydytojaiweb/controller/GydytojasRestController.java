package com.gydytojai.web.gydytojaiweb.controller;

import com.gydytojai.web.gydytojaiweb.model.Gydytojas;
import com.gydytojai.web.gydytojaiweb.model.Diagnozes;
import com.gydytojai.web.gydytojaiweb.service.DiagnozesService;
import com.gydytojai.web.gydytojaiweb.service.GydytojasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class GydytojasRestController {
    @Autowired
    GydytojasService service;

    @Autowired
    DiagnozesService diagnozesService;

    @GetMapping("/gydytojai-json")
    public Iterable<Gydytojas> gydytojaiJson() {
        return service.findAll();
    }

    @GetMapping("/gydytojai/{gydytojoId}")
    public Gydytojas gydytojasById(@PathVariable int gydytojoId) {
        return service.findById(gydytojoId);
    }

    @PostMapping("/gydytojai")
    public ResponseEntity<Void> addGydytojas(@RequestBody Gydytojas newGydytojas) {
        Gydytojas gydytojas = service.add(newGydytojas);
        if (gydytojas == null) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(gydytojas.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/gydytojai/{gydytojoId}")
    public void deleteGydytojasById(@PathVariable int gydytojoId) {
        diagnozesService.findByGydytojoId(gydytojoId).forEach(d -> diagnozesService.delete(d));
        service.deleteById(gydytojoId);
    }

    @GetMapping("/gydytojai/{gydytojoId}/diagnozes")
    public List<Diagnozes> findDiagnozesForGydytojas(@PathVariable int gydytojoId) {
        return service.findDiagnozesByGydytojoId(gydytojoId);
    }

    @PostMapping("/gydytojai/{gydytojoId}/diagnozes")
    public ResponseEntity<Void> addDiagnozesToGydytojas(@PathVariable String gydytojoId, @RequestBody Diagnozes newDiagnoze) {
        Diagnozes diagnoze = diagnozesService.add(newDiagnoze);
        if (diagnoze == null)
            return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(diagnoze.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/gydytojai/{gydytojoId}/diagnozes/{diagnozesId}")
    public Diagnozes retrieveDetailsForDiagnoze(@PathVariable int gydytojoId, @PathVariable int diagnozesId) {
        return diagnozesService.findById(diagnozesId);
    }
}
