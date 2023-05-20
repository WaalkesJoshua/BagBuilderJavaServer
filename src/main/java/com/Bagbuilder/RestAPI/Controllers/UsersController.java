package com.Bagbuilder.RestAPI.Controllers;

import com.Bagbuilder.RestAPI.Exceptions.UserNotFoundException;
import com.Bagbuilder.RestAPI.Models.User;
import com.Bagbuilder.RestAPI.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/users")
public class UsersController {

    @Autowired
    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path="")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping(path="/{id}")
    public User getUserById(@PathVariable Long id) {
        User foundUser = userService.findOne(id);
        if (foundUser == null) {
            throw new UserNotFoundException("Id: " + id);
        }
        return foundUser;
    }

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
    public User updateUserById(@RequestBody User user) {
        User updatedUser = userService.modifyUser(user);
        if (updatedUser == null) {
            throw new UserNotFoundException("Id: " + user.getId());
        }
        return updatedUser;
    }

}
