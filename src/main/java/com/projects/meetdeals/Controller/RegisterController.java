package com.projects.meetdeals.Controller;

import com.projects.meetdeals.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.projects.meetdeals.Service.RegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class RegisterController {
    private RegisterService registerService;

         @Autowired
         public RegisterController(RegisterService registerService) {
              this.registerService = registerService;
         }

         @PostMapping("/user/register")
         public void addGuest(@RequestBody User user) {
             registerService.add(user);
          }

}
