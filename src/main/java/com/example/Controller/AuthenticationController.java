package com.example.Controller;

import com.example.Repo.UserRepository;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthenticationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String show() {
        return "home";
    }

    // Handler for showing the registration page
    @GetMapping("/registration")
    public String registrationPage(User user) {
        return "registrationPage";
    }

    // Handler for showing the login page
    @GetMapping("/loginPage")
    public String loginPage(Model model) {
        model.addAttribute("user", new User()); // Ensure the user object is available in the model
        return "loginPage";
    }

    // Handler for the registration process
    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, Model model) {
        // Check if the email already exists in the database
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            // If the email already exists, return to the registration page with an error message
            model.addAttribute("error", "Email already exists");
            return "registrationPage";
        } else {
            // If the email does not exist, save the user and redirect to the login page
            userRepository.save(user);
            return "redirect:/loginPage";
        }
    }

    // Handler for the login process
    @PostMapping("/login")
    public String loginProcess(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               Model model) {
        // Find the user by email
        User dbUser = userRepository.findByEmail(email);
        if (dbUser != null && dbUser.getPassword().equals(password)) {
            // If the email and password match, redirect to the form page
            model.addAttribute("firstname", dbUser.getFirstname());
            return "redirect:/form";
        }
        // If the login details are incorrect, return to the login page with an error message
        model.addAttribute("error", "Invalid email or password");
        model.addAttribute("user", new User()); // Ensure the user object is available in the model
        return "loginPage";
    }
}
