package com.gydytojai.web.gydytojaiweb.controller;

import com.gydytojai.web.gydytojaiweb.model.Diagnozes;
import com.gydytojai.web.gydytojaiweb.service.DiagnozesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class DiagnozesRestController {

    @Autowired
    DiagnozesService service;

    @GetMapping("/list-diagnozes-json")
    public List<Diagnozes> showDiagnozesJson() {
        return service.findAll();
    }

    @GetMapping("/diagnozes/{diagnozesId}")
    public Diagnozes diagnozesById(@PathVariable int diagnozesId) {
        return service.findById(diagnozesId);
    }

    @PostMapping("/diagnozes")
    public ResponseEntity<Void> addDiagnozes(@RequestBody Diagnozes newDiagnozes) {
        Diagnozes diagnozes = service.add(newDiagnozes);
        if (diagnozes == null) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(diagnozes.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/diagnozes/{diagnozesId}")
    public void deleteDiagnozesById(@PathVariable int diagnozesId) {
        service.deleteById(diagnozesId);
    }
}
