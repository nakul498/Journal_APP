package com.educ.journalApp.Controller;

import com.educ.journalApp.Repository.UserRepo;
import com.educ.journalApp.api.response.WeatherResponse;
import com.educ.journalApp.entity.Users;
import com.educ.journalApp.service.UserService;
import com.educ.journalApp.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private WeatherService weatherService;

//    @GetMapping
//    public ResponseEntity<List<Users>> getAll(){
//        List<Users> all = userService.getAll();
//        if(all!=null && !all.isEmpty()){
//            return new ResponseEntity<>(all, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//    }
    @GetMapping
    public List<Users> getAllUsers(){
        log.debug("i a, here ");
        return userService.getAll();
    }

//    @GetMapping("/id/{myId}")
//    public ResponseEntity<Optional<Users>> getByID(@PathVariable ObjectId id){
//        Optional<Users> user = userService.getFromId(id);
//        if(user.isPresent()){
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody Users user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Users userInDb = userService.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveEntry(userInDb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepo.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/welcome")
    public ResponseEntity<?> welcome(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greeting = "";
        if(weatherResponse != null){
            greeting = ", weather feels like "+ weatherResponse.getCurrent().getFeelsLike();
        }
        return new ResponseEntity<>("Hi "+ authentication.getName() + greeting , HttpStatus.OK);
    }


}
