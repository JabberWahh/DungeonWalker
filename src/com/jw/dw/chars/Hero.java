package com.jw.dw.chars;

/**
 * Created by vahma on 27.04.15.
 */
public class Hero implements Characters {
    private Integer hp;
    private Integer dmg;
    public boolean needRest = false;

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

}
