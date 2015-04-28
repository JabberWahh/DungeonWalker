import com.jw.dw.chars.Enemy;
import com.jw.dw.chars.EnemyCreator;
import com.jw.dw.chars.Hero;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.Timer;


/**
 * Created by vahma on 27.04.15.
 */
public class Main {

    public static Timer timer;

    public static void main(String[] args) {

        System.out.println("Started...");

        Hero hero = new Hero();
        hero.SetHP(100);
        hero.SetDmg(5);

        EnemyCreator ec = new EnemyCreator();
        ArrayList enemyList = ec.GetEnemys();


        CharAction act = CharAction.GetInstance();
        act.StartBattle(hero, enemyList);




        /*Timer timerMain = new Timer();

        TimerTask task = new TimerAllTasks(timerMain, hero, ec, act);

        timerMain.scheduleAtFixedRate(task, 100, 100);*/

        TimerAllTasks task = new TimerAllTasks(hero, ec, act, enemyList);


        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                task.run(timer);

                //TimerAllTasks task = new TimerAllTasks(hero, ec, act);

            }
        });

        timer.start();


    }
}
