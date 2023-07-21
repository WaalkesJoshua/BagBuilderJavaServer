package com.Bagbuilder.RestAPI.Controllers;

import com.Bagbuilder.RestAPI.Exceptions.BagNotFoundException;
import com.Bagbuilder.RestAPI.Exceptions.DiscNotFoundException;
import com.Bagbuilder.RestAPI.Exceptions.UserNotFoundException;
import com.Bagbuilder.RestAPI.Models.Bag;
import com.Bagbuilder.RestAPI.Models.Disc;
import com.Bagbuilder.RestAPI.Models.User;
import com.Bagbuilder.RestAPI.Repositories.BagRepository;
import com.Bagbuilder.RestAPI.Repositories.DiscRepository;
import com.Bagbuilder.RestAPI.Repositories.UserRepository;
import com.Bagbuilder.RestAPI.Services.BagService;
import com.Bagbuilder.RestAPI.Services.DiscService;
import com.Bagbuilder.RestAPI.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/bags")
public class BagsController {

    @Autowired
    private BagRepository bagRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiscRepository discRepository;

//    @Autowired
//    private BagService bagService;
//
//    @Autowired
//    private DiscService discService;
//
//    @Autowired
//    private UserService userService;

    public BagsController(BagRepository bagRepository, UserRepository userRepository, DiscRepository discRepository) {
        this.bagRepository = bagRepository;
        this.userRepository = userRepository;
        this.discRepository = discRepository;
    }

    @GetMapping(path="/userId/{userId}")
    public List<Bag> getUsersBags(@PathVariable Long userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isEmpty()) {
            throw new UserNotFoundException("No user found for Id: " + userId);
        }
       return foundUser.get().getBags();
    }

    @GetMapping(path="/{id}")
    public Bag getBagById(@PathVariable Long id) {
        Optional<Bag> foundBag = bagRepository.findById(id);
        if (foundBag.isEmpty()) {
            throw new BagNotFoundException("No bag found for Id: " + id);
        }
        return foundBag.get();
    }

    @PostMapping(path="/add/{id}")
    public String createBag(@RequestBody Bag bag, @PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("No user found for Id: " + id);
        }
        bag.setUser(user.get());
        bagRepository.saveAndFlush(bag);

        List<Bag> userBags = user.get().getBags();
        userBags.add(bag);
        user.get().setBags(userBags);
        userRepository.saveAndFlush(user.get());
        return ("New bag created with name: " + bag.getName()+ " and Id: " + bag.getId() +
        " for : " + user.get().getFirstName());
    }


    @RequestMapping(method=RequestMethod.DELETE, path="/delete/{id}")
    public String deleteBagById(@PathVariable Long id) {
        if (!bagRepository.existsById(id)) {
            throw new BagNotFoundException("No bag found for Id: " + id);
        }
        bagRepository.deleteById(id);
        boolean isDeleted = !bagRepository.existsById(id);
        if( isDeleted ) {
            return("Successfully deleted bag");
        } else {
            return("Failed to delete bag, please try again");
        }
    }


    @PostMapping(path="/{id}/addDisc/{discId}")
    public Bag addDiscToBagById(@PathVariable Long id, @PathVariable int discId) {
        Optional<Disc> addedDisc = discRepository.findById(discId);
        if (addedDisc.isEmpty()) {
            throw new DiscNotFoundException("No disc found for Id: " + discId);
        }
        Optional<Bag> currentBag = bagRepository.findById(id);
        if (currentBag.isEmpty()) {
            throw new BagNotFoundException("No bag found for Id: " + id);
        }
        List<Disc> discsInBag = currentBag.get().getDiscs();
        discsInBag.add(addedDisc.get());
        currentBag.get().setDiscs(discsInBag);
        bagRepository.saveAndFlush(currentBag.get());
        return currentBag.get();
    }

    @PostMapping(path="/{id}/removeDisc/{discId}")
    public Bag removeDiscFromBagById(@PathVariable Long id, @PathVariable int discId) {
        Optional<Disc> removedDisc = discRepository.findById(discId);
        if (removedDisc.isEmpty()) {
            throw new DiscNotFoundException("No disc found for Id: " + discId);
        }
        Optional<Bag> currentBag = bagRepository.findById(id);
        if (currentBag.isEmpty()) {
            throw new BagNotFoundException("No bag found for Id: " + id);
        }
        List<Disc> discsInBag = currentBag.get().getDiscs();
        discsInBag.remove(removedDisc.get());
        currentBag.get().setDiscs(discsInBag);
        bagRepository.saveAndFlush(currentBag.get());
        return currentBag.get();
    }

    @RequestMapping(method=RequestMethod.PUT, path="/update")
    public Bag updateBag(@RequestBody Bag newBag) {
        Optional<Bag> bag = bagRepository.findById(newBag.getId());
        if(bag.isEmpty()) {
            throw new BagNotFoundException("No bag found for Id: " + newBag.getId());
        }
        bag.get().setName(newBag.getName());
        bag.get().setDescription(newBag.getDescription());
        bagRepository.saveAndFlush(bag.get());
        return bag.get();
    }

}

