package com.jw.dw.Items;

import com.jw.dw.randInt;
import javafx.scene.paint.Color;

/**Armour
 * Created by vahma on 14.05.15.
 */
public class Armour {
    private ItemQuality quality;
    public ArmourPart armourPart;
    public String armourName;
    public int lvl = 1;
    public Color color;

    public Armour(int dungeonLevel){
        lvl = (dungeonLevel / 5) + 1; //Every 5th dung. lvl brings new lvl weapon
        int w = randInt.GetRandInt(1, 4);
        switch (w) {
            case 1:
                armourName = "Helmet";
                armourPart = ArmourPart.Head;
                break;
            case 2:
                armourName = "Chest piece";
                armourPart = ArmourPart.Chest;
                break;
            case 3:
                armourName = "Gloves";
                armourPart = ArmourPart.Arms;
                break;
            case 4:
                armourName = "Boots";
                armourPart = ArmourPart.Legs;
                break;
            case 5:
                armourName = "Shield";
                armourPart = ArmourPart.Shield;
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
    public double getStansartArmour() {

        double dmgTmp = 1.2;
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

    public double getArmour() {

        double dmg;

        dmg = getStansartArmour();
        dmg = randInt.GetRandInt((int) (dmg * 0.6), (int) (dmg * 1.4));
        return dmg;

    }
}
