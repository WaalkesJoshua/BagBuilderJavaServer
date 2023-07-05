package com.Bagbuilder.RestAPI.Controllers;

import com.Bagbuilder.RestAPI.Exceptions.UserNotFoundException;
import com.Bagbuilder.RestAPI.Models.Bag;
import com.Bagbuilder.RestAPI.Models.User;
import com.Bagbuilder.RestAPI.Services.BagService;
import com.Bagbuilder.RestAPI.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private BagService bagService;

    public UsersController(UserService userService, BagService bagService) {
        this.userService = userService;
        this.bagService = bagService;
    }

//    @GetMapping(path="")
//    public List<User> getAllUsers() {
//        List<User> allUsers = userService.findAll();
//        for (User user: allUsers) {
//            List<Bag> userBags = bagService.getAllUserBags(user.getId());
//            user.setBags(userBags);
//        }
//        return allUsers;
//    }

//    @GetMapping(path="/{id}")
//    public User getUserById(@PathVariable Long id) {
//        List<Bag> userBags = bagService.getAllUserBags(id);
//        User foundUser = userService.findOne(id);
//        if (foundUser == null) {
//            throw new UserNotFoundException("Id: " + id);
//        }
//        foundUser.setBags(userBags);
//        return foundUser;
//    }

    @PostMapping(path="/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping(method=RequestMethod.DELETE, path="/delete/{id}")
    public User deleteUserById(@PathVariable Long id) {
        User deletedUser = userService.deleteUser(id);
        if (deletedUser == null) {
            throw new UserNotFoundException("Id: " + id);
        }
        return deletedUser;
    }

    //route to update user
    @RequestMapping(method=RequestMethod.PUT, path="/update")
    public User updateUser(@RequestBody User user) {
        User updatedUser = userService.modifyUser(user);
        if (updatedUser == null) {
            throw new UserNotFoundException("Id: " + user.getId());
        }
        return updatedUser;
    }

}
