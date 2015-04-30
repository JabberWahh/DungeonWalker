package com.jw.dw.gui;


import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.animation.*;


/**
 * Created by vahma on 28.04.15.
 * Drawer
 */
public class Rasterizer {
    private static Timeline timeline;
    private static Group root;
    public boolean startDraw;

    public void Draw(Group rt) {

        root = rt;
        int x = 10;
        int y = 10;
        int red = 255;
        int green = 40;
        int blue = 50;



        /*Text text = new Text(x, y, "JavaFX 2.0");
        text.setFont(Font.font("Droid Sans Bold", FontWeight.BOLD, 20));

        text.setFill(Color.rgb(red, green, blue, .99));
        text.setRotate(0);
        root.getChildren().add(text);*/




        if (startDraw) {
            root.getChildren().clear();
            for (int i = 1; i < 20; i++) {
                for (int j = 1; j < 20; j++) {

                    int rnd = (int) (Math.random() * 100);
                    System.out.println(rnd);
                    String ch;
                    if (rnd >= 98) {
                        ch = "O";
                    } else {
                        ch = "X";
                    }

                    Text text = new Text(x + (i * 15), y + (j * 17), ch);
                    text.setFont(Font.font("Droid Sans Bold", FontWeight.BOLD, 20));

                    text.setFill(Color.rgb(0, 153, 51, 1));
                    text.setRotate(0);

                    root.getChildren().add(text);

                }
            }
            startDraw=false;
        }


       /* timeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {

                            }
                        }
                ),
                new KeyFrame(Duration.seconds(2)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();*/


    }

}


