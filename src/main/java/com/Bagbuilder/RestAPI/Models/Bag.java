package com.Bagbuilder.RestAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name="bags")
@SequenceGenerator(name = "bags_seq", sequenceName = "", initialValue = 50)
public class Bag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bags_seq")
    private Long id;
    private int bagNumber;
    private String name;
    private String description;

    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToMany
    private List<Disc> discs;


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

    public User getUser() {
        return user;
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
