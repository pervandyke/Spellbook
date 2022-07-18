package com.vandyke.demoproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(Model model, @ModelAttribute("login") Login login) {
		System.out.println("Login request recieved!");
		System.out.println("Username was: " + login.getUserName());
		if (login != null && login.getUserName() != null & login.getPassword() != null) {
			model.addAttribute("user", login.getUserName());
			return "SpellbookPage";
		} else {
			model.addAttribute("error", "Try Again");
			return "LoginPage";
		}
	}
}
