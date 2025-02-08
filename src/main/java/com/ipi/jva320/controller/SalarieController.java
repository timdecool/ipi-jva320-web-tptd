package com.ipi.jva320.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/salaries")
public class SalarieController {

    @GetMapping("/{id}")
    public String getSalarie(@PathVariable int id) {
        return "detail_Salarie";
    }

}
