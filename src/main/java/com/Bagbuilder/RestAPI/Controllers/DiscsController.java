package com.Bagbuilder.RestAPI.Controllers;

import com.Bagbuilder.RestAPI.Exceptions.DiscNotFoundException;
import com.Bagbuilder.RestAPI.Models.Disc;
import com.Bagbuilder.RestAPI.Services.DiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/discs")
public class DiscsController {

    @Autowired
    private DiscService discService;

    DiscsController (DiscService discService) {
        this.discService = discService;
    }

    //create a getAllDiscs route
    @GetMapping(path="")
    public List<Disc> getAllDiscs() {
        return discService.findAll();
    }

    //create a getDiscById route
    @GetMapping(path="/{id}")
    public Disc getDiscById(@PathVariable int id) {
        //pass id into request to disc api and return disc
        Disc foundDisc = discService.findOne(id);
        if (foundDisc == null) {
            throw new DiscNotFoundException("Id: " + id);
        }
        return foundDisc;
    }


    //Since User to disc is a one to many relationship and not the other way around,
    //these should be in the User controller

//    //create a addDiscToBag route
//    @PostMapping(path="/{id}/user/{userId}")
//    public void addDiscToBag(@PathVariable int id, @PathVariable int userId) {
//        //add disc to users bag using discService method addDisc
//    }
//
//    //create a removeDiscFromBag route
//    @RequestMapping(method=HttpMethod.DELETE, path="/{id/user/{userId}")
//    public void removeDiscFromBag(@PathVariable int id, @PathVariable int userId) {
//        //remove disc from bag using discService method removeDisc
//    }
//
//    @RequestMapping(method=HttpMethod.DELETE, path="/deleteAll/user/{userId}")
//    public void removeAllDiscs(@PathVariable int userId) {
//        //remove all discs from users bag using discService method removeAllDiscs
//    }

}
