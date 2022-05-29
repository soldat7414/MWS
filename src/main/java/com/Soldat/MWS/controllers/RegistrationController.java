package com.Soldat.MWS.controllers;

import com.Soldat.MWS.entity.supporting_classes.Role;
import com.Soldat.MWS.entity.supporting_classes.User;
import com.Soldat.MWS.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo repo;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDB = repo.findByUsername(user.getUsername());

        if(userFromDB != null){
            model.put("massege", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        repo.save(user);
        return "redirect:/login";
    }
}
