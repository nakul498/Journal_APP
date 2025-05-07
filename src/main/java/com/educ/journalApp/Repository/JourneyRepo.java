package com.educ.journalApp.Repository;

import com.educ.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JourneyRepo extends MongoRepository<JournalEntry, ObjectId> {
   // List<JournalEntry> findByUserName(String userName);
}
