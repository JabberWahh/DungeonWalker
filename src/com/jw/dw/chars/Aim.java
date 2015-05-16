package com.jw.dw.chars;

import com.jw.dw.AI.AStar;
import com.jw.dw.Ambient.*;
import com.jw.dw.gui.WorldField;

/**Aim
 * Created by vahma on 04.05.15.
 */
public class Aim {
    public Integer posX = 0;
    public Integer posY = 0;
    private static Aim instance;

    private Aim() {
    }

    public static Aim GetInstance() {
        if (instance == null) {
            instance = new Aim();
        }
        return instance;
    }

    public void SetPosition(int x, int y) {
        posX = x;
        posY = y;
    }


    public void SetAim(boolean toDoor) {

        WorldField wf = WorldField.GetInstance();
        posX = 0;
        posY = 0;

        //Firstly chek for actionSpots
        for (int i = 0; i < wf.HEIGHT; i++) {
            for (int j = 0; j < wf.WIDTH; j++) {
                if (wf.worldField[i][j].kind == AmbientEnum.ActionSpot && wf.worldField[i][j].visible && !wf.worldField[i][j].activated) {
                    posX = i;
                    posY = j;
                }
            }
        }

        boolean doorChosen = false;
        if (posX == 0 &&!toDoor) {//for chests
            for (int i = 1; i < wf.HEIGHT &&!doorChosen; i++) {
                for (int j = 1; j < wf.WIDTH&&!doorChosen; j++) {
                    if (wf.worldField[i][j].kind == AmbientEnum.Chest && wf.worldField[i][j].visible && !wf.worldField[i][j].activated) {
                        posX = i;
                        posY = j;
                        AStar aStar = AStar.GetInstance();
                        aStar.Start();
                        if(aStar.routeFound){
                            doorChosen = true;
                        }
                    }
                }
            }
        }

        if (posX == 0) {
            Hero hero = Hero.getInstance();
            int tXUp = hero.posX - 2;
            int tXDown = hero.posX + 2;
            int tYUp = hero.posY - 2;
            int tYDown = hero.posY + 2;

            boolean doorFound = false;
            while (!doorFound) {
                tXUp--;
                tXDown++;
                tYUp--;
                tYDown++;
                if (tXUp < 2) {
                    tXUp = 2;
                }
                if (tYUp < 2) {
                    tYUp = 2;
                }
                if (tXDown > 48) {
                    tXDown = 48;
                }
                if (tYDown > 48) {
                    tYDown = 48;
                }

                for (int i = tXUp; i < tXDown; i++) {
                    for (int j = tYUp; j < tYDown; j++) {
                        if (wf.worldField[i][j].kind == AmbientEnum.Door && wf.worldField[i][j].visible && !wf.worldField[i][j].activated) {
                            posX = i;
                            posY = j;
                            doorFound = true;
                        }
                    }
                }
            }
        }

        if (posX == 0) {//for random doors
            for (int i = 0; i < wf.HEIGHT; i++) {
                for (int j = 0; j < wf.WIDTH; j++) {
                    if (wf.worldField[i][j].kind == AmbientEnum.Door && wf.worldField[i][j].visible && !wf.worldField[i][j].activated) {
                        posX = i;
                        posY = j;
                    }
                }
            }
        }

        /*if (posX == 0) {
            ArrayList<ActionSpot> asList = wf.actionSpots;

            ActionSpot as2 = asList.get(randInt.GetRandInt(0, asList.size() - 1));
            ArrayList apList2 = as2.actionPoints;
            ActionPoint ap = (ActionPoint) apList2.get(randInt.GetRandInt(0, apList2.size() - 1));

            posX = ap.x;
            posY = ap.y;

        }*/
    }
}
