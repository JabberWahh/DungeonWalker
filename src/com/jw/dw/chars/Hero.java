package com.jw.dw.chars;

/**
 * Created by vahma on 27.04.15.
 * Hero
 */
public class Hero implements Characters {
    private Integer hp;
    private Integer dmg;
    public boolean needRest = false;
    public static final String icon = "☺";
    public Integer posX = 0;
    public Integer posY = 0;
    public boolean mooved;
    private static Hero instance;

    private Hero(){}

    public static Hero GetInstance() {
        if (instance == null) {
            instance = new Hero();
        }
        return instance;
    }

    public void SetHP(Integer shp) {
        hp = shp;
    }


    public Integer GetDmg() {

        return dmg +(int )(Math.random() * 3 + 1);
    }


    public void SetDmg(Integer sdmg) {
        dmg = sdmg ;
    }
    public Integer GetHP() {

        return hp;
    }

    public void SetPosition(int x, int y){
        posX = x;
        posY = y;
    }

}
