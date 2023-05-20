package com.Bagbuilder.RestAPI.Services;

import com.Bagbuilder.RestAPI.Models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    public static List<User> users = new ArrayList<>();
    private static Long userCount = 0L;

    static {
        users.add(new User(userCount++, "Josh", "Waalkes", "Intermediate"));
        users.add(new User(userCount++, "Jared", "Leak", "Intermediate"));
        users.add(new User(userCount++, "Joe", "Talbott", "Beginner"));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(Long id) {
        User foundUser = null;
        for (User user : users) {
            if( user.getId() == id) {
                foundUser = user;
            }
        }
        return foundUser;
    }

    //create new user
    public User addUser(User user) {
        user.setId(userCount++);
        users.add(user);
        return user;
    }

    public User deleteUser(Long id) {
        User user = findOne(id);
        if (user == null){
            return null;
        }
        users.remove(user); // later this will translate to remove user from db

        return user;
    }

    public User modifyUser(User modUser) {
        User user = findOne(modUser.getId());
        if (user == null){
            return null;
        }
        user.setLastName(modUser.getLastName());        ////////
        user.setFirstName(modUser.getFirstName());      // Later this will translate to update value in db
        user.setExperience(modUser.getExperience());    ////////

        return user;
    }
}
