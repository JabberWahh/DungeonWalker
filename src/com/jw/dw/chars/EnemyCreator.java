package com.jw.dw.chars;

import java.util.ArrayList;

/**
 * Created by vahma on 27.04.15.
 * Creates enemies
 */
class EnemyCreator {


    public EnemyCreator() {
    }


    public ArrayList GetEnemys() {

        int rnd = (int) (Math.random() * 100 + 1);
        int count = 1;

        if (rnd > 90) {
            count = 3;
        } else if (rnd > 50) {
            count = 2;
        }

        ArrayList<Enemy> enemyBand = new ArrayList<>();
        for (int i = 1; i <= count; i++) {

           // enemyBand.add(new Enemy(50, 3, (int) (Math.random() * 100 + 1)));
        }

        //System.out.println("Created " + count + " enemys");
        return enemyBand;
    }
}
