package org.sivakamiveerapathiran.onlinenursery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuBarController {
    @GetMapping("/")
    public String launchDefault(){
        return "HomeScreen";
    }

    @GetMapping("/menu/about")
    public String launchAboutus(){
        return "About";
    }

    @GetMapping("/menu/contactUs")
    public String launchcontactUs(){
        return "contactUs";
    }

    @GetMapping("/menu/home")
    public String launchHome(){
        return "HomeScreen";
    }

    @GetMapping("/menu/allProducts")
    public String launchAllProducts(){
        return "redirect:/product/listAllProducts";
    }

    @GetMapping("/menu/login")
    public String launchLogin(){
        return "redirect:/login";
    }

    @GetMapping("/menu/registration")
    public String launchRegistration(){
        return "redirect:/user/register";
    }
}
