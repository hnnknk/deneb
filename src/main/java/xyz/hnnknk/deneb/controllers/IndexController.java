package xyz.hnnknk.deneb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.hnnknk.deneb.service.UserService;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }
}
