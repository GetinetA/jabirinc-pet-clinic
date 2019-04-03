package com.jabirinc.jabirincpetclinic.controllers;

import com.jabirinc.jabirincpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Getinet on 2019-02-28
 */
@Controller
public class VetController {

    private VetService vetService;

    @Autowired
    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"/vets", "/vets/index", "/vets/index.html", "/vets.html"})
    public String listVet(Model model) {

        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }
}
