package com.Bagbuilder.RestAPI.Models;

import java.util.ArrayList;
import java.util.List;

public class Bag {
    private Long id;
    private Long userId;
    private int bagNumber;
    private String name;
    private String description;
    private List<Disc> discs = new ArrayList<>();


    public Bag(Long id, Long userId, int bagNumber, String name, String description) {
        this.id = id;
        this.userId = userId;
        this.bagNumber = bagNumber;
        this.name = name;
        this.description = description;
        this.discs = discs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getBagNumber() {
        return bagNumber;
    }

    public void setBagNumber(int bagNumber) {
        this.bagNumber = bagNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<Disc> getDiscs() {
        return discs;
    }

    public void setDiscs(List<Disc> discs) {
        this.discs = discs;
    }

    @Override
    public String toString() {
        return "Bag{" +
                "id=" + id +
                ", bagNumber=" + bagNumber +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
