package com.spring.springbootmongodbas.Controller;

import com.spring.springbootmongodbas.Models.User;
import com.spring.springbootmongodbas.Repository.UserRepo;
import com.spring.springbootmongodbas.Services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequestMapping("/user" )
@RestController
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService)
    {
        this.userService=userService;
    }


    //login as user
    @PostMapping(value="/login")
    public String login(@RequestBody Map<String, Object> map)
    {

        return userService.login(map.get("user_email").toString(),map.get("user_password").toString());

    }
    //to fetch the user by id
    @GetMapping(value="/get-user")
    public User getUser(HttpServletRequest request)
    {
        ObjectId userId=(ObjectId) request.getAttribute("userId");
        return userService.getUser(userId);
    }

    //able to view all employee
@PostMapping(value = "/viewall")
    public List<User>viewall()
{
    return userService.viewAll();
}
    //  creating a user
    @PostMapping(value = "/add")
 public String signup(@RequestBody User user)
    {
       return userService.signup(user);
     }
//edit the employee
@PostMapping(value = "/edit")
    public void edit(@RequestBody User user,String strId )
{
    userService.updateUser(strId,user);
}
    //delete the employee
    @PostMapping("/delete")
    public void delete(@RequestBody String strId)
    {
        userService.deleteById(strId);
    }

}
