package com.educ.journalApp.Repository;

import com.educ.journalApp.entity.JournalEntry;
import com.educ.journalApp.entity.Users;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<Users, ObjectId> {
    Users findByUserName(String username);

    void deleteByUserName(String name);
}
