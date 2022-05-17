package com.Soldat.MWS.controllers;

import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    @GetMapping("/mws")
    public String greeting() {

        return "index";
    }

    @GetMapping("mws/organization/{id}")
    public String doc (@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        return ("company");
    }

}
