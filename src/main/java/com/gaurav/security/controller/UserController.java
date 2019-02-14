package com.gaurav.security.controller;

import com.gaurav.security.entity.Authority;
import com.gaurav.security.entity.User;
import com.gaurav.security.repository.AuthorityRepository;
import com.gaurav.security.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    private AuthorityRepository authorityRepository;
    private UserRepository userRepository;

    public UserController(AuthorityRepository authorityRepository, UserRepository userRepository) {
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/userForm")
    public String userForm(Model model){
        model.addAttribute("user",new User());
        return "user-form";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user){
        Authority authority = authorityRepository.findById(1L).orElse(null);
        user.addAuthority(authority);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/";
    }
}
