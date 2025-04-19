package com.kamruddin.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppUserController {
    @Autowired
    private AppUserService appUserService;

    @GetMapping("/")
    public String loadAddUserPage(Model model) {
        model.addAttribute("appUser", new AppUser());
        return "addUser"; // Renders the addUser.html template
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute AppUser appUser, Model model) {
        appUserService.createUser(appUser);
        model.addAttribute("message", "User added successfully!");
        model.addAttribute("users", appUserService.getAllUsers());
        return "showUsers"; // Redirect to a success page or another view
    }

    @GetMapping("/users")
    public String loadAllUsersPage(Model model) {
        model.addAttribute("users", appUserService.getAllUsers());
        return "showUsers"; // Renders the allUsers.html template
    }
}
