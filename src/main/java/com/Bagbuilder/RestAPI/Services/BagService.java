package com.Bagbuilder.RestAPI.Services;

import com.Bagbuilder.RestAPI.Models.Bag;
import com.Bagbuilder.RestAPI.Models.Disc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BagService {

    @Autowired
    private DiscService discService;

    private static List<Bag> bags = new ArrayList<>();
    private static Long bagCount = 0L;

    static {
        bags.add(new Bag(++bagCount, 1L,1, "CasualBag", "For casual rounds"));
        bags.add(new Bag(++bagCount, 1L, 2, "TourneyBag", "For tournies"));
        bags.add(new Bag(++bagCount, 2L, 1, "MyFirstBag", "made my first bag"));
        bags.add(new Bag(++bagCount, 3L, 1, "BestBag", "For slaying it"));
    }

    public List<Bag> getAllBags() {
       return bags;
    }

    public Bag getBagById(Long id) {
        Bag foundBag = null;
        for (Bag bag : bags) {
            if (bag.getId() == id) {
                foundBag = bag;
            }
        }
        return foundBag;
    }

    public List<Bag> getAllUserBags(Long userId) {
        List<Bag> userBags = new ArrayList<>();
        for (Bag bag : bags) {
            if (bag.getUserId() == userId) {
                userBags.add(bag);
            }
        }
        return userBags;
    }

    //need new method to create  new bag
    public Bag createNewBag(Long userId, String name, String description) {
        int bagNumber = getAllUserBags(userId).size() + 1;
        Bag createdBag = new Bag(++bagCount, userId, bagNumber, name, description);
        bags.add( createdBag );
        return createdBag;
    }

    //method to delete bag
    public Bag deleteBagById(Long id) {
        Bag bagToRemove = getBagById(id);
        if (bagToRemove == null) {
            return null;
        }
        bags.remove(bagToRemove);
        return bagToRemove;
    }

    //get discs from bag  ---- I opt to just select all instead of by id or name, because front end will likely never use that feature.
    public List<Disc> getDiscsFromBag(Long id) {
        Bag currentBag = getBagById(id);
        if (currentBag == null) {
            return null;
        }
        return currentBag.getDiscs();
    }

    //need method to add disc to bag
    public List<Disc> addDiscToBag(int discId, Long bagId) {
        Bag currentBag = getBagById(bagId);
        Disc discToAdd = discService.findOne(discId);
        if (currentBag == null || discToAdd == null) {
            return null;
        }
        List<Disc> currentDiscs = getDiscsFromBag(bagId);
        currentDiscs.add(discToAdd);
        currentBag.setDiscs(currentDiscs);

        return currentDiscs;
    }

    //need method to remove disc from bag
    public List<Disc> removeDiscfromBag(int discId, Long bagId) {
        Bag currentBag = getBagById(bagId);
        Disc discToRemove = discService.findOne(discId);
        if (currentBag == null || discToRemove == null) {
            return null;
        }
        List<Disc> currentDiscs = getDiscsFromBag(bagId);
        currentDiscs.remove(discToRemove);
        currentBag.setDiscs(currentDiscs);

        return currentDiscs;
    }
}
