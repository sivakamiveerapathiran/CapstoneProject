/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the controller methods for the loading the custom login screen.
 * Methods:
 * loadLoginPage - This method is used to load the custom login page
 ***************************/

package org.sivakamiveerapathiran.onlinenursery.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Slf4j
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loadLoginPage(Model model){

        log.info("Inside loadLoginPage Method");
        log.info("Loading logincustom Page");
        return "logincustom";
    }

}
