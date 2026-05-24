package dev.sagar.journalApp.controllers;

import dev.sagar.journalApp.Services.JEServices;
import dev.sagar.journalApp.Services.UserServices;
import dev.sagar.journalApp.entity.JouranalEntry;
import dev.sagar.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController_Mongo {
    @Autowired
    private JEServices services;
    @Autowired
    private UserServices userService;
    @GetMapping
    public String welcome() {
        return "Welcome to journal app!";
    }

//    Map<Integer,JouranalEntity> entries = new HashMap<>();

    @PostMapping("/submit/{userName}")
    public JouranalEntry pushEntry(@RequestBody JouranalEntry je, @PathVariable String userName) {
      return services.saveEntryToMongo(userName,je);
    }

    @GetMapping("/show-entries/{userName}")
    public List<JouranalEntry> getEntries(@PathVariable String userName){
        User user = userService.findByuserName(userName);
//        return new ArrayList<>(entries.values());
        return user.getJEntries();

    }

    @PutMapping("/update")
    public boolean updateEntry(@RequestBody JouranalEntry je) {
//        je.setId(id);
//        entries.put(id, je);
//        return true;
      return  services.UpdateEntryToMongo(je);
//        return false;
    }

    @DeleteMapping("/delete")
    public boolean deleteEntry (@RequestBody JouranalEntry je){
//        entries.remove (id);
        return services.DeleteEntryFromMongo(je);

    }
    //get by id
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getEntryById(@PathVariable ObjectId id){
//        JouranalEntry ent = services.getById(id);
//    if(ent!=null){
//        return new ResponseEntity<>(ent, HttpStatus.OK) ;
//    }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

}
