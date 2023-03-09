package org.sivakamiveerapathiran.onlinenursery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loadLoginPage(Model model){
        return "logincustom";
    }

}
