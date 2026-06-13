package dev.sagar.journalApp.controllers;

import dev.sagar.journalApp.Services.JEServices;
import dev.sagar.journalApp.Services.UserServices;
import dev.sagar.journalApp.entity.JouranalEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/submit")
    public JouranalEntry pushEntry(@RequestBody JouranalEntry je) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return services.saveEntryToMongo(userName, je);
    }

    @GetMapping("/show-entries")
//    public List<JouranalEntry> getEntries() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = authentication.getName();
//        User user = userService.findByuserName(userName);
////        return new ArrayList<>(entries.values());
//        return user.getJEntries();
//
//    }

    @PutMapping("/update/{id}")
    public boolean updateEntry(@RequestBody JouranalEntry je, @PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
//        je.setId(id);
//        entries.put(id, je);
//        return true;
        return services.UpdateEntryToMongo(je, id, userName);
//        return false;
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteEntry(@PathVariable int id) {
//        entries.remove (id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return services.DeleteEntryFromMongo(id, userName);

    }

    //get by id
    @GetMapping("/{id}")
    public JouranalEntry getEntryById(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        return services.getById(id, user);
    }
}
