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

    //private static final Logger logger
     //       = LoggerFactory.getLogger(UserController.class);
     private UserService userService;
    @GetMapping("/user/register")
    public String registerForm(Model model) {

        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");
        model.addAttribute("user",new User() );
        return "register";
    }

  @Autowired
  public UserController(UserService userService) {

        this.userService = userService;
  }

    @PostMapping("/user/addUser")
   public  String createUser(@ModelAttribute("user")User user)
        {

         String response="";
//try {
     userService.createUser(user);
     response="User created";
//}
/*catch(Exception e){
    e.printStackTrace();
    response=e.toString();
    log.info(e.toString());
}*/
// return ResponseEntity.status(HttpStatus.CREATED).body(response);
            return "redirect:/product/listAllProducts";
        }

}
