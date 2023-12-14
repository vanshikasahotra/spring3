package com.spring.springbootmongodbas.Repository;

import com.spring.springbootmongodbas.Models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepo extends MongoRepository<User, ObjectId> {

    @Query("{ user_email: \"?0\" }")
    List<User> getUserByemail(String email);
}
