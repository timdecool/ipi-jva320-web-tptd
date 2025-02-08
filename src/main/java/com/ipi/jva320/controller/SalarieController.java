package com.ipi.jva320.controller;

import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Controller
@RequestMapping("/salaries")
public class SalarieController {

    @Autowired
    SalarieAideADomicileService salarieAideADomicileService;

    @GetMapping("/{id}")
    public String getSalarie(@PathVariable Long id, ModelMap model) {
        SalarieAideADomicile salarie = salarieAideADomicileService.getSalarie(id);
        model.put("sa", salarie);
        if(salarie == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ce salarié n'existe pas.");
        }
        return "detail_Salarie";
    }

    @GetMapping("/aide/new")
    public String createSalarie(ModelMap model) {
        model.put("salarie", new SalarieAideADomicile());
        return "detail_Salarie";
    }

    @PostMapping("/save")
    public String saveSalarie(@ModelAttribute("salarie") SalarieAideADomicile salarie) throws SalarieException {
        salarieAideADomicileService.creerSalarieAideADomicile(salarie);
        return "redirect:/salaries/" + salarie.getId();
    }
}
