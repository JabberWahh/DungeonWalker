package com.jw.dw.Ambient;

import com.jw.dw.gui.WorldField;
import com.jw.dw.randInt;

import java.util.ArrayList;

public class ActionSpot {
    public int x;
    public int y;
    public int lightXUp;
    public int lightXDown;
    public int lightYUp;
    public int lightYDown;
    public ArrayList<ActionPoint> actionPoints;
    public boolean activated = false;
    public String icon = "○";


    public ActionSpot(int xUp, int xDown, int yUp, int yDown) {
        actionPoints = new ArrayList<>();
        x = randInt.GetRandInt(xUp, xDown);
        y = randInt.GetRandInt(yUp, yDown);
        lightXUp = xUp;
        lightXDown = xDown;
        lightYUp = yUp;
        lightYDown = yDown;
    }

    public void AddActionPoint(int xDoorPos, int yDoorPos) {
        actionPoints.add(new ActionPoint(xDoorPos, yDoorPos));
    }


    public static void RemoveUnnecessaryPoints(){
        WorldField wf = WorldField.GetInstance();
        ArrayList<ActionSpot> asList = wf.actionSpots;

        for(int i=asList.size()-1; i>-1;i--){
            ArrayList<ActionPoint> apList = asList.get(i).actionPoints;
            for(int j=apList.size()-1;j>-1;j--){
                ActionPoint ap = apList.get(j);
                if(wf.worldField[ap.x][ap.y].wall){
                    apList.remove(j);
                }
            }
            if(apList.size() == 0){
                asList.remove(i);
            }
        }

        //Adding exit spot

        ActionSpot as = asList.get(randInt.GetRandInt(0, asList.size() - 1));
        //as.exit = true;
        wf.worldField[as.x][as.y].icon = "☼";
        wf.worldField[as.x][as.y].exit = true;
        wf.worldField[as.x][as.y].enemy = null;

    }


}
