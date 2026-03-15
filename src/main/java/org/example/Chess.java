package org.example;

import java.util.Objects;

public class Chess {
    String name, side;
    int weight, loc;
    boolean show=false, live=true; // 有沒有被翻開、吃掉
    Chess(String name, int weight, String side, int loc) {
        this.name=name;
        this.weight=weight;
        this.side=side;
        this.loc=loc;
    }

    @Override
    public String toString() {
        if(show&&live)
        {
            if(side.equals("Red")){
                return "["+name+"]";
            }
            else
                return "{"+name+"}";
        }
        else if(live)
            return " Ｘ ";
        else
            return " － ";
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public String getSide() {
        return side;
    }

    public Integer getLoc() {
        return loc;
    }
}
