package com.Bagbuilder.RestAPI.Services;

import com.Bagbuilder.RestAPI.Models.User;

import java.util.ArrayList;
import java.util.List;

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

    public User modifyUser(User modUser) {
        User user = findOne(modUser.getId());
        if (user == null){
            return user;
        }
        user.setLastName(modUser.getLastName());        ////////
        user.setFirstName(modUser.getFirstName());      // Later this will translate to update value in db
        user.setExperience(modUser.getExperience());    ////////

        return user;
    }

    public User deleteUser(User userToBeDeleted) {
        User user = findOne(userToBeDeleted.getId());
        if (user == null){
            return user;
        }
        users.remove(user); // later this will translate to remove user from db

        return user;
    }

    //need new route to create  new bag

    //need route to add disc to bag

    //need route to remove disc from bag
}
