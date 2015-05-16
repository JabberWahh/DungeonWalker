package com.jw.dw.Ambient;

import com.jw.dw.chars.Enemy;

/**CellMap
 * Created by vahma on 04.05.15.
 */
public class CellMap {
    public boolean wall = true;
    public String icon = AmbientWall.icon;
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
