package com.educ.journalApp.Controller;

import com.educ.journalApp.entity.Users;
import com.educ.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/healthCheck")
    public String healthCheck(){
        return "OK";
    }

    @PostMapping ("/create-user")
    public void createUser(@RequestBody Users user){
        userService.saveNewEntry(user);
    }

}
