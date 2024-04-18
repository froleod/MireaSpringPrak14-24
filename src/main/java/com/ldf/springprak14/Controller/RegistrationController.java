package com.ldf.springprak14.Controller;

import com.ldf.springprak14.Service.AppService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.ldf.springprak14.Entity.User;

@RestController
@AllArgsConstructor
public class RegistrationController {

    private AppService appService;

//    @GetMapping("/registration")
//    public String registration(){
//        return "registration";
//    }

    @PostMapping("/registration")
    public String addUser(@RequestBody User user) {
        appService.addUser(user);
        return "User is saved!";
    }
}
