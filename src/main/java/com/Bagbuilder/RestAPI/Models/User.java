package com.Bagbuilder.RestAPI.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name="bb_users")
@SequenceGenerator(name = "user_seq", initialValue = 50,  allocationSize = 1)
public class User {

    public User () {}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;
    private String firstName;
    private String lastName;
    private String experience; //should be Beginner, Intermediate, or Advanced only
    private String email;

    @OneToMany(mappedBy = "user")
    @OrderBy("bagNumber DESC")
    private List<Bag> bags;

    public User(Long id, String firstName, String lastName, String experience, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Bag> getBags() {
        return bags;
    }

    public void setBags(List<Bag> bags) {
        this.bags = bags;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", experience='" + experience + '\'' +
                ", bag=" + bags +
                '}';
    }
}
