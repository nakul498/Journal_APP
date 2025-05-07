package com.educ.journalApp.Repository;

import com.educ.journalApp.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepoImpl {

        @Autowired
        private MongoTemplate mongoTemplate;

        public List<Users> getUserForSA(){
            Query query = new Query();
            query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));
            query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
            List<Users> users = mongoTemplate.find(query, Users.class);
            return users;
        }
}
