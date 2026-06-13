package dev.sagar.journalApp.Services;

import dev.sagar.journalApp.MongoConnRepo.UserRepo;
import dev.sagar.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepo user;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        System.out.println("LOGIN ATTEMPT = " + username);

        User DBuser = user.findByuserName(username);

        System.out.println("FOUND USER = " + DBuser);

        if (DBuser != null) {
            return org.springframework.security.core.userdetails.User
                    .builder()
                    .username(DBuser.getUserName())
                    .password(DBuser.getPassword())
                    .roles(DBuser.getRoles().toArray(new String[0]))
                    .build();
        }

        throw new UsernameNotFoundException("Username not found");
    }
}

