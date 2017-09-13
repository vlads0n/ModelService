package main.java.controller;

import main.java.model.User;
import main.java.service.IUserService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



/**
 * Created by Влад on 22.08.2017.
 */
@Controller
public class UserController {

    @Autowired
    private IUserService service;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accounts", service.getAll());
        return "index";
    }

    @GetMapping("/user/create")
    public String createUser(@ModelAttribute User account, Model model) {
        return "user_create";
    }

    @GetMapping("/user/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User account, Model model) {
        service.getAll().forEach(
                user -> {
                    if (user.getId().longValue() == id.longValue()) {
                        account.setId(user.getId());
                        account.setName(user.getName());
                        model.addAttribute("account", account);
                    }
                }
        );
        return "user_update";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        service.deleteUser(id);
        return "redirect:/";
    }

    @PostMapping("/user/save")
    public String saveUser(@ModelAttribute User account, Model model) {
        if (account.getId() == null)
            service.addUser(account);
        else
            service.updateUser(account);
        return "redirect:/";
    }
}
