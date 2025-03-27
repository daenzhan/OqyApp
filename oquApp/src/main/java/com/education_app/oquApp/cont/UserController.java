package com.education_app.oquApp.cont;

import com.education_app.oquApp.model.User;
import com.education_app.oquApp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "userlist";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "user_create";
    }

    @PostMapping("/create")
    public String createUser( @ModelAttribute("user") User user,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "user_create";
        }

        if (userService.findByEmail(user.getEmail()).isPresent()) {
            result.rejectValue("email", "error.user", "Email already exists");
            return "user_create";
        }

        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("success", "User created successfully");
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "user_edit";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id,  @ModelAttribute("user") User user,
                             BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "user_edit";
        }

        user.setUser_id(id);
        userService.updateUser(user);
        redirectAttributes.addFlashAttribute("success", "User updated successfully");
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("success", "User deleted successfully");
        return "redirect:/users";
    }
}