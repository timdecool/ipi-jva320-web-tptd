package com.ipi.jva320.controller;

import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;


    @GetMapping("/")
    public String home(final ModelMap model) {
        Long numberOfEmployees = salarieAideADomicileService.countSalaries();
        model.put("numberOfEmployees", numberOfEmployees);
        return "home";
    }
}
