package dev.sagar.journalApp.Services;

import dev.sagar.journalApp.MongoConnRepo.UserRepo;
import dev.sagar.journalApp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class UserServices {
    @Autowired
    private UserRepo repo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByuserName(String userName) {
        User useFromDB = repo.findByuserName(userName);
        return useFromDB;
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    public boolean saveUser(User user) {
        user.setRoles(Arrays.asList("USER"));
        repo.save(user);
        return true;
    }

    public boolean saveNewUser(User user) {
        try {
            if (user.getUserName() != null && user.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRoles(Arrays.asList("USER"));
                repo.save(user);
                return true;
            }
        } catch (Exception e) {
            log.info("\u001B[31m User Already Exist !!\u001B[0m");


        }
        return false;
    }


    public boolean saveAdmin(User user) {
        if (user.getUserName() != null && user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.getRoles().add("USER");
            repo.save(user);
            return true;
        }
        return false;
    }
}
