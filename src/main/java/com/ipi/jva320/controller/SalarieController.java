package com.ipi.jva320.controller;

import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/salaries")
public class SalarieController {

    @Autowired
    SalarieAideADomicileService salarieAideADomicileService;

    @GetMapping("/{id}")
    public String getSalarie(@PathVariable Long id, ModelMap model) {
        SalarieAideADomicile salarie = new SalarieAideADomicile("Jeannette Dupontelle", LocalDate.of(2021, 7, 1), LocalDate.now(), 0, 0, 10, 1, 0);
        model.put("salarie", salarie);
        return "detail_Salarie";
    }

}
