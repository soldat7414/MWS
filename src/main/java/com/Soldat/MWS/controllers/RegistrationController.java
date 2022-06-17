package com.Soldat.MWS.controllers;

import com.Soldat.MWS.entity.supporting_classes.User;
import com.Soldat.MWS.services.controller_services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(@ModelAttribute ("user") User user) {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute ("user") @Valid User user,
                          BindingResult bindingResult,
                          Model model) {
        //valid confirmation password
        if(user.getPassword()!=null && !user.getPassword().equals(user.getPassword2())){
            model.addAttribute("passwordError", "Паролі не співпадають!");
            return "registration";
        }
        //check if there is an errors of validation
        if(bindingResult.hasErrors()){
//            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
//            model.mergeAttributes(errorsMap);
            return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("message", "Такий nik-name вже використовується!");
            return "registration";
        }
        model.addAttribute("user", null);
        return "redirect:/login";
    }



    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActiveted = userService.activateUser(code);

        if (isActiveted) {
            model.addAttribute("message",
                    "Активація облікового запису пройшла успішно!");
        } else {
            model.addAttribute("message",
                    "Мабуть, Ващ обліковий запис було активовано раніше...");
        }
        return "login";
    }
}
