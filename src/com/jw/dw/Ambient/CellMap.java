package com.jw.dw.Ambient;

/**
 * Created by vahma on 04.05.15.
 */
public class CellMap {
    public int x;
    public int y;
    public boolean wall = true;
    public String icon = AmbientWall.icon;

    public CellMap(int xPos, int yPos, boolean canWalk, String iconShow){
        x = xPos;
        y= yPos;
        wall = canWalk;
        icon = iconShow;
    }

}
