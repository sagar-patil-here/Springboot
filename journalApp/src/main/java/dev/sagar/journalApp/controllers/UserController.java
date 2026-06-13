package dev.sagar.journalApp.controllers;

import dev.sagar.journalApp.MongoConnRepo.UserRepo;
import dev.sagar.journalApp.Services.UserServices;
import dev.sagar.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserServices us;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo ur;


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User Dbuser = us.findByuserName(userName);
        Dbuser.setUserName(user.getUserName());
//        Dbuser.setPassword(passwordEncoder.encode(user.getPassword()));
        Dbuser.setPassword(user.getPassword());
        Dbuser.setRoles(Arrays.asList("User"));
        us.saveNewUser(Dbuser);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ur.deleteByUserName(authentication.getName());
        return new ResponseEntity<>("User deleted !!", HttpStatus.OK);
    }


//    @GetMapping
//    public List<User> getall() {
//        return Us.findAll();
//    }


//    @GetMapping("/{username}")
//    public User getUserFromUserName(@PathVariable String username){
//       User user = Us.findByuserName(username);
//       return user;
//    }


//    @PutMapping("/{username}")
//    public User update(@PathVariable String username, @RequestBody User u) {
//        User user = Us.findByuserName(username);
//        if (user != null) {
//            user.setUserName(u.getUserName());
//            user.setPassword(u.getPassword());
//            Us.saveUser(user);
//        }
//
//        return user;
//    }


}
