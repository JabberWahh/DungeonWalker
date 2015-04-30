import com.jw.dw.chars.Hero;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by vahma on 27.04.15.
 * Starts when Hero needs a rest
 */
public class HealingAction {
    private Hero hero;
    private static HealingAction instance;

    public boolean healing;

    private HealingAction() {


    }

    public static HealingAction GetInstance() {
        if (instance == null) {
            instance = new HealingAction();
        }
        return instance;
    }

    public void Heal(Hero h) {
        hero = h;
        healing = true;

        Timer timer2 = new Timer();

        TimerTask task = new TimerTask() {
            public void run() {

                hero.SetHP(hero.GetHP() + 10);
                if (hero.GetHP() > 100) {
                    hero.SetHP(100);
                }

                System.out.println("H ++ " + hero.GetHP());

                if (hero.GetHP() == 100) {
                    hero.needRest = false;
                    healing = false;
                    try {
                        timer2.cancel();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }

            }
        };

        timer2.scheduleAtFixedRate(task, 100, 1000);

    }
}
