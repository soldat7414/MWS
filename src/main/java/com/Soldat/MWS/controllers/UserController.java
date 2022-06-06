package com.Soldat.MWS.controllers;

import com.Soldat.MWS.entity.supporting_classes.Role;
import com.Soldat.MWS.entity.supporting_classes.User;
import com.Soldat.MWS.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepo userRepo;

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEdit(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @GetMapping("{user}/delete")
    public String userDelete(@PathVariable User user, Model model){
       userRepo.delete(user);
        return "userEdit";
    }

    @PostMapping
    public String saveUser(@RequestParam ("userId") User user,
                           @RequestParam Map<String, String> form){
        form.entrySet().stream().forEach(item-> System.out.println(item.getKey() + " : " + item.getValue()));
        if(form.get("username").length()>0) user.setUsername(form.get("username"));
        if(form.get("name").length()>0) user.setName(form.get("name"));
        if(form.get("surname").length()>0) user.setSurname(form.get("surname"));
        if(form.get("email").length()>0) user.setEmail(form.get("email"));
        if(form.get("password").length()>0) user.setPassword(form.get("password"));
        Set<String> roles = Arrays.stream(Role.values()).map(Role::getAuthority)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for(String key : form.keySet()){
            if(roles.contains(key)) user.setRole(Role.valueOf(key));
        }
        userRepo.save(user);
        return "redirect:/user";
    }
}
