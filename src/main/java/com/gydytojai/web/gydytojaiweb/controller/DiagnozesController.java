package com.gydytojai.web.gydytojaiweb.controller;

import com.gydytojai.web.gydytojaiweb.model.Gydytojas;
import com.gydytojai.web.gydytojaiweb.model.Diagnozes;
import com.gydytojai.web.gydytojaiweb.service.DiagnozesService;
import com.gydytojai.web.gydytojaiweb.service.GydytojasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class DiagnozesController {

    @Autowired
    DiagnozesService service;

    @Autowired
    GydytojasService gydytojasService;

    @GetMapping("/list-diagnozes")
    public String showDiagnozes(ModelMap model) {
        List<Diagnozes> diagnozes = service.findAll();
        model.put("diagnozes", diagnozes);
        Map<Integer, Gydytojas> gydytojai = diagnozes.stream()
                .collect(Collectors.toMap(Diagnozes::getId, d -> gydytojasService.findById(d.getGydytojoId())));
        model.put("gydytojai", gydytojai);
        return "list-diagnozes";
    }

    @GetMapping("/add-diagnozes")
    public String showAddPage(ModelMap model) {
        model.addAttribute("diagnozes", new Diagnozes());
        return "diagnozes";
    }

    @PostMapping("/add-diagnozes")
    public String add(ModelMap model, @ModelAttribute("diagnozes") Diagnozes diagnozes, BindingResult result) {
        if (result.hasErrors()) {
            return "diagnozes";
        }
        service.add(diagnozes);
        return "redirect:/list-diagnozes";
    }

    @GetMapping("/update-diagnozes/{diagnozesId}")
    public String showUpdatePage(ModelMap model, @PathVariable int diagnozesId) {
        model.addAttribute("diagnozes", service.findById(diagnozesId));
        return "diagnozes";
    }

    @PostMapping("/update-diagnozes/{diagnozesId}")
    public String update(ModelMap model, @ModelAttribute("diagnozes") Diagnozes diagnozes, BindingResult result) {
        if (result.hasErrors()) {
            return "diagnozes";
        }
        service.update(diagnozes);
        return "redirect:/list-diagnozes";
    }

    @GetMapping("/delete-diagnozes/{diagnozesId}")
    public String delete(@PathVariable int diagnozesId) {
        service.deleteById(diagnozesId);
        return "redirect:/list-diagnozes";
    }
}
