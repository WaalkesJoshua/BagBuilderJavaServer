package com.Bagbuilder.RestAPI.Services;

import com.Bagbuilder.RestAPI.Models.Disc;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DiscService {

    //use disc model to create disc service
    private static List<Disc> discs = new ArrayList<>();
    private static int discCount = 0;

//    static {
//        discs.add(new Disc (++discCount, "Firebird", "Driver",
//                "Discraft", "Overstable", 9));
//        discs.add(new Disc (++discCount, "Destroyer", "Driver",
//                "Innova", "Overstable", 12));
//        discs.add(new Disc (++discCount, "P2", "Putter",
//                "Discmania", "Stable", 2));
//    }

    public List<Disc> findAll() {
        return discs;
    }

    public Disc findOne(int id) {
        Disc foundDisc = null;
        for (Disc disc : discs) {
            if (disc.getId() == id) {
                foundDisc = disc;
            }
        }
        return foundDisc;
    }

}
