package web.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import web.models.User;

import java.util.List;

@Controller
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String allUsersPage(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/users/show")
    public String show(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/show";
    }

    @GetMapping("/users/edit")
    public String editUserPage(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/users/edit";
    }

    @PutMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/users/new")
    public String createUserForm(@ModelAttribute("user") User user) {
        return "/users/new";
    }

    @PostMapping()
    public String addUserToDb(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @DeleteMapping(value = "/users/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.removeUser(id);
        return "redirect:/";
    }
}
