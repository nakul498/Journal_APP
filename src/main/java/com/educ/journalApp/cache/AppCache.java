package com.educ.journalApp.cache;

import com.educ.journalApp.Repository.ConfigJournalRepo;
import com.educ.journalApp.entity.CongifJournalEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class AppCache {

    @Autowired
    private ConfigJournalRepo configJournalRepo;

    public Map<String, String> APP_CACHE = new HashMap<>();

    @PostConstruct
    public void init(){
        List<CongifJournalEntity> all = configJournalRepo.findAll();
        for(CongifJournalEntity congifJournalEntity : all){
            APP_CACHE.put(congifJournalEntity.getKey(), congifJournalEntity.getValue());
        }
        //APP_CACHE = null;
    }
}
