package com.jw.dw.chars;

/**
 * Created by vahma on 27.04.15.
 */
public class Enemy implements Characters {

    private Integer hp;
    private Integer dmg;
    public Integer enemyName = 0;

    public Enemy(Integer hpset, Integer dmgset, Integer nm) {
        hp = hpset;
        dmg = dmgset;
        enemyName = nm;
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
