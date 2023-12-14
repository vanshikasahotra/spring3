package com.spring.springbootmongodbas.Controller;

import com.spring.springbootmongodbas.Models.Admin;
import com.spring.springbootmongodbas.Models.User;
import com.spring.springbootmongodbas.Repository.AdminRepo;
import com.spring.springbootmongodbas.Repository.UserRepo;
import com.spring.springbootmongodbas.Services.AdminService;
import com.spring.springbootmongodbas.Services.UserService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequestMapping("/admin")
@RestController()

public class AdminController {

 AdminService adminService;
 UserService userService;
 public  AdminController(AdminService adminService, UserService userService) {

  this.adminService = adminService;
  this.userService=userService;
 }

 //sign up as Admin
 @PostMapping("/signup")
 public String Signup(@RequestBody Admin admin)
 {
  return adminService.signup(admin);
 }

//login as Admin
@PostMapping(value="/login")
 public String login(@RequestBody Map<String, Object> map)
 {

  return adminService.login(map.get("email").toString(),map.get("password").toString());

 }
 //admin can view all the employee
 @GetMapping(value="/viewall")
 public List<User> viewAll()
 {
  return userService.viewAll();
 }
 //adin can delete any employee
@PostMapping(value="/delete")
 public void delete(String strid)
 {
 userService.deleteById(strid);
 }
//admin can update the employee
 @PostMapping(value="/update")
 public void update(String strid,User user)
 {
  userService.updateUser(strid,user);
 }


}
