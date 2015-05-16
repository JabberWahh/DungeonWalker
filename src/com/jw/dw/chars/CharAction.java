package com.jw.dw.chars;

import com.jw.dw.Ambient.ActionPoint;
import com.jw.dw.Ambient.ActionSpot;
import com.jw.dw.Items.Armour;
import com.jw.dw.Items.Flask;
import com.jw.dw.Items.FlaskKind;
import com.jw.dw.Items.Weapon;
import com.jw.dw.gui.WorldField;
import com.jw.dw.randInt;

import java.util.ArrayList;


/**
 * Created by vahma on 27.04.15.
 * Figting mode
 */
public class CharAction {

    public boolean battleStarted;
    public int dmgToHero;
    public int dmgToEnemy;

    private static CharAction instance;

    private CharAction() {
    }

    public static CharAction getInstance() {
        if (instance == null) {
            instance = new CharAction();
        }
        return instance;
    }

    /*public void StartBattle(Hero h, Enemy e) {

        hero = h;
        enemy = e;


        if (!battleStarted) {
            battleStarted = true;
            Timer timer2 = new Timer();

            TimerTask task = new TimerTask() {
                public void run() {

                    //Iterator<Enemy> iterEnemy = enemy.iterator();

                    // while (iterEnemy.hasNext()) {
                    for (Iterator<Enemy> iterEnemy = enemy.iterator(); iterEnemy.hasNext(); ) {

                        Enemy enOne = iterEnemy.next();

                        int dmgToHero = enOne.GetDmg();
                        int dmgToEnemy = hero.GetDmg();

                        hero.SetHP(hero.GetHP() - dmgToHero);
                        enOne.SetHP(enOne.GetHP() - dmgToEnemy);
                        System.out.println("H -> " + dmgToEnemy + " dmg., " + hero.GetHP() + " hp left. E@" + enOne.enemyName + "@ ->" + dmgToHero + " dmg., " + enOne.GetHP());

                        if (enOne.GetHP() <= 0) {
                            if (!enemy.isEmpty()) {
                                iterEnemy.remove();
                            }
                        }

                        if (hero.GetHP() <= 0 || enemy.isEmpty()) {
                            battleStarted = false;
                            try {
                                timer2.cancel();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        }

                    }
                }
            };
            timer2.scheduleAtFixedRate(task, 100, 100);
        }


    }*/

    public void battle(Hero h, Enemy e) {


        dmgToHero = e.getDmg();
        dmgToEnemy = h.getDmg();

        //Checking for flasks
        if (Flask.isActivatedFlaskByKind(FlaskKind.Damage)) {
            dmgToEnemy = dmgToEnemy * 4;
        }
        if (dmgToHero <= 0) {
            dmgToHero = 1;
        }
        if (dmgToEnemy <= 0) {
            dmgToEnemy = 1;
        }
        //

        h.setHP(h.getHP() - dmgToHero);
        e.setHP(e.getHP() - dmgToEnemy);
        System.out.println("H -> " + dmgToEnemy + " dmg., " + h.getHP() + " hp left. E@" + e.icon + "@ ->" + dmgToHero + " dmg., " + e.getHP());


        if (h.getHP() <= 0 || e.getHP() <= 0) {
            battleStarted = false;
        }
        if (h.getHP() > 0 && e.getHP() <= 0) {
            h.xp = h.xp + e.xp;
            h.monstersKilled++;
            if (e.elite) {
                flaskFinding();
            }
            if (h.strongestEnemy == null) {
                h.strongestEnemy = e;
            } else {
                if (h.strongestEnemy.hpmax < e.hpmax) {
                    h.strongestEnemy = e;
                }
            }
            if (h.xp >= h.lvl * 150) {
                h.lvl++;
                if (h.getHP() < h.getMAXHP()) {//if not mega health then restore all
                    h.setHP();
                }
                h.SetDmg();
            }

        }


    }


    public void doorMooving() {
        Hero hero = Hero.getInstance();
        if (hero.mooved) {
            WorldField wf = WorldField.GetInstance();

            hero.mooved = false;
            //System.out.println("hero " + hero.posX + " " + hero.posY);

            ArrayList<ActionSpot> asList = wf.actionSpots;
            boolean actionFound = false;
            int neededActionSpot = -1;
            for (int i = 0; i < asList.size() && !actionFound; i++) {
                ArrayList<ActionPoint> apList;
                apList = asList.get(i).actionPoints;
                for (int j = 0; j < apList.size() && !actionFound; j++) {
                    ActionPoint ap = apList.get(j);
                    //System.out.println("ap " + ap.x + " " + ap.y);
                    if (ap.x == hero.posX && ap.y == hero.posY && !asList.get(i).activated) {
                        neededActionSpot = i;
                        asList.get(i).activated = true;
                        actionFound = true;
                    }
                }
            }

            if (neededActionSpot > -1) {
                ActionSpot actionSpot = asList.get(neededActionSpot);

                //Do smth with ActionSpot

                //Lightning
                for (int i = actionSpot.lightXUp - 1; i < actionSpot.lightXDown + 2; i++) {
                    for (int j = actionSpot.lightYUp - 1; j < actionSpot.lightYDown + 2; j++) {
                        wf.worldField[i][j].visible = true;
                    }
                }


                //Deletig spot
                asList.remove(neededActionSpot);
            }
        }
    }

    public void chestOpening() {
        Hero hero = Hero.getInstance();
        WorldField wf = WorldField.GetInstance();

        int rnd = randInt.GetRandInt(1, 2);//1 of 5 for weapon
        if (rnd == 1) {
            //get weapon
            Weapon weapon = new Weapon(wf.lvl);
            if (hero.weapon == null) {
                hero.weapon = weapon;
            }
            if (weapon.getStansartDmg() > hero.weapon.getStansartDmg()) {
                hero.weapon = weapon;
            }
        }

        rnd = randInt.GetRandInt(1, 2);//1 of 5 for armour
        if (rnd == 1) {
            //get armour
            Armour armour = new Armour(wf.lvl);
            boolean needIt = true;
            for (int i = hero.armour.size() - 1; i == 0; i--) {
                if (hero.armour.get(i).armourPart == armour.armourPart && hero.armour.get(i).getStansartArmour() > armour.getStansartArmour()) {
                    needIt = false;
                } else if (hero.armour.get(i).getStansartArmour() > armour.getStansartArmour()) {
                    hero.armour.remove(i);
                }
            }
            if (needIt) {
                hero.armour.add(armour);
            }
        }

        //flask
        flaskFinding();

    }

    public void flaskFinding() {
        Hero hero = Hero.getInstance();
        int rnd = randInt.GetRandInt(1, 2);
        if (rnd == 1) {
            int rndn = randInt.GetRandInt(1, 5); //number of flasks
            for (int i = 0; i < rndn; i++) {
                int rndK = randInt.GetRandInt(1, 20);//Kind of flask
                if (rndK < 10) {
                    hero.flasks.add(new Flask(FlaskKind.HealPermanent));
                } else if (rndK < 15) {
                    hero.flasks.add(new Flask(FlaskKind.HealTime));
                } else if (rndK < 17) {
                    hero.flasks.add(new Flask(FlaskKind.PlusHP));
                } else if (rndK < 18) {
                    hero.flasks.add(new Flask(FlaskKind.Armor));
                } else {
                    hero.flasks.add(new Flask(FlaskKind.Damage));
                }

            }


        }
    }

    public void lightAround() {
        Hero hero = Hero.getInstance();
        WorldField wf = WorldField.GetInstance();
        for (int i = hero.posX - 1; i < hero.posX + 2; i++) {
            for (int j = hero.posY - 1; j < hero.posY + 2; j++) {
                wf.worldField[i][j].visible = true;
            }
        }
    }


}
