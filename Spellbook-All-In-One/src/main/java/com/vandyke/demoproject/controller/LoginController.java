package com.vandyke.demoproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vandyke.demoproject.dao.UserDao;
import com.vandyke.demoproject.exceptions.UserAlreadyExistsException;
import com.vandyke.demoproject.exceptions.UserNotFoundException;
import com.vandyke.demoproject.model.Login;
import com.vandyke.demoproject.model.User;

@Controller
public class LoginController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/login-page", method = RequestMethod.GET)
    public String getHomePage() {
        return "LoginPage";
    }
    
    @RequestMapping(value = "/spellbook", method = RequestMethod.POST)
    public String login(@ModelAttribute("login") Login login, ModelMap model) {
        User user = null;
        
        try {
            user = userDao.findUserByUsername(login.getUsername());
            if (user.getPassword().equals(login.getPassword())) {
                model.addAttribute("name", login.getUsername());
                model.addAttribute("userId", user.getId());
                return "SpellbookPage";
            } else {
                model.addAttribute("error", "Password Incorrect");
                return "LoginPage";
            }
            
        } catch (UserNotFoundException e) {
            model.addAttribute("error", "Username not found");
            return "LoginPage";
        }
    }

    @RequestMapping(value = "/register-page", method = RequestMethod.GET)
    public String getRegisterPage() {
        return "RegisterPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("register") Login register, ModelMap model){

        try {
            userDao.createUser(register);
            User user = userDao.findUserByUsername(register.getUsername());
            model.addAttribute("name", user.getUsername());
            model.addAttribute("userId", user.getId());
            return "SpellbookPage";
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("error", "User Already Exists");
            return "RegisterPage";
        } catch (UserNotFoundException e) {
            model.addAttribute("error", "Server Error");
            return "RegisterPage";
        }
    }
}
