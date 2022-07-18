
import com.jw.dw.Phases;
import com.jw.dw.chars.CharAction;
import com.jw.dw.chars.Hero;


import com.jw.dw.gui.Rasterizer2;
import com.jw.dw.gui.ShowingStage;
import com.jw.dw.gui.WorldField;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.stage.Stage;


import javax.swing.*;

import javafx.scene.Scene;

import javafx.scene.Group;

import javafx.scene.paint.Color;

import java.util.Objects;

public class Main extends Application {

    private static Group root;
    private static Rasterizer2 rastr;

    private final AnimationTimer at = new AnimationTimer() {
        @Override
        public void handle(long now) {
            rastr.Draw(root);
        }
    };

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dungeon Walker ver 0.05");
        root = new Group();
        rastr = new Rasterizer2();
        //

        Scene sC = new Scene(root, 1800, 1040, Color.rgb(34, 34, 34, 1));

        sC.setOnKeyPressed(ke -> {
            if (ke.getCode() == KeyCode.I) {
                if (rastr.showingStage == ShowingStage.INVENTORY) {
                    rastr.showingStage = ShowingStage.MAP;
                } else {
                    rastr.showingStage = ShowingStage.INVENTORY;
                }
            }
            if (ke.getCode() == KeyCode.M) {
                rastr.showingStage = ShowingStage.MAP;
            }
        });

        //Start render
        Timer timerRendr = new Timer(1000, e -> rastr.startDraw = true);

        WorldField sf = WorldField.GetInstance();
        at.start();
        //

        Image applicationIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("ADOMIcon2.png")));
        primaryStage.getIcons().add(applicationIcon);

        primaryStage.setScene(sC);
        primaryStage.show();
        timerRendr.start();

        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });

    }

    ///////////////////////////////

    public static void main(String[] args) {
        System.out.println("Started...");

        Hero hero = Hero.getInstance();

        CharAction act = CharAction.getInstance();

        TimerAllTasks task = new TimerAllTasks(hero, act);

        //Mooving phase
        Timer timer = new Timer(1000, e -> task.run());
        task.phase = Phases.MOOVING;
        timer.start();
        //
        launch(args);
    }
}
