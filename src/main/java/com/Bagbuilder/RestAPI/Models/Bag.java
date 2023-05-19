package com.Bagbuilder.RestAPI.Models;

import java.util.ArrayList;
import java.util.List;

public class Bag {
    private Long id;
    private int bagNumber;
    private String name;
    private String description;
    private Integer discCount = 0;
    private List<Disc> discs = new ArrayList<>();


    public Bag(Long id, int bagNumber, String name, String description) {
        this.id = id;
        this.bagNumber = bagNumber;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getDiscCount() {
        return discCount;
    }

    public void setDiscCount(Integer discCount) {
        this.discCount = discCount;
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
                ", discCount=" + discCount +
                '}';
    }
}
