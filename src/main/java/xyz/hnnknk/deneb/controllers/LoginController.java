package xyz.hnnknk.deneb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping(value = "/t")
    public String index() {
        return "t";
    }

    @RequestMapping(value = "/t/hello")
    public String a() {
        return "hello";
    }
}