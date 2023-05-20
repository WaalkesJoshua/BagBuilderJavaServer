package com.Bagbuilder.RestAPI.Controllers;

import com.Bagbuilder.RestAPI.Models.Bag;
import com.Bagbuilder.RestAPI.Models.Disc;
import com.Bagbuilder.RestAPI.Services.BagService;
import com.Bagbuilder.RestAPI.Services.DiscService;
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

    public BagsController(BagService bagService, DiscService discService) {
        this.bagService = bagService;
        this.discService = discService;
    }

    //get all bags for user
    @GetMapping(path="/userId/{id}")
    public List<Bag> getUsersBags(@PathVariable Long userId) {

        //Fill in logic
        //Don't Forget UserNotFoundException

    }

    //getBagById
    @GetMapping(path="/{id}")
    public Bag getBagById(@PathVariable Long id) {

        //Fill in logic
        //Since this is the first route that needs bag id, stop here and create BagNotFoundException
    }

    //createBag
    @PostMapping(path="/add")
    public Bag createBag(@RequestBody Bag bag) {

        //Fill in logic

    }


    //deleteBagById
    @RequestMapping(method=RequestMethod.DELETE, path="/delete/{id}")
    public Bag deleteBagById(@PathVariable Long id) {

        //Fill in logic

    }


    //addDiscToBagById
    @PostMapping(path="/{id}/addDisc")
    public Disc addDiscToBagById(@PathVariable Long id, @RequestBody Disc disc) {

        //Fill in logic
        //use discService to validate disc and if invalid throw disc exception

    }

    //removeDiscFromBagById
    @PostMapping(path="/{id}/removeDisc")
    public Disc removeDiscFromBagById(@PathVariable Long id, @RequestBody Disc disc) {

        //Fill in logic
        //use discService to validate disc and if invalid throw disc exception

    }

    //updateBag
    @RequestMapping(method=RequestMethod.PUT, path="/update")
    public Bag updateBag(@RequestBody Bag bag) {

        //Fill in logic

    }

}

