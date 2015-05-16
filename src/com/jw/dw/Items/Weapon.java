package com.jw.dw.Items;

import javafx.scene.paint.Color;
import com.jw.dw.randInt;

/**Weapon
 * Created by vahma on 14.05.15.
 */
public class Weapon {
    private ItemQuality quality;
    public String weaponName;
    public int lvl = 1;
    public Color color;

    public Weapon(int dungeonLevel) {
        lvl = (dungeonLevel / 5) + 1; //Every 5th dung. lvl brings new lvl weapon
        int w = randInt.GetRandInt(1, 5);
        switch (w) {
            case 1:
                weaponName = "Sword";
                break;
            case 2:
                weaponName = "Spear";
                break;
            case 3:
                weaponName = "Dagger";
                break;
            case 4:
                weaponName = "Mace";
                break;
            case 5:
                weaponName = "Axe";
                break;
        }
        w = randInt.GetRandInt(1, 50);
        if (w < 20) {
            quality = ItemQuality.Poor;
            color = Color.rgb(163, 163, 163);
        } else if (w < 38) {
            quality = ItemQuality.Normal;
            color = Color.rgb(255, 255, 255);
        } else if (w < 42) {
            quality = ItemQuality.Magic;
            color = Color.rgb(51, 133, 255);
        } else if (w < 47) {
            quality = ItemQuality.Rare;
            color = Color.rgb(51, 133, 255);
        } else {
            quality = ItemQuality.Unique;
            color = Color.rgb(255, 153, 0);
        }
    }

    public double getStansartDmg() {

        double dmgTmp = 5;
        dmgTmp = dmgTmp * lvl / 1.2;
        if (quality == ItemQuality.Poor) {
            dmgTmp = dmgTmp * 0.6;
        }
        //if (quality == ItemQuality.Normal) {
        //dmgTmp = dmgTmp;
        //}
        if (quality == ItemQuality.Magic) {
            dmgTmp = dmgTmp * 1.2;
        }
        if (quality == ItemQuality.Rare) {
            dmgTmp = dmgTmp * 1.5;
        }
        if (quality == ItemQuality.Unique) {
            dmgTmp = dmgTmp * 2;
        }


        return dmgTmp;

    }

   /* public int getDmg() {

        double dmg;

        dmg = getStansartDmg();
        dmg = randInt.GetRandInt((int) (dmg * 0.6), (int) (dmg * 1.4));
        return (int) dmg;

    }*/


}
