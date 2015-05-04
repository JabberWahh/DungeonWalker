package com.jw.dw.Ambient;

import com.jw.dw.randInt;

import java.util.ArrayList;

/**
 * Created by vahma on 03.05.15.
 */
public class ActionSpot {
    public int x;
    public int y;
    public int lightXUp;
    public int lightXDown;
    public int lightYUp;
    public int lightYDown;
    public ArrayList<ActionPoint> actionPoints;
    public boolean exit = false;
    public boolean activated = false;

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



}
