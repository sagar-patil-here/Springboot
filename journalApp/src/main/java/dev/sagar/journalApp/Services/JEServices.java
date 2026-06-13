package dev.sagar.journalApp.Services;

import dev.sagar.journalApp.MongoConnRepo.JouneralRepo;
import dev.sagar.journalApp.MongoConnRepo.UserRepo;
import dev.sagar.journalApp.entity.JouranalEntry;
import dev.sagar.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class JEServices {
    @Autowired
    private JouneralRepo jr;
    @Autowired
    private UserRepo use;
    @Autowired
    private UserServices userServices;

    //Create
    @Transactional
    public JouranalEntry saveEntryToMongo(String user, JouranalEntry entry) {
        User MongoUser = use.findByuserName(user);
        JouranalEntry saved = jr.save(entry);
        MongoUser.getJEntries().add(entry);
        use.save(MongoUser);
        return entry;
    }

    //Update

    public boolean UpdateEntryToMongo(JouranalEntry entry, int id, String userName) {
        User Mongouser = userServices.findByuserName(userName);
        JouranalEntry EntryFromMongo = Mongouser.getJEntries().stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if (EntryFromMongo != null) {
            EntryFromMongo.setTitle(entry.getTitle());
            EntryFromMongo.setContent(entry.getContent());
            jr.save(EntryFromMongo);

            return true;
        }
        return false;

    }

    //Delete
    public boolean DeleteEntryFromMongo(int id, String username) {
        User MongoUser = userServices.findByuserName(username);
        boolean removed = MongoUser.getJEntries().removeIf(x -> x.getId() == id);
        if (removed) {
            userServices.saveUser(MongoUser);
            jr.deleteById(id);
            return true;
        }
        return false;
    }

    //Retrieve
//    public ArrayList<JouranalEntry> getAllEnteriesFromMongo() {
//        return new ArrayList<>(jr.findAll());
//
//    }

    public JouranalEntry getById(int id, String userName) {
        User Mongouser = userServices.findByuserName(userName);
        JouranalEntry DBEntry;
        return DBEntry = Mongouser.getJEntries().stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }
}
