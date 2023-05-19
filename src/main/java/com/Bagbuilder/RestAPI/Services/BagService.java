package com.Bagbuilder.RestAPI.Services;

import com.Bagbuilder.RestAPI.Models.Bag;
import com.Bagbuilder.RestAPI.Models.Disc;

import java.util.ArrayList;
import java.util.List;

public class BagService {

    private static List<Bag> bags = new ArrayList<>();
    private static Long bagCount = 0L;

    static {
        bags.add(new Bag(++bagCount, 1, "CasualBag", "For casual rounds"));
        bags.add(new Bag(++bagCount, 2, "TourneyBag", "For tournies"));
        bags.add(new Bag(++bagCount, 1, "MyFirstBag", "made my first bag"));
        bags.add(new Bag(++bagCount, 1, "BestBag", "For slaying it"));
    }


}
