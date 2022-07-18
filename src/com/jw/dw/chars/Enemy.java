package com.jw.dw.chars;

import com.jw.dw.Items.Flask;
import com.jw.dw.Items.FlaskKind;
import com.jw.dw.randInt;

public class Enemy implements Characters {

    private Integer hp;
    public Integer hpmax;
    private final Integer dmg;
    public Integer level;
    public String icon;
    public Integer posX = 0;
    public Integer posY = 0;
    public String enemyName;
    public int xp;
    public boolean elite = false;

    /*public Enemy(Integer hpset, Integer dmgset, Integer lvl) {
        hp = hpset;
        dmg = dmgset;
        level = lvl;

    }*/

    public Enemy(Integer dungeonlvl) {

        double hpMult = 1;
        double dmgEnemy;

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
            dmgEnemy = 3.7;
        } else if (rnd < 650) {
            icon = "k";
            enemyName = "Kobold";
            hpMult = 1.6;
            dmgEnemy = 4.5;
        } else if (rnd < 750) {
            icon = "b";
            enemyName = "Beholder";
            hpMult = 1.8;
            dmgEnemy = 5.7;
        } else if (rnd < 800) {
            icon = "W";
            enemyName = "Wyvern";
            hpMult = 2;
            dmgEnemy = 6.8;
            elite = true;
        } else if (rnd < 850) {
            icon = "E";
            enemyName = "Elemental";
            hpMult = 2.3;
            dmgEnemy = 7.9;
            elite = true;
        } else if (rnd < 900) {
            icon = "M";
            enemyName = "Manticora";
            hpMult = 2.5;
            dmgEnemy = 9;
            elite = true;
        } else {
            icon = "D";
            enemyName = "Dragon";
            hpMult = 3;
            dmgEnemy = 11;
            elite = true;
        }
        hp = (int) ((80 * hpMult) + ((dungeonlvl - 1) * 40));
        hpmax = hp;
        dmg = (int) ((dungeonlvl - 1) + dmgEnemy);
        level = dungeonlvl;
        xp = (hp / 5);

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
            if (armour == 0) {
                armour = 1;
            }
            armour = armour * 4;
        }
        dmgTmp = randInt.GetRandInt((int) (dmg * 0.6), (int) (dmg * 1.4));
        dmgTmp = dmgTmp - armour;
        if (dmgTmp <= 0) {
            dmgTmp = 1;
        }
        return (int) dmgTmp;
    }

//    public void SetDmg(Integer sdmg) {
//
//        dmg = sdmg;
//    }

    public Integer getHP() {
        return hp;
    }
}
