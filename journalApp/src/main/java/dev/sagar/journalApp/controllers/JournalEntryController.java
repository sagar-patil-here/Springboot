package dev.sagar.journalApp.controllers;

import dev.sagar.journalApp.entity.JouranalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {


    @GetMapping
    public String welcome() {
        return "Welcome to journal app!";
    }

    Map<Integer, JouranalEntry> entries = new HashMap<>();

    @PostMapping("/submit")
    public boolean pushEntry(@RequestBody JouranalEntry[] je) {
        for (int i =0;i< je.length;i++) {
            entries.put(je[i].getId(),je[i]);
        }
        return true;
    }

    @GetMapping("/show-entries")
    public ArrayList<JouranalEntry> getEntries(){
        return new ArrayList<>(entries.values());
    }

    @PutMapping("/update/{id}")
    public boolean updateEntry(@PathVariable("id") int id, @RequestBody JouranalEntry je) {
        je.setId(id);
        entries.put(id, je);
        return true;
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteEntry (@PathVariable("id") int id){
        entries.remove (id);
        return true;
    }

}
