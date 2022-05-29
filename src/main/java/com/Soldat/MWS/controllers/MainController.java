package com.Soldat.MWS.controllers;

import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    @GetMapping("/")
    public String greeting() {

        return "greeting";
    }

    @GetMapping("/mws")
    public String main() {

        return "index";
    }

    @GetMapping("mws/organization/{id}")
    public String organization (@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        return ("company");
    }

    @GetMapping("mws/document/{id}")
    public String document (@PathVariable long id, Model model) {
        model.addAttribute("docId", id);
        return ("document");
    }

}
