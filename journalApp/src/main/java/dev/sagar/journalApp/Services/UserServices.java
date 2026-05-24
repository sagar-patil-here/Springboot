package dev.sagar.journalApp.Services;

import dev.sagar.journalApp.MongoConnRepo.UserRepo;
import dev.sagar.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class UserServices {
    @Autowired
    private UserRepo repo;
    public User findByuserName(String userName){
     User useFromDB=   repo.findByuserName(userName);
     return useFromDB;
    }
   public List<User> findAll(){
        return repo.findAll();
    }
    public boolean saveUser(User user){
        repo.save(user);
        return true;
    }
}
