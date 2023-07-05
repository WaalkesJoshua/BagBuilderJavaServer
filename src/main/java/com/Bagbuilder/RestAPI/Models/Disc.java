package com.Bagbuilder.RestAPI.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="discs")
@SequenceGenerator(name = "disc_seq", sequenceName = "")
public class Disc {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disc_seq")
    private int id;
    private String name;
    private String type;
    private String maker;
    private int speed;
    private int glide;
    private int turn;
    private int fade;
    private String stability;

    @ManyToMany(mappedBy = "discs")
    private List<Bag> bags;

    public Disc(int id, String name, String type, String maker, int speed, int turn, int fade, int glide) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.maker = maker;
        this.speed = speed;
        this.turn = turn;
        this.fade = fade;
        this.glide = glide;

        if(turn + fade == 0) {
            this.stability = "Stable";
        } else if (turn + fade < 0) {
            this.stability = "Understable";
        } else {
            this.stability = "Overstable";
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getMaker() {
        return maker;
    }

    public String getStability() {
        return stability;
    }

    public int getSpeed() {
        return speed;
    }

    public int getGlide() {
        return glide;
    }

    public int getTurn() {
        return turn;
    }

    public int getFade() {
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
