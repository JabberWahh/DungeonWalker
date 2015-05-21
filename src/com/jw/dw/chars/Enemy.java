package com.jw.dw.chars;

import com.jw.dw.Items.Flask;
import com.jw.dw.Items.FlaskKind;
import com.jw.dw.randInt;

/**
 * Created by vahma on 27.04.15.
 * Class Enemy
 */
public class Enemy implements Characters {

    private Integer hp;
    public Integer hpmax;
    private Integer dmg;
    public Integer level = 0;
    public String icon = "g";
    public Integer posX = 0;
    public Integer posY = 0;
    public String enemyName = "";
    public int xp = 0;
    public boolean elite = false;

    /*public Enemy(Integer hpset, Integer dmgset, Integer lvl) {
        hp = hpset;
        dmg = dmgset;
        level = lvl;

    }*/

    public Enemy(Integer dungeonlvl) {

        double hpMult = 1;
        double dmgEnemy = 1;

        int rnd = randInt.GetRandInt(1, 1000);

        if (rnd < 400) {
            icon = "s";
            enemyName = "spider";
            dmgEnemy = 2;
        } else if (rnd < 500) {
            icon = "g";
            enemyName = "Goblin";
            hpMult = 1.1;
            dmgEnemy = 2.5;
        } else if (rnd < 550) {
            icon = "z";
            enemyName = "Zombie";
            hpMult = 1.2;
            dmgEnemy = 3;
        } else if (rnd < 600) {
            icon = "d";
            enemyName = "Demon";
            hpMult = 1.3;
            dmgEnemy = 4;
        } else if (rnd < 650) {
            icon = "k";
            enemyName = "Kobold";
            hpMult = 1.6;
            dmgEnemy = 5;
        } else if (rnd < 750) {
            icon = "b";
            enemyName = "Beholder";
            hpMult = 1.8;
            dmgEnemy = 7;
        } else if (rnd < 800) {
            icon = "W";
            enemyName = "Wyvern";
            hpMult = 2;
            dmgEnemy = 9;
            elite = true;
        } else if (rnd < 850) {
            icon = "E";
            enemyName = "Elemental";
            hpMult = 2.3;
            dmgEnemy = 10;
            elite = true;
        } else if (rnd < 900) {
            icon = "M";
            enemyName = "Manticora";
            hpMult = 2.5;
            dmgEnemy = 12;
            elite = true;
        } else if (rnd >= 900) {
            icon = "D";
            enemyName = "Dragon";
            hpMult = 3;
            dmgEnemy = 14;
            elite = true;
        }
        hp = (int) ((80 * hpMult) + ((dungeonlvl - 1) * 40));
        hpmax = hp;
        dmg = (int) ((dungeonlvl - 1) + dmgEnemy);
        level = dungeonlvl;
        xp = (hp / 40);


    }

    public void setHP(Integer shp) {
        hp = shp;
    }


    public Integer getDmg() {

        double armour = 0;
        double dmgTmp;
        for (int i = 0; i < Hero.getInstance().armour.size(); i++) {
            armour = armour + Hero.getInstance().armour.get(i).getArmour();
        }
        if (Flask.isActivatedFlaskByKind(FlaskKind.Armor)) {
            armour = armour * 4;
        }
        dmgTmp = randInt.GetRandInt((int) (dmg * 0.6), (int) (dmg * 1.4));
        dmgTmp = dmgTmp - armour;
        if (dmgTmp <= 0) {
            dmgTmp = 1;
        }
        return (int) dmgTmp;
    }


    public void SetDmg(Integer sdmg) {

        dmg = sdmg;
    }

    public Integer getHP() {

        return hp;
    }
}
