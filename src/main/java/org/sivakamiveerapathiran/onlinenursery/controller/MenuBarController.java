/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the controller methods for the menu Bar items in all screens.
 * Methods:
 * launchDefault -  This method is used to launch the home screen when using localhost:8080/
 * launchAboutus -  This method is used to launch the About us screen
 * launchcontactUs -  This method is used to launch the contact Us  screen
 * launchHome -  This method is used to launch the home screen when launching localhost:8080/menu/home
 * launchAllProducts -  This method is used to launch the Products screen
 * launchLogin -  This method is used to launch the Login screen
 * launchRegistration -  This method is used to launch the Registration screen
 ***************************/

package org.sivakamiveerapathiran.onlinenursery.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Slf4j
@Controller
public class MenuBarController {
    @GetMapping("/")
    public String launchDefault(){
        log.info("Inside launchDefault Method");
        log.info("Loading HomeScreen Page");
        return "HomeScreen";
    }

    @GetMapping("/menu/about")
    public String launchAboutus(){
        log.info("Inside launchAboutus Method");
        log.info("Loading About Page");
        return "About";
    }

    @GetMapping("/menu/contactUs")
    public String launchcontactUs(){
        log.info("Inside launchcontactUs Method");
        log.info("Loading contactUs Page");
        return "contactUs";
    }

    @GetMapping("/menu/home")
    public String launchHome(){
        log.info("Inside launchHome Method");
        log.info("Loading HomeScreen Page");
        return "HomeScreen";
    }

    @GetMapping("/menu/allProducts")
    public String launchAllProducts(){
        log.info("Inside launchAllProducts Method");
        log.info("redirect:/product/listAllProducts");
        return "redirect:/product/listAllProducts";
    }

    @GetMapping("/menu/login")
    public String launchLogin(){
        log.info("Inside launchLogin Method");
        log.info("redirect:/login");
        return "redirect:/login";
    }

    @GetMapping("/menu/registration")
    public String launchRegistration(){
        log.info("Inside launchRegistration Method");
        log.info("redirect:/user/register");
        return "redirect:/user/register";
    }
}
