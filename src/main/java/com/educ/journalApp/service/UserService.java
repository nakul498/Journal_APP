package com.educ.journalApp.service;

import com.educ.journalApp.Repository.JourneyRepo;
import com.educ.journalApp.Repository.UserRepo;
import com.educ.journalApp.entity.JournalEntry;
import com.educ.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveEntry(Users user){
        userRepo.save(user);
    }
    public void saveNewEntry(Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("ADMIN"));
        userRepo.save(user);
    }

    public List<Users> getAll(){
        return userRepo.findAll();
    }
    
    public Optional<Users> getFromId(ObjectId id){
        return userRepo.findById(id);
    }

    public void deleteFromId(ObjectId id){
        userRepo.deleteById(id);
    }

    public Users findByUserName(String userName){
        return userRepo.findByUserName(userName);
    }

    public void saveAdmin(Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("ADMIN"));
        userRepo.save(user);
    }
}
