package com.spring.springbootmongodbas.Services;
import org.bson.types.ObjectId;
import com.spring.springbootmongodbas.Models.User;
import com.spring.springbootmongodbas.Repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;


import java.util.Optional;
@Service
public class UserService {
  private UserRepo userRepo;

  private TokenService tokenService;

  public UserService(UserRepo userRepo, TokenService tokenService) {
    this.userRepo = userRepo;
    this.tokenService = tokenService;
  }

  //get an user
  public User getUser(ObjectId id) {
    Optional<User> optionalUser = userRepo.findById(id);
    return optionalUser.orElseGet(optionalUser::get);
  }

  //list all User
  public List<User> getUser() {
    List<User> getUsers = userRepo.findAll();
    return getUsers;

  }
//create a new user
  //signup as user
  public String signup(User user) {
    userRepo.save(user);
    return "{" + "\"message\":" + "Successfully created user\",\n" +
            "\"data\":" + user + ",\n" + "}";
  }



  //login as User
  public String login(String email, String password) {
    List<User> foundUsers = userRepo.getUserByemail(email);
    //getuser by email is the function to get user detail from the given email.



    if (foundUsers.isEmpty()) {
      return "Authentication Failed  Not Found";
      //get(0) means it will check if that 1st user is having the same password
    } else if (!foundUsers.get(0).getUser_password().equals(password)) {
      //it will check if the given password is matched with stored password or not
      //if yes then it will printed
      return "Password Incorrect";
    }

   // String userId = foundUsers.get(0).getId().toString();

    return "{\n" +
            "\"message\": \"User Successfully Logged-in\",\n" +
            "\"data\": {\n" +
            "  \"Name\": \"" + foundUsers.get(0).getUser_name() + "\",\n" +
            "  \"Email\": \"" + foundUsers.get(0).getUser_email() + "\"\n" +
            "\"n" +

            "}";


  }
  //list all employee
  public List<User> viewAll() {
    List<User> getUsers = userRepo.findAll();
    return getUsers;

  }
  //delete a user
  public String deleteById(String strId)
  {
    ObjectId id = new ObjectId(strId);
    Optional<User> foundUser = userRepo.findById(id);
    //getuser by email is the function to get user detail from the given email.



    if (foundUser.isEmpty()) {
      return "User Not Found";
      //get(0) means it will check if that 1st user is having the same password
    }

    // String userId = foundUsers.get(0).getId().toString();

    return "{\n" +
            "\"message\": \"User Successfully deleted\",\n" +
            "\"data\": {\n" +
            "  \"Name\": \"" + foundUser.get().getUser_name() + "\",\n" +
            "  \"Email\": \"" + foundUser.get().getUser_email() + "\"\n" +
            "\"n" +

            "}";


  }
//update the user
  public String updateUser(String strId, User user)
  {
    ObjectId objectId=new ObjectId(strId);

    Optional<User> optUser = userRepo.findById(objectId);

    if(optUser.isPresent())
    {
      User updatedUser =optUser.get();

      if(user.getUser_name() != null && !user.getUser_name().isEmpty() )
      {
        updatedUser.setUser_name(user.getUser_name());
      }


      if(user.getUser_email() != null && !user.getUser_email().isEmpty())
        updatedUser.setUser_email(user.getUser_email());

      userRepo.save(updatedUser);



      return "";
    }
    return null;
  }
//  //delete a user
//  public boolean deleteAccountById(String s) {
//    try {
//      ObjectId objectId=new ObjectId(s);
//      userRepo.deleteById(objectId);
//      return true;
//    } catch (Exception ex) {
//      return false;
//    }
//
//  }
  //create a user
//  public void updatethepassword(String strid,User user)
//  {
//    ObjectId k=new ObjectId(strid);
//    Optional<User>k2=userRepo.findById(k);
//    if(k2.isPresent())
//    {
//      User user1=k2.get();
//      if(user1.getUser_password()!=null&&!user1.getUser_password().isEmpty())
//      {
//        user1.getUser_password()
//      }
//    }
//
//  }
  }

