package com.spring.springbootmongodbas.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private ObjectId id;

    private String user_name;
    private String user_password;
    private String user_email;

    @Override
    public String toString() {
        return "User{" +
                "User_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", User_email='" + user_email + '\'' +
                ", User_id='" + id + '\'' +
                '}';
    }
}
