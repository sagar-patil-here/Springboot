package dev.sagar.journalApp.Services;

import dev.sagar.journalApp.MongoConnRepo.JouneralRepo;
import dev.sagar.journalApp.controllers.JournalEntryController;
import dev.sagar.journalApp.entity.JouranalEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class JEServices {
        @Autowired
        private  JouneralRepo jr;
        //Create
        public boolean saveEntryToMongo(JouranalEntry entry) {
            jr.save(entry);
            return true;
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
}
