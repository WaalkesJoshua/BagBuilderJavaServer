package com.Bagbuilder.RestAPI.Controllers;

import com.Bagbuilder.RestAPI.Exceptions.UserNotFoundException;
import com.Bagbuilder.RestAPI.Models.Bag;
import com.Bagbuilder.RestAPI.Models.User;
import com.Bagbuilder.RestAPI.Repositories.BagRepository;
import com.Bagbuilder.RestAPI.Repositories.UserRepository;
import com.Bagbuilder.RestAPI.Services.BagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/users")
public class UsersController {

//    @Autowired
//    private userRepository userRepository;
//
//    @Autowired
//    private BagService bagService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BagRepository bagRepository;

    public UsersController(UserRepository userRepository, BagRepository bagRepository) {
        this.userRepository = userRepository;
        this.bagRepository = bagRepository;
    }

    @GetMapping(path="")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
      Optional<User> foundUser = userRepository.findById(id);
      if (foundUser.isEmpty()) {
          throw new UserNotFoundException("No user found for id: " + id);
      }
      return foundUser;
    }

    @PostMapping(path="/add")
    public User addUser(@RequestBody User user) {
        return userRepository.saveAndFlush(user);
    }

    @RequestMapping(method={RequestMethod.DELETE}, path="/delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        if(foundUser.isEmpty()) {
            throw new UserNotFoundException("No user found for id: " + id);
        }
        userRepository.deleteById(id);
        return "User with Id: " + id + " successfully deleted";
    }

    //route to update user
    @RequestMapping(method=RequestMethod.PUT, path="/update")
    public String updateUser(@RequestBody User user) {
        Optional<User> foundUser = userRepository.findById(user.getId());
        if (foundUser.isEmpty()) {
            throw new UserNotFoundException("No user found for id: " + user.getId());
        }
      userRepository.saveAndFlush(user);
      return "User with Id: " + user.getId() + " successfully updated";
    }
}
