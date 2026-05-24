package dev.sagar.journalApp.Services;

import dev.sagar.journalApp.JournalAppApplication;
import dev.sagar.journalApp.MongoConnRepo.JouneralRepo;
import dev.sagar.journalApp.MongoConnRepo.UserRepo;
import dev.sagar.journalApp.controllers.JournalEntryController;
import dev.sagar.journalApp.controllers.JournalEntryController_Mongo;
import dev.sagar.journalApp.entity.JouranalEntry;
import dev.sagar.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Component
public class JEServices {
        @Autowired
        private  JouneralRepo jr;
        @Autowired
        private UserRepo use;
        //Create
        public JouranalEntry saveEntryToMongo(String user, JouranalEntry entry) {
            User MongoUser = use.findByuserName(user);
            JouranalEntry saved = jr.save(entry);
            MongoUser.getJEntries().add(entry);
            use.save(MongoUser);
            return entry;
        }
        //Update
        public boolean UpdateEntryToMongo(JouranalEntry entry) {
            jr.save(entry);
            return true;
        }

        //Delete
        public boolean DeleteEntryFromMongo(JouranalEntry entry){
            jr.delete(entry);
            return true;
        }

         //Retrieve
        public ArrayList<JouranalEntry> getAllEnteriesFromMongo(){
            return new ArrayList<>(jr.findAll());

        }
        public JouranalEntry getById(int id){
           return  jr.findById(id).orElse(null);
        }
}
