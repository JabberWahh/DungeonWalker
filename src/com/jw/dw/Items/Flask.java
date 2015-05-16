package com.jw.dw.Items;

import com.jw.dw.chars.Hero;
import com.jw.dw.randInt;

/**Flask
 * Created by vahma on 15.05.15.
 */
public class Flask {
    public boolean activated = false;
    public FlaskKind flaskKind;
    public String flaskName;
    public int value;

    public Flask() {
        int w = randInt.GetRandInt(1, 5);
        switch (w) {
            case 1:
                flaskKind = FlaskKind.HealPermanent;
                flaskName = "Healing flask";
                break;
            case 2:
                flaskKind = FlaskKind.HealTime;
                flaskName = "Flask of regeneration";
                break;
            case 3:
                flaskKind = FlaskKind.Armor;
                flaskName = "Flask of stone skin";
                break;
            case 4:
                flaskKind = FlaskKind.Damage;
                flaskName = "Flask of anger";
                break;
            case 5:
                flaskKind = FlaskKind.PlusHP;
                flaskName = "Flask of titan";
                break;
        }

    }

    public Flask(FlaskKind fk) {
        System.out.println(fk);
        this.flaskKind = fk;
        if (fk == FlaskKind.HealTime) {
            flaskName = "Flask of regeneration";
        }
        if (fk == FlaskKind.Armor) {
            flaskName = "Flask of stone skin";
            this.value = 120;
        }
        if (fk == FlaskKind.Damage) {
            flaskName = "Flask of anger";
            this.value = 120;
        }
        if (fk == FlaskKind.PlusHP) {
            flaskName = "Flask of titan";
            this.value = 240;
        }
        if (fk == FlaskKind.HealPermanent) {
            flaskName = "Healing flask";
        }

    }

    public static boolean isActivatedFlaskByKind(FlaskKind fk) {
        Hero hero = Hero.getInstance();
        for (int i = 0; i < hero.flasks.size(); i++) {
            if (hero.flasks.get(i).flaskKind == fk && hero.flasks.get(i).activated) {
                return true;
            }
        }
        return false;
    }
}
