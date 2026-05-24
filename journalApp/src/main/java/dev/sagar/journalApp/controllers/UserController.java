package dev.sagar.journalApp.controllers;

import dev.sagar.journalApp.Services.UserServices;
import dev.sagar.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServices Us;
    @GetMapping
    public List<User> getall(){
        return Us.findAll();
    }
    @GetMapping("/{username}")
    public User getUserFromUserName(@PathVariable String username){
       User user = Us.findByuserName(username);
       return user;
    }
    @PutMapping("/{username}")
    public User update (@PathVariable String username ,@RequestBody User u){
        User user = Us.findByuserName(username);
        if(user!=null){
            user.setUserName(u.getUserName());
            user.setPassword(u.getPassword());
            Us.saveUser(user);
        }

        return user;
    }

    @PostMapping
    public String add(@RequestBody User user){
        Us.saveUser(user);
        return "New User Created !!";
    }
}

