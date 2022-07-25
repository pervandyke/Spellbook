package com.vandyke.demoproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vandyke.demoproject.model.Login;

@Controller
public class LoginController {
    @RequestMapping(value = "/login-page", method = RequestMethod.GET)
    public String getHomePage() {
        return "LoginPage";
    }
    
    @RequestMapping(value = "/spellbook", method = RequestMethod.POST)
    public String login(@ModelAttribute("login") Login login, ModelMap model) {

        if (login != null && login.getUsername() != "" && login.getPassword() != "") {
            model.addAttribute("name", login.getUsername());
            return "SpellbookPage";
        } else {
            model.addAttribute("error", "Try Again");
            return "LoginPage";
        }
    }
}
