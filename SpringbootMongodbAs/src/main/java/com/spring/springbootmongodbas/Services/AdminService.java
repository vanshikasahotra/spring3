package com.spring.springbootmongodbas.Services;

import com.spring.springbootmongodbas.Models.Admin;
import com.spring.springbootmongodbas.Models.User;
import com.spring.springbootmongodbas.Repository.AdminRepo;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;

@Service
public class AdminService {
   AdminRepo adminRepo;
    TokenService tokenService;
    AdminService(AdminRepo adminRepo, TokenService tokenService)
    {
        this.adminRepo=adminRepo;
        this.tokenService = tokenService;

    }

    public String signup(Admin admin) {

        adminRepo.save(admin);
        return "{" + "\"message\":" + "Admin Sucessfully created user\",\n" +
                "\"data\":" + admin.toString() + ",\n" + "}";
    }
    //login as admin
    public String login(String email, String password) {
        List<Admin> foundUsers = adminRepo.getAdminByEmail(email);//getuser by email is the function to get user detail from the given email.


        if (foundUsers.isEmpty()) {
            return "Authentication Failed User Not Found";
            //get(0) means it will check if that 1st user is having the same password
        } else if (!foundUsers.get(0).getPassword().equals(password)) {
            //it will check if the given password is matched with stored password or not
            //if yes then it will printed
            return "Password Incorrect";
        }

        String token= tokenService.createToken(foundUsers.get(0).getId());

        return "{\n" +
                "\"message\": \"Successfully Logged-in\",\n" +
                "\"data\": {\n" +
                "  \"Name\": \"" + foundUsers.get(0).getName() + "\",\n" +
                "  \"Email\": \"" + foundUsers.get(0).getEmail() + "\"\n" +
                "},\n" +
                "'token': '"+token+"' \n}";
 }
}
