import com.jw.dw.AI.AStar;
import com.jw.dw.Ambient.AmbientEmpty;
import com.jw.dw.Ambient.AmbientEnum;
import com.jw.dw.Ambient.CellMap;
import com.jw.dw.Phases;
import com.jw.dw.chars.*;
import com.jw.dw.gui.WorldField;

import java.util.ArrayList;
import javax.swing.Timer;

/**
 * Created by vahma on 28.04.15.
 * Battle time
 */
class TimerAllTasks {

    private Hero hero;
    private CharAction act;
    private ArrayList enemyList;
    public Phases phase;
    private CellMap[][] field;
    private boolean enemySpoted = false;
    private String tmpChar = AmbientEmpty.icon;

    private ArrayList route;
    //private Point tmpPoint;

    public TimerAllTasks(Hero h, CharAction cAct) {
        hero = h;
        act = cAct;
    }


    public void run(Timer tM) {

        //
        //phase = Phases.MOOVING;
        //
        WorldField sf = WorldField.GetInstance();
        field = sf.GetField();

        if (phase == Phases.FIGHT && hero.GetHP() > 0) {
            CharAction act = CharAction.GetInstance();
            //Enemy enemy = ec.GetEnemys();

            Enemy enemy = field[hero.posX][hero.posY].enemy;

            if (act.battleStarted) {
                act.Battle(hero, enemy);

            }

            if (enemy.GetHP() <= 0) {
                phase = Phases.MOOVING;
            }

            if (hero.GetHP() <= 50 && hero.GetHP() > 0 && !hero.needRest && !act.battleStarted) {
                //hero.needRest = true;
                phase = Phases.HEALING;

            }

            if (hero.GetHP() <= 0) {
                System.out.println("Hero is dead!");
                phase = Phases.RESURECTION;

            }

            /*if (hero.needRest && !act.battleStarted && hero.GetHP() > 0) {
                //System.out.println("needRest = " + hero.needRest + "  hero.GetHP() " + hero.GetHP());
                HealingAction ha = HealingAction.GetInstance();
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

                com.jw.dw.chars.CharAction act = com.jw.dw.chars.CharAction.GetInstance();
                act.StartBattle(hero, enemyList);
            }*/
        }

        if(phase == Phases.HEALING){
            HealingAction ha = HealingAction.GetInstance();
            ha.Heal(hero);
            if(hero.GetHP()>=0){
                phase = Phases.MOOVING;
            }
        }

        if (phase == Phases.MOOVING) {

            //Поиск пути
            AStar aStar = AStar.GetInstance();


            if (!aStar.routeFound) {
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
                        act.LightAround();
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
                            act.DoorMooving();
                        }
                        if (prevCell.exit) {
                            sf.lvl++;
                            sf.SetField();
                            phase = Phases.MOOVING;
                        }

                        if (aStar.arX.size() == 0) {
                            aStar.routeFound = false;
                            aStar.Start();
                        }
                    }


                }

            }

        }


    }
}
