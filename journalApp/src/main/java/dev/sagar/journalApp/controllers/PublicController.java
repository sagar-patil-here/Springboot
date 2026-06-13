package dev.sagar.journalApp.controllers;

import dev.sagar.journalApp.Services.UserServices;
import dev.sagar.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class PublicController {
    @Autowired
    private UserServices Us;

    @GetMapping
    public String test() {
        return "Working";
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody User user) {

        if (Us.saveNewUser(user)) {
            return new ResponseEntity<>("New User Created !!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Went wrong Check log", HttpStatus.CONFLICT);
        }

    }

}
