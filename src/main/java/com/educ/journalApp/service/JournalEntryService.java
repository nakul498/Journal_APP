package com.educ.journalApp.service;

import com.educ.journalApp.Repository.JourneyRepo;
import com.educ.journalApp.entity.JournalEntry;
import com.educ.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JourneyRepo journeyRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            Users user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry save = journeyRepo.save(journalEntry);
            user.getJournalEntryList().add(save);
            userService.saveEntry(user);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("transactional check", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
         journeyRepo.save(journalEntry);
    }


    public List<JournalEntry> getAll(){
        return journeyRepo.findAll();
    }
    
    public Optional<JournalEntry> getFromId(ObjectId id){
        return journeyRepo.findById(id);
    }
    @Transactional
    public void deleteFromId(ObjectId id, String userName) throws RuntimeException {
       try {
           Users user = userService.findByUserName(userName);
           boolean removed = user.getJournalEntryList().removeIf(x -> x.getId().equals(id));
           if (removed) {
               userService.saveEntry(user);
               journeyRepo.deleteById(id);
           }
       }catch (Exception e){
           System.out.println(e);
           throw new RuntimeException("An error occurred while deleting the entry", e);
       }
    }

//    public List<JournalEntry> findByUserName(String userName){
//        return journeyRepo.findByUserName(userName);
//    }
}
