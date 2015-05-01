package com.jw.dw.gui;


import com.jw.dw.Ambient.AmbientWall;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.animation.*;

import java.util.Objects;


/**
 * Created by vahma on 28.04.15.
 * Drawer
 */
public class Rasterizer {
    private static Timeline timeline;
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
        String[][] field = sf.GetField();

        if (startDraw) {
            root.getChildren().clear();
            for (int i = 0; i < sf.WIDTH; i++) {
                for (int j = 0; j < sf.HEIGHT; j++) {


                    String ch = "•";
                    if (field[i][j] != null) {
                        ch = field[i][j];
                    }

                    Text text = new Text(x + (i * 15) + 15, y + (j * 17) + 17, ch);
                    text.setFont(Font.font("Droid Sans Bold", 16));

                    //Defaul color
                    text.setFill(Color.rgb(0, 153, 51, 1));
                    if (Objects.equals(ch, AmbientWall.icon)) {
                        text.setFill(Color.rgb(107, 71, 36));
                    }

                    text.setRotate(0);

                    root.getChildren().add(text);

                }
            }
            startDraw = false;
        }


    }

}

