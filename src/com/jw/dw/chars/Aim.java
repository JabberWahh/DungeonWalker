package com.jw.dw.chars;

import com.jw.dw.Ambient.*;
import com.jw.dw.gui.WorldField;
import com.jw.dw.randInt;

import java.util.ArrayList;

/**
 * Created by vahma on 04.05.15.
 */
public class Aim {
    public Integer posX = 0;
    public Integer posY = 0;
    private static Aim instance;

    private Aim(){}

    public static Aim GetInstance() {
        if (instance == null) {
            instance = new Aim();
        }
        return instance;
    }

    public void SetPosition(int x, int y){
        posX = x;
        posY = y;
    }

    public void SetAimToRandomDoor(){

        WorldField wf = WorldField.GetInstance();
        ArrayList<ActionSpot> asList = wf.actionSpots;

        for(int i=asList.size()-1; i>-1;i--){
            ArrayList apList = asList.get(i).actionPoints;
            for(int j=apList.size()-1;j>-1;j--){
                ActionPoint ap = (ActionPoint)apList.get(j);
                if(wf.worldField[ap.x][ap.y] == AmbientWall.icon){
                    apList.remove(j);
                }
            }
            if(apList.size() == 0){
                asList.remove(i);
            }
        }

        ActionSpot as2 = asList.get(randInt.GetRandInt(0, asList.size() - 1));
        ArrayList apList2 = as2.actionPoints;
        ActionPoint ap = (ActionPoint)apList2.get(randInt.GetRandInt(0, apList2.size() - 1));

        posX = ap.x;
        posY = ap.y;

    }
}
