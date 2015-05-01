package com.jw.dw.gui;

import com.jw.dw.Ambient.AmbientWall;
import com.jw.dw.chars.Enemy;
import com.jw.dw.chars.Hero;

/**
 * Created by vahma on 30.04.15.
 * WorldField
 */
public class WorldField {


    private static WorldField instance;

    private String[][] worlField;
    public final int WIDTH = 50;
    public final int HEIGHT = 50;
    private boolean seted;

    private WorldField() {
    }

    public String[][] GetField() {
        if (!seted) {
            SetField();
        }
        return worlField;
    }

    public static WorldField GetInstance() {
        if (instance == null) {
            instance = new WorldField();
            instance.SetField();
            instance.seted = false;
        }
        return instance;
    }

    public void SetField() {

        worlField = new String[WIDTH][HEIGHT];

        int rndX;
        int rndY;

        //Hero
        rndX = (int) ((Math.random() * (WIDTH - 2)) + 1);
        rndY = (int) ((Math.random() * (HEIGHT - 2)) + 1);
        //System.out.println("Hero set  " + rndX + "   " + rndY);
        worlField[rndX][rndY] = Hero.icon;


        //Enemy
        boolean enemySeted = false;

        while (!enemySeted) {
            rndX = (int) ((Math.random() * (WIDTH - 2)) + 1);
            rndY = (int) ((Math.random() * (HEIGHT - 2)) + 1);
            //System.out.println("Enemy set  " + rndX + "   " + rndY);
            enemySeted = true;
            worlField[rndX][rndY] = Enemy.icon;
        }
        //Ambient
        for (int i = 0; i < 200; i++) { //Количество препятствий

            rndX = (int) ((Math.random() * (WIDTH - 1)) + 1);
            rndY = (int) ((Math.random() * (HEIGHT - 1)) + 1);

            if (worlField[rndX][rndY] == null) {
                worlField[rndX][rndY] = AmbientWall.icon;
            }

        }
        for (int i = 0; i < WIDTH; i++) {
            worlField[i][0] = AmbientWall.icon;
            worlField[i][WIDTH - 1] = AmbientWall.icon;
        }
        for (int j = 0; j < HEIGHT; j++) {
            worlField[0][j] = AmbientWall.icon;
            worlField[HEIGHT - 1][j] = AmbientWall.icon;
        }
        seted = true;

    }


}
