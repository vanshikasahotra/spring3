package com.spring.springbootmongodbas.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Admin {
    
    private String name;
    private String password;
    private String email;
    private ObjectId id;

    @Override
    public String toString() {
        return "Admin{" +
                "A_name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
