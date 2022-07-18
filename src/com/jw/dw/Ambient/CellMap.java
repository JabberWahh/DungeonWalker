package com.jw.dw.Ambient;

import com.jw.dw.chars.Enemy;

public class CellMap {
    public boolean wall;
    public String icon;
    public boolean visible = false;
    public AmbientEnum kind;
    public boolean activated = false;
    public boolean exit = false;
    public Enemy enemy;

    public CellMap(String icon, AmbientEnum cellkind){
        this.wall = true;
        this.icon = icon;
        this.kind = cellkind;
    }

}
