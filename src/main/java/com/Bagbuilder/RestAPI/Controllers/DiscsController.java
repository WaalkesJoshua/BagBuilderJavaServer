package com.Bagbuilder.RestAPI.Controllers;

import com.Bagbuilder.RestAPI.Exceptions.DiscNotFoundException;
import com.Bagbuilder.RestAPI.Models.Disc;
import com.Bagbuilder.RestAPI.Repositories.DiscRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/discs")
public class DiscsController {

    @Autowired
    private DiscRepository discRepository;

    DiscsController (DiscRepository discRepository) {
        this.discRepository = discRepository;
    }

    //create a getAllDiscs route
    @GetMapping(path="")
    public List<Disc> getAllDiscs() {
        return discRepository.findAll();
    }

    //create a getDiscById route
    @GetMapping(path="/{id}")
    public Disc getDiscById(@PathVariable int id) {
        //pass id into request to disc api and return disc
        Optional<Disc> foundDisc = discRepository.findById(id);
        if (foundDisc.isEmpty()) {
            throw new DiscNotFoundException("No disc found for Id: " + id);
        }
        return foundDisc.get();
    }
}
