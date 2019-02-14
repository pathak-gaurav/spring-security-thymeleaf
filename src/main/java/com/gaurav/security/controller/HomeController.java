package com.gaurav.security.controller;

import com.gaurav.security.repository.AuthorityRepository;
import com.gaurav.security.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;

    public HomeController(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @RequestMapping("/")
    public String showHome(Model model) {
        model.addAttribute("users",userRepository.findAll());
        return "home";
    }
}
