package com.jw.dw.chars;

import com.jw.dw.Ambient.ActionPoint;
import com.jw.dw.Ambient.ActionSpot;
import com.jw.dw.gui.WorldField;

import java.util.ArrayList;


/**
 * Created by vahma on 27.04.15.
 * Figting mode
 */
public class CharAction {

    private Hero hero;
    private Enemy enemy;
    public boolean battleStarted;

    private static CharAction instance;

    private CharAction() {
    }

    public static CharAction GetInstance() {
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

    public void Battle(Hero h, Enemy e) {


        hero = h;
        enemy = e;


        int dmgToHero = enemy.GetDmg();
        int dmgToEnemy = hero.GetDmg();

        hero.SetHP(hero.GetHP() - dmgToHero);
        enemy.SetHP(enemy.GetHP() - dmgToEnemy);
        System.out.println("H -> " + dmgToEnemy + " dmg., " + hero.GetHP() + " hp left. E@" + enemy.icon + "@ ->" + dmgToHero + " dmg., " + enemy.GetHP());


        if (hero.GetHP() <= 0 || enemy.GetHP() <= 0) {
            battleStarted = false;
        }


    }

    public void DoorMooving() {
        Hero hero = Hero.GetInstance();
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

                //

                /*if (actionSpot.exit){
                    //Next lvl
                    wf.lvl ++;
                    wf.SetField();
                }*/
               /* else{
                    Aim aim = Aim.GetInstance();
                    aim.posX = actionSpot.x;
                    aim.posY = actionSpot.y;
                    AStar aStar = AStar.GetInstance();
                    aStar.Start();
                }*/


                //Deletig spot
                asList.remove(neededActionSpot);
            }
        }
    }

    public void LightAround() {
        Hero hero = Hero.GetInstance();
        WorldField wf = WorldField.GetInstance();
        for (int i = hero.posX - 1; i < hero.posX + 2; i++) {
            for (int j = hero.posY - 1; j < hero.posY + 2; j++) {
                wf.worldField[i][j].visible = true;
            }
        }
    }

    public static Enemy CreateEnemy() {

        Enemy enemy = new Enemy(50, 3, 1);
        return enemy;

    }

}
