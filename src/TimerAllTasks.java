import com.jw.dw.AI.AStar;
import com.jw.dw.Ambient.AmbientDoor;
import com.jw.dw.Ambient.AmbientEmpty;
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
    private String[][] field;
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
            /*while (!aStar.routeFound) {
                field = sf.GetField();
            }*/


            /*if (aStar.routeFound) {

                if (aStar.arX.size() != 0) {
                    boolean done = false;
                    for (int i = 0; i < sf.WIDTH; i++) {
                        for (int j = 0; j < sf.HEIGHT; j++) {
                            if (!done) {
                                if (Objects.equals(field[i][j], Hero.icon)) {
                                    field[i][j] = AmbientEmpty.icon;
                                    field[aStar.arX.get(aStar.arX.size() - 1)][aStar.arY.get(aStar.arY.size() - 1)] = Hero.icon;
                                    done = true;


                                }
                            }
                        }

                    }
                    aStar.arX.remove(aStar.arX.size() - 1);
                    aStar.arY.remove(aStar.arY.size() - 1);
                }
            }*/

            if (aStar.routeFound) {

                if (aStar.arX.size() != 0) {
                    field[hero.posX][hero.posY] = tmpChar;
                    tmpChar = field[aStar.arX.get(aStar.arX.size() - 1)][aStar.arY.get(aStar.arY.size() - 1)];



                    hero.SetPosition(aStar.arX.get(aStar.arX.size() - 1),aStar.arY.get(aStar.arY.size() - 1));
                    field[aStar.arX.get(aStar.arX.size() - 1)][aStar.arY.get(aStar.arY.size() - 1)] = Hero.icon;
                    if(tmpChar == AmbientDoor.icon){
                        act.Action();
                    }
                    aStar.arX.remove(aStar.arX.size() - 1);
                    aStar.arY.remove(aStar.arY.size() - 1);


                }

            }

        }


    }
}
