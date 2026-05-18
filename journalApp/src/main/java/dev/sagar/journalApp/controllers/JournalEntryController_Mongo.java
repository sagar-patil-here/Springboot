package dev.sagar.journalApp.controllers;

import dev.sagar.journalApp.Services.JEServices;
import dev.sagar.journalApp.entity.JouranalEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/journal")
public class JournalEntryController_Mongo {
    @Autowired
    private JEServices services;
    @GetMapping
    public String welcome() {
        return "Welcome to journal app!";
    }

//    Map<Integer,JouranalEntity> entries = new HashMap<>();

    @PostMapping("/submit")
    public boolean pushEntry(@RequestBody JouranalEntry[] je) {
        for (int i =0;i< je.length;i++) {
            services.saveEntryToMongo(je[i]);
        }
        return true;
    }

    @GetMapping("/show-entries")
    public ArrayList<JouranalEntry> getEntries(){

//        return new ArrayList<>(entries.values());
        return services.getAllEnteriesFromMongo();

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

}
