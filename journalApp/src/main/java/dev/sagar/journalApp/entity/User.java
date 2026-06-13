package dev.sagar.journalApp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    //by default it will not work with @Document to make it work we have to add property in application.properties = auto-index-creation=true
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @DBRef      //used to ref actual field in DB
    private List<JouranalEntry> jEntries = new ArrayList<>();
    private List<String> roles = new ArrayList<>();
}
