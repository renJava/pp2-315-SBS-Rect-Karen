package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    static final String rolesS = "roles";

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String printUsers(Model model, @ModelAttribute("newUser") User newUser, Principal principal) {
        User authenticatedUser = userService.findByUsername(principal.getName());
        model.addAttribute("authenticatedUser", authenticatedUser);
        model.addAttribute("authenticatedUserRoles", authenticatedUser.getRoles());
        model.addAttribute("users", userService.findAll());
        model.addAttribute(rolesS, roleService.getAllRoles());
        return "user-admin";
    }

    @GetMapping("/user-create")
    public String createUserForm(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("currentUser", userService.findByUsername(userDetails.getUsername()));
        model.addAttribute(rolesS, roleService.getAllRoles());
        model.addAttribute("user", new User());
        return "user-create";
    }

    static final String REDIRECT_ADMIN = "redirect:/admin";

    @PostMapping
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
                          @ModelAttribute("currentUser") User currentUser,
                          @ModelAttribute("roles") List<Role> roles) {
        if (bindingResult.hasErrors()) {
            return "user-create";
        }

        userService.saveUser(user);
        return REDIRECT_ADMIN;
    }

    @DeleteMapping("/user-delete/{id}")
    public String deleteUserFromTable(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return REDIRECT_ADMIN;
    }

    @PatchMapping("/user-create/{id}") //TODO user-create вместо user-update
    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return REDIRECT_ADMIN;
        }

        userService.saveUser(user);
        return REDIRECT_ADMIN;
    }

}
