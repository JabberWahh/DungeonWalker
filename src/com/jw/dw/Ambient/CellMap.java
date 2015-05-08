package com.jw.dw.Ambient;

import com.jw.dw.chars.Enemy;

/**
 * Created by vahma on 04.05.15.
 */
public class CellMap {
    public int x;
    public int y;
    public boolean wall = true;
    public String icon = AmbientWall.icon;
    public boolean visible = false;
    public AmbientEnum kind;
    public boolean activated = false;
    public boolean exit = false;
    public Enemy enemy;

    public CellMap(int xPos, int yPos, boolean wall , String icon, AmbientEnum cellkind){
        x = xPos;
        y= yPos;
        this.wall = wall;
        this.icon = icon;
        this.kind = cellkind;
    }

}
