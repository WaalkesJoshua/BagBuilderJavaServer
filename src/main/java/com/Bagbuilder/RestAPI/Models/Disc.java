package com.Bagbuilder.RestAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity(name="discs")
@SequenceGenerator(name = "disc_seq", allocationSize = 1)
public class Disc {

    public Disc () {}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disc_seq")
    private int id;
    private String name;
    private String type;
    private String maker;
    private double speed;
    private double glide;
    private double turn;
    private double fade;
    private String stability;

    @ManyToMany(mappedBy = "discs")
    @JsonIgnore
    private List<Bag> bags;

    public Disc(int id, String name, String maker, double speed, double turn, double fade, double glide, String stability, String type) {
        this.id = id;
        this.name = name;
        this.maker = maker;
        this.speed = speed;
        this.turn = turn;
        this.fade = fade;
        this.glide = glide;
        this.stability = stability;
        this.type = type;

//        if(turn + fade == 0) {
//            this.stability = "Stable";
//        } else if (turn + fade < 0) {
//            this.stability = "Understable";
//        } else {
//            this.stability = "Overstable";
//        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public void setStability(String stability) {
//        this.stability = stability;
//    }

    public String getType() {
        return type;
    }

    public String getMaker() {
        return maker;
    }

    public String getStability() {
        return stability;
    }

    public double getSpeed() {
        return speed;
    }

    public double getGlide() {
        return glide;
    }

    public double getTurn() {
        return turn;
    }

    public double getFade() {
        return fade;
    }

    public List<Bag> getBags() {
        return bags;
    }

    @Override
    public String toString() {
        return "Disc{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maker='" + maker + '\'' +
                '}';
    }
}
