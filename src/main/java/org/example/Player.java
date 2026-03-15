package org.example;

public class Player {
    String name, side;
    Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSide(String side) {
        this.side=side;
    }
    public String getSide() {
        return side;
    }
}
