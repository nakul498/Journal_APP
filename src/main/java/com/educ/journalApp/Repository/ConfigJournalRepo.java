package com.educ.journalApp.Repository;

import com.educ.journalApp.entity.CongifJournalEntity;
import com.educ.journalApp.entity.Users;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalRepo extends MongoRepository<CongifJournalEntity, ObjectId> {

}
