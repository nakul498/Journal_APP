//package com.educ.journalApp.Controller;
//
//import com.educ.journalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//
//@RestController
//@RequestMapping("/_journal")
//public class JournalApplicationController {
//    private Map<Long, JournalEntry> map = new HashMap<>();
//
//    @GetMapping("/getAll")
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(map.values()) ;
//    }
//
//    @GetMapping("/getById/{myid}")
//    public JournalEntry getId(@PathVariable long myid){
//        return map.get(myid);
//    }
//
//    @PostMapping
//    public boolean getData(@RequestBody JournalEntry entry){
//        map.put(entry.getId(), entry);
//        return true;
//    }
//
//    @DeleteMapping("id/{myId}")
//    public JournalEntry deleteEntry(@PathVariable Long myId){
//        return map.remove(myId);
//    }
//    @PutMapping("/id/{id}")
//    public JournalEntry updateById(@PathVariable Long id, @RequestBody JournalEntry myEntry){
//        return map.put(id, myEntry);
//    }
//
//
//}
