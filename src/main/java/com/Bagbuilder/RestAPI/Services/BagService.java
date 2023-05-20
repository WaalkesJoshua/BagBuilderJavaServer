package com.Bagbuilder.RestAPI.Services;

import com.Bagbuilder.RestAPI.Models.Bag;
import com.Bagbuilder.RestAPI.Models.Disc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BagService {

    private static List<Bag> bags = new ArrayList<>();
    private static Long bagCount = 0L;


    static {
        bags.add(new Bag(++bagCount, 1L,1, "CasualBag", "For casual rounds"));
        bags.add(new Bag(++bagCount, 1L, 2, "TourneyBag", "For tournies"));
        bags.add(new Bag(++bagCount, 2L, 1, "MyFirstBag", "made my first bag"));
        bags.add(new Bag(++bagCount, 3L, 1, "BestBag", "For slaying it"));
    }

    ////There is no real use case for this///
//    public List<Bag> getAllBags() {
//       return bags;
//    }

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

    public Bag createNewBag(Bag bag) {
        int bagNumber = getAllUserBags(bag.getUserId()).size() + 1;
        bag.setId(++bagCount);
        bag.setBagNumber(bagNumber);
        bags.add(bag);
        return bag;
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

    //get discs from bag  ---- I opt to just select all instead of by id or name,
    // because front end will likely never use that feature.
    public List<Disc> getDiscsFromBag(Long id) {
        Bag currentBag = getBagById(id);
        if (currentBag == null) {
            return null;
        }
        return currentBag.getDiscs();
    }

    public Disc addDiscToBagById(Disc disc, Long bagId) {
        Bag currentBag = getBagById(bagId);
        if (currentBag == null ) {
            return null;
        }
        List<Disc> currentDiscs = getDiscsFromBag(bagId);
        currentDiscs.add(disc);
        currentBag.setDiscs(currentDiscs);

        return disc;
    }

    public Disc removeDiscfromBagById(Disc disc, Long bagId) {
        Bag currentBag = getBagById(bagId);
        if (currentBag == null ) {
            return null;
        }
        List<Disc> currentDiscs = getDiscsFromBag(bagId);
        currentDiscs.remove(disc);
        currentBag.setDiscs(currentDiscs);

        return disc;
    }

    public Bag modifyBag(Bag bag) {
        Bag updatedBag = getBagById(bag.getId());
        if (updatedBag == null) {
            return null;
        }
        updatedBag.setBagNumber(bag.getBagNumber());
        updatedBag.setName(bag.getName());
        updatedBag.setDescription(bag.getName());

        return updatedBag;
    }
}
