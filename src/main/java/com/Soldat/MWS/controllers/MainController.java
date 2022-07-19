package com.Soldat.MWS.controllers;

import com.Soldat.MWS.entity.supporting_classes.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@PreAuthorize("hasAuthority('ROLE_USER')")
@Controller
public class MainController {

    @GetMapping("/")
    public String greeting(@AuthenticationPrincipal User user, Model model) {
        if(user.getActivationCode()!=null){
            model.addAttribute("authorizationError",
                    user.getName() + " , ваша електронна адреса не підтверджена.\n" +
                            "Для підтвердження пройдіть за посиланням з нашого емейлу!");
            return "login";
        }

        return "redirect:/mws";
    }

    @GetMapping("/mws")
    public String main(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("mws/organization/{id}")
    public String organization (@AuthenticationPrincipal User user,
                                @PathVariable long id, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return ("company");
    }

    @GetMapping("mws/document/{id}")
    public String document (@AuthenticationPrincipal User user,
                            @PathVariable long id, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("docId", id);
        return ("document");
    }

}
