package com.ipi.jva320.controller;

import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Controller
@RequestMapping("/salaries")
public class SalarieController {

    @Autowired
    SalarieAideADomicileService salarieAideADomicileService;

    @GetMapping("")
    public String getSalaries(
            ModelMap model,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            @RequestParam(value = "sortProperty", defaultValue = "id") String property,
            @RequestParam(value = "sortDirection", defaultValue = "ASC") String direction,
            @RequestParam(value = "nom", defaultValue = "") String nom
    ) {
        List<SalarieAideADomicile> salaries;
        Long numberOfEmployees;

        if(!nom.isEmpty()) {
            salaries = salarieAideADomicileService.getSalaries(nom, PageRequest.of(page, size));
            numberOfEmployees = (long) salarieAideADomicileService.getSalaries(nom).size();

        } else {
            salaries = salarieAideADomicileService.getSalaries(PageRequest.of(page, size)).getContent();
            numberOfEmployees = salarieAideADomicileService.countSalaries();
        }

        if(salaries.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas de salariés répondant à ces critères.");
        }

        model.put("numberOfEmployees", numberOfEmployees);
        model.put("salaries", salaries);
        model.put("page", page);
        model.put("size", size);

        return "list";
    }

    @GetMapping("/{id}")
    public String getSalarie(@PathVariable Long id, ModelMap model) {
        SalarieAideADomicile salarie = salarieAideADomicileService.getSalarie(id);
        if(salarie == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ce salarié n'existe pas.");
        }
        model.put("salarie", salarie);
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

    @PostMapping("/{id}")
    public String updateSalarie(@PathVariable Long id, @ModelAttribute("salarie") SalarieAideADomicile salarie) throws SalarieException {
        salarie.setId(id);
        salarieAideADomicileService.updateSalarieAideADomicile(salarie);
        return "redirect:/salaries/" + salarie.getId();
    }

    @GetMapping("/{id}/delete")
    public String deleteSalarie(@PathVariable Long id) throws SalarieException {
        salarieAideADomicileService.deleteSalarieAideADomicile(id);
        return "redirect:/salaries";
    }

}
