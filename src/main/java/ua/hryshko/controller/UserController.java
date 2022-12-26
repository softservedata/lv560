package ua.hryshko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.hryshko.model.User;
import ua.hryshko.security.PasswordConfig;
import ua.hryshko.security.SecurityUser;
import ua.hryshko.service.RoleService;
import ua.hryshko.service.UserService;


@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    private final PasswordConfig passwordConfig;

    @Autowired
    public UserController(PasswordConfig passwordConfig,UserService userService,RoleService roleService) {
        this.passwordConfig = passwordConfig;
        this.userService = userService;
        this.roleService = roleService;
    }


    @RequestMapping("/")
    public String mainPage(@AuthenticationPrincipal SecurityUser securityUser) {
        if (securityUser.getRole().equals("admin")) {
            return "redirect:/all";
        } else return "redirect:/menu";

//        else return "redirect:/show/gasometers/" + securityUser.getId();

    }


    @RequestMapping(value = "/menu")
    public String menu(@AuthenticationPrincipal SecurityUser securityUser,Model model){

        model.addAttribute("owner",securityUser);

        return "menu";
    }

    @RequestMapping("/create")
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        return "user_create";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user,
                          BindingResult result) {
        user.setRole(roleService.readById(1L));
        String crypto = passwordConfig.passwordEncoder().encode(user.getPassword());
        user.setPassword(crypto);
        userService.addUser(user);
        return "redirect:/all";
    }




    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    @PreAuthorize("!hasAuthority('user')  or #id == authentication.principal.id")
    public String update(@PathVariable Long id, Model model) {
        User user = userService.readById(id);
        model.addAttribute("user", user);
        return "user_edit";
    }

    @RequestMapping(value = "/editsave/{id}", method = RequestMethod.POST)
    @PostAuthorize("!hasAuthority('user')  or #id == authentication.principal.id")
    public String update(@PathVariable("id") Long id,@ModelAttribute("user") User user,
                         BindingResult result) {

        user.setId(id);
        user.setRole(roleService.readById(1L));
        userService.update(user);

        return "redirect:/all";
    }


    @RequestMapping(value = "/delete/{userId}")
    @PreAuthorize("!hasAuthority('user')  or #userId == authentication.principal.id")
    public String deleteUser(@PathVariable("userId") Long userId) {
        userService.removeUser(userId);
        return "redirect:/all";
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @PreAuthorize("!hasAuthority('user')")
    public String all(Model model) {
        model.addAttribute("userList", userService.listUsers());
        return "user_list";
    }


}
