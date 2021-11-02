package com.prekes.web.prekesweb.controller;

import com.prekes.web.prekesweb.model.Gydytojas;
import com.prekes.web.prekesweb.service.DiagnozesService;
import com.prekes.web.prekesweb.service.GydytojasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GydytojasController {
    @Autowired
    GydytojasService service;

    @Autowired
    DiagnozesService diagnozesService;

    @GetMapping("/list-gydytojai")
    public String showAllGydytojai(ModelMap model) {
        model.put("gydytojai", service.findAll());
        return "list-gydytojai";
    }

    @GetMapping("/add-gydytojas")
    public String showAddPage(ModelMap model) {
        model.addAttribute("gydytojas", new Gydytojas());
        return "gydytojas";
    }

    @PostMapping("/add-gydytojas")
    public String add(ModelMap model, @ModelAttribute("gydytojas") Gydytojas gydytojas, BindingResult result) {
        if (result.hasErrors()) {
            return "gydytojas";
        }
        service.add(gydytojas);
        return "redirect:/list-gydytojai";
    }

    @GetMapping("/update-gydytojas/{gydytojoId}")
    public String showUpdatePage(ModelMap model, @PathVariable int gydytojoId) {
        model.addAttribute("gydytojas", service.findById(gydytojoId));
        return "gydytojas";
    }

    @PostMapping("/update-gydytojas/{gydytojoId}")
    public String update(ModelMap model, @ModelAttribute("gydytojas") Gydytojas gydytojas, BindingResult result) {
        if (result.hasErrors()) {
            return "gydytojas";
        }
        service.update(gydytojas);
        return "redirect:/list-gydytojai";
    }

    @GetMapping("/delete-gydytojas/{gydytojoId}")
    public String delete(@PathVariable int gydytojoId) {
        diagnozesService.findByGydytojoId(gydytojoId).forEach(d -> diagnozesService.delete(d));
        service.deleteById(gydytojoId);
        return "redirect:/list-gydytojai";
    }
}
