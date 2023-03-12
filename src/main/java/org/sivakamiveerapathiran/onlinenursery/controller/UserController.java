/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the controller methods for the User  Functionalities.
 * Methods:
 * createUser - This Method is used to create a User in DB.
 ***************************/
package org.sivakamiveerapathiran.onlinenursery.controller;

import org.sivakamiveerapathiran.onlinenursery.service.UserService;
import org.sivakamiveerapathiran.onlinenursery.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class UserController {

     private UserService userService;
    @GetMapping("/user/register")
    public String registerForm(Model model) {
        log.info("Inside registerForm Method");
        model.addAttribute("user",new User() );
        log.info("Loading register page");
        return "register";
    }

  @Autowired
  public UserController(UserService userService) {

        this.userService = userService;
  }

  @PostMapping("/user/addUser")
   public  String createUser(@ModelAttribute("user")User user)
        {
            log.info("Inside createUser Method");
         String response="";

     userService.createUser(user);
            log.info("redirect:/product/listAllProducts");

     return "redirect:/product/listAllProducts";
        }

}
