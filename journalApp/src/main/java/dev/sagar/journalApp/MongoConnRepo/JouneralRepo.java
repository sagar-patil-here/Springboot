package dev.sagar.journalApp.MongoConnRepo;

import dev.sagar.journalApp.entity.JouranalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JouneralRepo extends MongoRepository <JouranalEntry,Integer> {
}
