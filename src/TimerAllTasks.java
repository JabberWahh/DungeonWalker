import com.jw.dw.chars.Enemy;
import com.jw.dw.chars.EnemyCreator;
import com.jw.dw.chars.Hero;

import java.util.ArrayList;
import javax.swing.Timer;

/**
 * Created by vahma on 28.04.15.
 */
public class TimerAllTasks {

    public Hero hero;
    public EnemyCreator ec;
    public CharAction act;
    public ArrayList enemyList;
    public Timer timerMain;


    public TimerAllTasks(Hero h, EnemyCreator eCr, CharAction cAct, ArrayList eL) {
        hero = h;
        ec = eCr;
        act = cAct;
        enemyList = eL;


    }


    public void run(Timer tM) {
        timerMain = tM;

        if (hero.GetHP() <= 50 && hero.GetHP() > 0 && !hero.needRest) {
            hero.needRest = true;

        }

        if (hero.GetHP() <= 0) {
            System.out.println("Hero is dead!");
            try {
                timerMain.stop();
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
}
