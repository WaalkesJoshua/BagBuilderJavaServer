package com.Bagbuilder.RestAPI.Controllers;

import com.Bagbuilder.RestAPI.Exceptions.BagNotFoundException;
import com.Bagbuilder.RestAPI.Exceptions.DiscNotFoundException;
import com.Bagbuilder.RestAPI.Exceptions.UserNotFoundException;
import com.Bagbuilder.RestAPI.Models.Bag;
import com.Bagbuilder.RestAPI.Models.Disc;
import com.Bagbuilder.RestAPI.Models.User;
import com.Bagbuilder.RestAPI.Services.BagService;
import com.Bagbuilder.RestAPI.Services.DiscService;
import com.Bagbuilder.RestAPI.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/bags")
public class BagsController {

    @Autowired
    private BagService bagService;

    @Autowired
    private DiscService discService;

    @Autowired
    private UserService userService;

    public BagsController(BagService bagService, DiscService discService, UserService userService) {
        this.bagService = bagService;
        this.discService = discService;
        this.userService = userService;
    }

//    @GetMapping(path="/userId/{userId}")
//    public List<Bag> getUsersBags(@PathVariable Long userId) {
//        User foundUser = userService.findOne(userId);
//        if (foundUser == null) {
//            throw new UserNotFoundException("Id: " + userId);
//        }
//        return bagService.getAllUserBags(userId);
//    }

    @GetMapping(path="/{id}")
    public Bag getBagById(@PathVariable Long id) {
        Bag foundBag = bagService.getBagById(id);
        if (foundBag == null) {
            throw new BagNotFoundException("Id: " + id);
        }
        return foundBag;
    }

//    @PostMapping(path="/add")
//    public Bag createBag(@RequestBody Bag bag) {
//        return bagService.createNewBag(bag);
//    }


    @RequestMapping(method=RequestMethod.DELETE, path="/delete/{id}")
    public Bag deleteBagById(@PathVariable Long id) {
        Bag deletedBag = bagService.deleteBagById(id);
        if (deletedBag == null) {
            throw new BagNotFoundException("Id: " + id);
        }
        return deletedBag;
    }


    @PostMapping(path="/{id}/addDisc")
    public Disc addDiscToBagById(@PathVariable Long id, @RequestBody Disc disc) {
        int discId = disc.getId();
        Disc addedDisc = discService.findOne(discId);
        if (addedDisc == null) {
            throw new DiscNotFoundException("Id: " + discId);
        }
        Bag currentBag = bagService.getBagById(id);
        if (currentBag == null) {
            throw new BagNotFoundException("Id: " + id);
        }
        bagService.addDiscToBagById(id, addedDisc);

        return addedDisc;
    }

    @PostMapping(path="/{id}/removeDisc")
    public Disc removeDiscFromBagById(@PathVariable Long id, @RequestBody Disc disc) {
        Disc removedDisc = discService.findOne(disc.getId());
        if (removedDisc == null) {
            throw new DiscNotFoundException("Id: " + disc.getId());
        }
        Bag currentBag = bagService.getBagById(id);
        if (currentBag == null) {
            throw new BagNotFoundException("Id: " + id);
        }
        bagService.removeDiscfromBagById(id, removedDisc);

        return removedDisc;
    }

    @RequestMapping(method=RequestMethod.PUT, path="/update")
    public Bag updateBag(@RequestBody Bag bag) {
        Bag updatedbag = bagService.modifyBag(bag);
        if (updatedbag == null) {
            throw new BagNotFoundException("Id: " + bag.getId());
        }
        return updatedbag;
    }

}

