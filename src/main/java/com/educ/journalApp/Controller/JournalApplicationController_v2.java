package com.educ.journalApp.Controller;

import com.educ.journalApp.entity.JournalEntry;
import com.educ.journalApp.entity.Users;
import com.educ.journalApp.service.JournalEntryService;
import com.educ.journalApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/journal")
public class JournalApplicationController_v2 {

    @Autowired
    private JournalEntryService journalEntryService;

    private static final Logger logger = LoggerFactory.getLogger(JournalApplicationController_v2.class);

    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<Object> getAllJournalEntriesOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = userService.findByUserName(authentication.getName());
        logger.info("user verified");
        List <JournalEntry> all = user.getJournalEntryList();
        if(all!=null && !all.isEmpty()){

            return  new ResponseEntity<>(all, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @GetMapping("/Id/{myid}")
//    public Optional<JournalEntry> getId(@PathVariable ObjectId myid){
//        return journalEntryService.getFromId(myid);
//    }
    @GetMapping("Id/{myid}")
    public ResponseEntity<JournalEntry> getId(@PathVariable ObjectId myid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = userService.findByUserName(authentication.getName());
        List<JournalEntry> collect = user.getJournalEntryList().stream().filter(x->x.getId().equals(myid)).toList();
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.getFromId(myid);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry){
      try {
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          journalEntryService.saveEntry(entry, authentication.getName());
          return new ResponseEntity<>(entry, HttpStatus.CREATED);
      }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }

    }

    @DeleteMapping("Id/{myId}")
    public ResponseEntity<Object> deleteEntry(@PathVariable ObjectId myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         journalEntryService.deleteFromId(myId, authentication.getName());
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/Id/{id}")
    public ResponseEntity<JournalEntry> updateById(@PathVariable ObjectId id, @RequestBody JournalEntry myEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = userService.findByUserName(authentication.getName());
       journalEntryService.getFromId(id).orElse(null);
        List<JournalEntry> collect = user.getJournalEntryList().stream().filter(x->x.getId().equals(id)).toList();
        if(!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.getFromId(id);
            if (journalEntry.isPresent()) {
                JournalEntry oldjournalEntry =journalEntry.get();
                oldjournalEntry.setTitle(!myEntry.getTitle().isEmpty() ? myEntry.getTitle():oldjournalEntry.getTitle());
                oldjournalEntry.setContent(myEntry.getContent()!=null && !myEntry.getContent().isEmpty() ? myEntry.getContent():oldjournalEntry.getContent());
                oldjournalEntry.setDate(LocalDateTime.now());
                journalEntryService.saveEntry(oldjournalEntry);
                return new ResponseEntity<>(oldjournalEntry, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
