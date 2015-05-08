package com.jw.dw.chars;

import com.jw.dw.randInt;

/**
 * Created by vahma on 27.04.15.
 * Class Enemy
 */
public class Enemy implements Characters {

    private Integer hp;
    private Integer dmg;
    public Integer level = 0;
    public String icon = "g";
    public Integer posX = 0;
    public Integer posY = 0;

    public Enemy(Integer hpset, Integer dmgset, Integer lvl ) {
        hp = hpset;
        dmg = dmgset;
        level = lvl;

    }

    public Enemy(Integer lvl ) {
        hp = 50 *(1+(lvl / 10));
        dmg = 3 *(1+(lvl / 10));;
        level = lvl;

        int rnd = randInt.GetRandInt(1,2);
        if(rnd==1){
            icon = "s";
        }

    }

    public void SetHP(Integer shp) {
        hp = shp;
    }


    public Integer GetDmg() {

        return dmg + (int) (Math.random() * 3 + 1);
    }


    public void SetDmg(Integer sdmg) {

        dmg = sdmg;
    }

    public Integer GetHP() {

        return hp;
    }
}
