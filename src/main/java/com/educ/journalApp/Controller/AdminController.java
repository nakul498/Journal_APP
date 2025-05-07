package com.educ.journalApp.Controller;

import com.educ.journalApp.entity.Users;
import com.educ.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @GetMapping("/all-Users")
    public ResponseEntity<List<Users>> getAllUsers(){
         List<Users> all = userService.getAll();
         if(all != null || !all.isEmpty()){
                return new ResponseEntity<>(all, HttpStatus.ACCEPTED);
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create-admin-user")
    public void createUser(@RequestBody Users user){
        userService.saveAdmin(user);
    }
}
