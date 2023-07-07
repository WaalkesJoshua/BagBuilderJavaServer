package com.Bagbuilder.RestAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name="bags")
@SequenceGenerator(name = "bags_seq", initialValue = 50, allocationSize = 1)
public class Bag {
    public Bag () {}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bags_seq")
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToMany
    @OrderBy("speed ASC")
    private List<Disc> discs;


    public Bag(Long id, String name, String description) {
        this.id = id;
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

    public void setUser(User user) {
        this.user = user;
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
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
