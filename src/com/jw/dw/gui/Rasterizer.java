package com.jw.dw.gui;


import com.jw.dw.Ambient.*;
import com.jw.dw.chars.Enemy;
import com.jw.dw.chars.Hero;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

import java.util.Objects;


/**
 * Created by vahma on 28.04.15.
 * Drawer
 */
public class Rasterizer {
    public boolean startDraw;

    public void Draw(Group root) {

        int x = 10;
        int y = 10;
        /*int red = 255;
        int green = 40;
        int blue = 50;
        */



        /*Text text = new Text(x, y, "JavaFX 2.0");
        text.setFont(Font.font("Droid Sans Bold", FontWeight.BOLD, 20));

        text.setFill(Color.rgb(red, green, blue, .99));
        text.setRotate(0);
        root.getChildren().add(text);*/

        WorldField sf = WorldField.GetInstance();
        //String[][] field = sf.SetField();
        CellMap[][] field = sf.GetField();

        if (startDraw) {
            root.getChildren().clear();
            for (int i = 0; i < sf.WIDTH; i++) {
                for (int j = 0; j < sf.HEIGHT; j++) {
                    if (field[i][j].visible) {
                        String ch = AmbientEmpty.icon;
                        if (field[i][j] != null) {
                            ch = field[i][j].icon;
                        }


                        Text text = new Text(x + (i * 15) + 15, y + (j * 17) + 17, ch);
                        text.setFont(Font.font("Droid Sans Bold", 16));

                        //Defaul color
                        text.setFill(Color.rgb(0, 153, 51, 1));
                        if (Objects.equals(ch, AmbientWall.icon)) {
                            text.setFill(Color.rgb(107, 71, 36));
                        }
                        if (Objects.equals(ch, Hero.icon)) {
                            text.setFill(Color.rgb(51, 173, 255));
                        }
                        if (field[i][j].enemy != null) {
                            if (field[i][j].enemy.GetHP() > 0) {
                                text.setFill(Color.rgb(255, 71, 25));
                            } else {
                                text.setFill(Color.rgb(5, 5, 5));
                            }
                        }
                        if (Objects.equals(ch, AmbientDoor.icon)) {
                            text.setFill(Color.rgb(194, 194, 214));
                        }
                        if (Objects.equals(ch, "A")) {
                            text.setFill(Color.rgb(255, 51, 153));
                        }
                        if (Objects.equals(ch, AmbientChest.icon)) {
                            text.setFill(Color.rgb(255, 255, 153));
                        }

                        text.setRotate(0);

                        root.getChildren().add(text);

                    }
                }
            }

            //Name
            Text text = new Text(800, 28, "Hero: " + Hero.GetInstance().heroName);
            text.setFont(Font.font("IncisedBlack Normal", 12));
            text.setFill(Color.rgb(255, 255, 153));
            root.getChildren().add(text);

            //Hero lvl
            text = new Text(800, 48, "Lvl: " + Hero.GetInstance().lvl);
            text.setFont(Font.font("Droid Sans Bold", 12));
            text.setFill(Color.rgb(255, 255, 153));
            root.getChildren().add(text);

            //Hero HP
            text = new Text(800, 68, "HP: ");
            text.setFont(Font.font("Droid Sans Bold", 12));
            text.setFill(Color.rgb(255, 255, 153));
            root.getChildren().add(text);

            String hp = "";
            for (int i = 1; i < (Hero.GetInstance().GetHP() / 10) + 1; i++) {
                hp = hp + "▓";
            }

            text = new Text(830, 68, hp);
            text.setFont(Font.font("Droid Sans Bold", 12));
            text.setFill(Color.rgb(255, 51, 51));
            root.getChildren().add(text);

            //Dungeon lvl
            text = new Text(800, 88, "Dungeon level: " + sf.lvl);
            text.setFont(Font.font("Droid Sans Bold", 12));
            text.setFill(Color.rgb(255, 255, 153));
            root.getChildren().add(text);

            startDraw = false;
        }


    }

}


