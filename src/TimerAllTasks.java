import com.jw.dw.AI.AStar;
import com.jw.dw.Ambient.AmbientDoor;
import com.jw.dw.Ambient.AmbientEmpty;
import com.jw.dw.Ambient.AmbientEnum;
import com.jw.dw.Ambient.CellMap;
import com.jw.dw.Phases;
import com.jw.dw.chars.EnemyCreator;
import com.jw.dw.chars.Hero;
import com.jw.dw.gui.WorldField;

import java.util.ArrayList;
import javax.swing.Timer;

/**
 * Created by vahma on 28.04.15.
 * Battle time
 */
class TimerAllTasks {

    private Hero hero;
    private EnemyCreator ec;
    private CharAction act;
    private ArrayList enemyList;
    public Phases phase;
    private CellMap[][] field;
    private boolean enemySpoted = false;
    private String tmpChar = AmbientEmpty.icon;

    private ArrayList route;
    //private Point tmpPoint;

    public TimerAllTasks(Hero h, EnemyCreator eCr, CharAction cAct, ArrayList eL) {
        hero = h;
        ec = eCr;
        act = cAct;
        enemyList = eL;

    }


    public void run(Timer tM) {

        //
        phase = Phases.MOOVING;
        //


        if (phase == Phases.FIGHT) {

            if (hero.GetHP() <= 50 && hero.GetHP() > 0 && !hero.needRest) {
                hero.needRest = true;

            }

            if (hero.GetHP() <= 0) {
                System.out.println("Hero is dead!");
                try {
                    tM.stop();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

            if (hero.needRest && !act.battleStarted && hero.GetHP() > 0) {
                //System.out.println("needRest = " + hero.needRest + "  hero.GetHP() " + hero.GetHP());
                HealingAction ha = HealingAction.GetInstance();
                if (!ha.healing) {
                    ha.Heal(hero);
                }

            }


            if (enemyList.isEmpty() && !hero.needRest && hero.GetHP() > 0) {


                enemyList = ec.GetEnemys();
                System.out.println("New battle!");

                CharAction act = CharAction.GetInstance();
                act.StartBattle(hero, enemyList);
            }
        }

        if (phase == Phases.MOOVING) {

            //Поиск пути
            AStar aStar = AStar.GetInstance();

            WorldField sf = WorldField.GetInstance();
            field = sf.GetField();

            if (!aStar.routeFound) {
                aStar.Start();
            }

            //Анимация перемещения

            if (aStar.routeFound) {

                if (aStar.arX.size() != 0) {
                    field[hero.posX][hero.posY].icon = tmpChar;
                    tmpChar = field[aStar.arX.get(aStar.arX.size() - 1)][aStar.arY.get(aStar.arY.size() - 1)].icon;



                    hero.SetPosition(aStar.arX.get(aStar.arX.size() - 1),aStar.arY.get(aStar.arY.size() - 1));
                    field[aStar.arX.get(aStar.arX.size() - 1)][aStar.arY.get(aStar.arY.size() - 1)].icon = Hero.icon;

                    AmbientEnum cellKind = field[aStar.arX.get(aStar.arX.size() - 1)][aStar.arY.get(aStar.arY.size() - 1)].kind;

                    //if(tmpChar == AmbientDoor.icon){
                    if(cellKind == AmbientEnum.Door){
                        hero.mooved = true;
                        act.DoorMooving();
                    }

                    aStar.arX.remove(aStar.arX.size() - 1);
                    aStar.arY.remove(aStar.arY.size() - 1);


                }

            }

        }


    }
}
