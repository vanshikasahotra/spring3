package com.spring.springbootmongodbas.Repository;

import com.spring.springbootmongodbas.Models.Admin;
import com.spring.springbootmongodbas.Models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.cdi.MongoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepo extends MongoRepository<Admin, ObjectId> {

    @Query("{ email: \"?0\" }")
    List<Admin> getAdminByEmail(String email);
}

