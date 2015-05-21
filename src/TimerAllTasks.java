import com.jw.dw.AI.AStar;
import com.jw.dw.Ambient.*;
import com.jw.dw.Items.Flask;
import com.jw.dw.Items.FlaskKind;
import com.jw.dw.Phases;
import com.jw.dw.chars.*;
import com.jw.dw.gui.WorldField;

import java.util.ArrayList;
import javax.swing.*;

/**
 * Created by vahma on 28.04.15.
 * battle time
 */
class TimerAllTasks {

    private final Hero hero;
    private final CharAction act;
    private ArrayList enemyList;
    public Phases phase;
    private boolean enemySpoted = false;
    private String tmpChar = AmbientEmpty.icon;

    private ArrayList route;
    //private Point tmpPoint;

    public TimerAllTasks(Hero h, CharAction cAct) {
        hero = h;
        act = cAct;
    }


    public void run() {

        //
        //phase = Phases.MOOVING;
        //
        WorldField sf = WorldField.GetInstance();
        CellMap[][] field = sf.GetField();
        Enemy enemy = field[hero.posX][hero.posY].enemy;

        //Using flasks//
        //permanently healing
        if ((hero.getMAXHP() * 0.3) > hero.getHP()) {
            for (int i = 0; i < hero.flasks.size(); i++) {
                if (hero.flasks.get(i).flaskKind == FlaskKind.HealPermanent) {
                    hero.setHP();
                    hero.flasks.remove(i);
                    break;
                }
            }
        }


        //healing by regen
        //Activation
        if (!Flask.isActivatedFlaskByKind(FlaskKind.HealTime) && (hero.getMAXHP() * 0.3) > hero.getHP()) {
            boolean isPermanent = false;
            for (int i = 0; i < hero.flasks.size(); i++) {
                if (hero.flasks.get(i).flaskKind == FlaskKind.HealPermanent) {
                    isPermanent = true;//waiting till low hp to use permanent
                }
            }
            if (!isPermanent) {
                for (int i = 0; i < hero.flasks.size(); i++) {
                    if (hero.flasks.get(i).flaskKind == FlaskKind.HealTime) {
                        hero.flasks.get(i).activated = true;
                        hero.flasks.get(i).value = hero.getMAXHP() * 2;
                        break;
                    }
                }
            }
        }


        if (enemy != null && enemy.elite) {
            //Flask of stone
            if (!Flask.isActivatedFlaskByKind(FlaskKind.Armor)) {
                for (int i = 0; i < hero.flasks.size(); i++) {
                    if (hero.flasks.get(i).flaskKind == FlaskKind.Armor) {
                        hero.flasks.get(i).activated = true;
                        break;
                    }
                }
            }
            //Flask of anger
            if (!Flask.isActivatedFlaskByKind(FlaskKind.Damage)) {
                for (int i = 0; i < hero.flasks.size(); i++) {
                    if (hero.flasks.get(i).flaskKind == FlaskKind.Damage) {
                        hero.flasks.get(i).activated = true;
                        break;
                    }
                }
            }
            //Flask of health
            if (hero.getMAXHP() > hero.getHP()) {
                for (int i = 0; i < hero.flasks.size(); i++) {
                    if (hero.flasks.get(i).flaskKind == FlaskKind.PlusHP) {
                        hero.flasks.remove(i);
                        hero.setHP(hero.getHP() + hero.getMAXHP());
                        break;
                    }
                }
            }
        }

        //Using
        if (Flask.isActivatedFlaskByKind(FlaskKind.HealTime)) {
            for (int i = 0; i < hero.flasks.size(); i++) {
                if (hero.flasks.get(i).flaskKind == FlaskKind.HealTime && hero.flasks.get(i).activated) {
                    int spit = hero.getMAXHP() / 50;
                    hero.flasks.get(i).value = hero.flasks.get(i).value - spit;
                    hero.addHP(spit);
                    if (hero.flasks.get(i).value <= 0) {
                        hero.flasks.remove(i);
                    }
                    break;
                }
            }
        }

        if (Flask.isActivatedFlaskByKind(FlaskKind.Armor)) {
            for (int i = 0; i < hero.flasks.size(); i++) {
                if (hero.flasks.get(i).flaskKind == FlaskKind.Armor) {
                    if (hero.flasks.get(i).value <= 0) {
                        hero.flasks.remove(i);
                    } else {
                        hero.flasks.get(i).value--;
                    }
                }
            }
        }

        if (Flask.isActivatedFlaskByKind(FlaskKind.Damage)) {
            for (int i = 0; i < hero.flasks.size(); i++) {
                if (hero.flasks.get(i).flaskKind == FlaskKind.Damage) {
                    if (hero.flasks.get(i).value <= 0) {
                        hero.flasks.remove(i);
                    } else {
                        hero.flasks.get(i).value--;
                    }
                }
            }
        }

        //Minusing mega health
        if (hero.getHP() > hero.getMAXHP()) {
            hero.setHP(hero.getHP() - 1);
        }


        //End using flasks//

        if (phase == Phases.FIGHT && hero.getHP() > 0) {
            CharAction act = CharAction.getInstance();
            //Enemy enemy = ec.GetEnemys();


            if (act.battleStarted) {
                act.battle(hero, enemy);

            }

            if (enemy != null && enemy.getHP() <= 0) {
                phase = Phases.MOOVING;
            }

            /*if (hero.getHP() <= 50 && hero.getHP() > 0 && !act.battleStarted) {
                //hero.needRest = true;
                phase = Phases.HEALING;

            }*/

            if (hero.getHP() <= 0) {
                System.out.println("Hero is dead!");
                phase = Phases.RESURECTION;
                hero.timeToResurect = 0;
                hero.deathCount++;

            }

            /*if (hero.needRest && !act.battleStarted && hero.GetHP() > 0) {
                //System.out.println("needRest = " + hero.needRest + "  hero.GetHP() " + hero.GetHP());
                HealingAction ha = HealingAction.getInstance();
                if (!ha.healing) {
                    ha.Heal(hero);
                }

            }*/

            if (!act.battleStarted) {
                act.battleStarted = true;
            }




            /*if (enemyList.isEmpty() && !hero.needRest && hero.GetHP() > 0) {


                enemyList = ec.GetEnemys();
                System.out.println("New battle!");

                com.jw.dw.chars.CharAction act = com.jw.dw.chars.CharAction.getInstance();
                act.StartBattle(hero, enemyList);
            }*/
        }

        /*if (phase == Phases.HEALING) {
            HealingAction ha = HealingAction.GetInstance();
            ha.Heal(hero);
            if (hero.getHP() >= 0) {
                phase = Phases.MOOVING;
            }
        }*/
        if (phase == Phases.RESURECTION) {
            hero.timeToResurect = hero.timeToResurect + 10;
            System.out.println(hero.timeToResurect);
            if (hero.timeToResurect >= (int) (100 * (hero.lvl / 1.5))) {
                System.out.println("I'm alive!");
                sf.SetField();
                AStar aStar = AStar.GetInstance();
                aStar.routeFound = false;
                phase = Phases.MOOVING;
                hero.setHP();
                tmpChar = "☼";
            }

        }

        if (phase == Phases.MOOVING) {

            //Поиск пути
            AStar aStar = AStar.GetInstance();


            if (!aStar.routeFound) {
                Aim aim = Aim.GetInstance();
                aim.SetAim(false);
                aStar.Start();
            }

            //Анимация перемещения

            if (aStar.routeFound) {

                if (aStar.arX.size() != 0) {
                    field[hero.posX][hero.posY].icon = tmpChar;
                    tmpChar = field[aStar.arX.get(aStar.arX.size() - 1)][aStar.arY.get(aStar.arY.size() - 1)].icon;


                    hero.SetPosition(aStar.arX.get(aStar.arX.size() - 1), aStar.arY.get(aStar.arY.size() - 1));
                    field[aStar.arX.get(aStar.arX.size() - 1)][aStar.arY.get(aStar.arY.size() - 1)].icon = Hero.icon;

                    CellMap prevCell = field[aStar.arX.get(aStar.arX.size() - 1)][aStar.arY.get(aStar.arY.size() - 1)];
                    field[aStar.arX.get(aStar.arX.size() - 1)][aStar.arY.get(aStar.arY.size() - 1)].activated = true;

                    if (prevCell.kind == AmbientEnum.Chest) {
                        act.lightAround();
                    }

                    aStar.arX.remove(aStar.arX.size() - 1);
                    aStar.arY.remove(aStar.arY.size() - 1);
                    //if(tmpChar == AmbientDoor.icon){


                    if (aStar.arX.size() == 0 || prevCell.kind == AmbientEnum.Door) {
                        if (prevCell.kind == AmbientEnum.ActionSpot) {
                            phase = Phases.FIGHT;
                        }
                        if (prevCell.kind == AmbientEnum.Door) {
                            hero.mooved = true;
                            act.doorMooving();
                        }
                        if (prevCell.exit) {
                            sf.lvl++;
                            sf.SetField();
                            phase = Phases.MOOVING;
                        }
                        //Opening chest
                        if (prevCell.kind == AmbientEnum.Chest) {
                            act.chestOpening();

                        }
                        /*if (!field[hero.posX][hero.posY].activated) {
                            aStar.routeFound = false;
                            Aim aim = Aim.getInstance();
                            aim.SetAim();
                            aStar.Start();
                            field[hero.posX][hero.posY].activated = true;
                        }*/

                        if (aStar.arX.size() == 0) {
                            aStar.routeFound = false;
                            aStar.Start();
                        }
                    }


                }

            } else {
                Aim aim = Aim.GetInstance();
                aim.SetAim(true);
                aStar.Start();
            }

        }


    }


}
