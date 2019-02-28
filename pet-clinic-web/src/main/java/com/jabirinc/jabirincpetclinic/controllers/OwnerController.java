package com.jabirinc.jabirincpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Getinet on 2019-02-28
 */
@RequestMapping("/owners")
@Controller
public class OwnerController {

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listOwners() {

        return "owners/index";
    }
}
