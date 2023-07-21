package com.Bagbuilder.RestAPI.Controllers;

import com.Bagbuilder.RestAPI.Exceptions.EmailAlreadyExistsException;
import com.Bagbuilder.RestAPI.Exceptions.UserNotFoundException;
import com.Bagbuilder.RestAPI.Models.Bag;
import com.Bagbuilder.RestAPI.Models.User;
import com.Bagbuilder.RestAPI.Repositories.BagRepository;
import com.Bagbuilder.RestAPI.Repositories.UserRepository;
import com.Bagbuilder.RestAPI.Services.BagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public boolean isEmailExists (String email) {
        return userRepository.existsByEmail(email);
    }

    @Autowired
    private BagRepository bagRepository;

    public UsersController(UserRepository userRepository, BagRepository bagRepository) {
        this.userRepository = userRepository;
        this.bagRepository = bagRepository;
    }

    @GetMapping(path="")
    public List<User> getAllUsers() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return userRepository.findAll(sort);
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
        if(isEmailExists(user.getEmail())) {
            throw new EmailAlreadyExistsException(user.getEmail() + " already exists");
        }
        userRepository.saveAndFlush(user);
        String bagName = "My First Bag";
        String bagDescription = "This is your first bag, let's add some discs!";
        Bag firstBag = new Bag(bagName, bagDescription);
        firstBag.setUser(user);
        bagRepository.saveAndFlush(firstBag);
        List<Bag> userBags = new ArrayList<>();
        userBags.add(firstBag);
        user.setBags(userBags);
        return userRepository.saveAndFlush(user);
    }

    @RequestMapping(method={RequestMethod.DELETE}, path="/delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        if(foundUser.isEmpty()) {
            throw new UserNotFoundException("No user found for id: " + id);
        }
        List<Long> bagIds = bagRepository.getAllBagIdByUserId(id);
        if(bagIds.size() > 0) {
            bagRepository.deleteDiscRelationsForBagIds(bagIds);
        }
        bagRepository.deleteAllBagsByUserId(id);
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
