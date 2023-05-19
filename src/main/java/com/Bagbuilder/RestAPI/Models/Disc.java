package com.Bagbuilder.RestAPI.Models;

public class Disc {
    private int id;
    private String name;
    private String type;
    private String maker;
    private String stability;
    private int speed;

    public Disc(int id, String name, String type, String maker, String stability, int speed) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.maker = maker;
        this.stability = stability;
        this.speed = speed;
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

    @Override
    public String toString() {
        return "Disc{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maker='" + maker + '\'' +
                '}';
    }
}
