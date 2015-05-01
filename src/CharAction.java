import com.jw.dw.chars.Enemy;
import com.jw.dw.chars.Hero;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by vahma on 27.04.15.
 * Figting mode
 */
public class CharAction {

    private Hero hero;
    private ArrayList enemy;
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

    public void StartBattle(Hero h, ArrayList e) {

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




    }

}
