package com.jw.dw.Ambient;

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

    public CellMap(int xPos, int yPos, boolean wall , String icon, AmbientEnum cellkind){
        x = xPos;
        y= yPos;
        this.wall = wall;
        this.icon = icon;
        this.kind = cellkind;
    }

}
