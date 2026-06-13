package dev.sagar.journalApp.controllers;

import dev.sagar.journalApp.MongoConnRepo.JouneralRepo;
import dev.sagar.journalApp.MongoConnRepo.UserRepo;
import dev.sagar.journalApp.Services.UserServices;
import dev.sagar.journalApp.entity.JouranalEntry;
import dev.sagar.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserRepo ur;
    @Autowired
    JouneralRepo jr;
    @Autowired
    UserServices s;

    @GetMapping("/All-Users")
    public List<User> getAllUsers() {
        return ur.findAll();
    }

    @GetMapping("/All-Entries")
    public List<JouranalEntry> getAllEntries() {
        return jr.findAll();
    }

    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody User user) {
        user.setRoles(Arrays.asList("ADMIN"));
        s.saveAdmin(user);
        return new ResponseEntity<>("Admin Created", HttpStatus.CREATED);
    }
}
