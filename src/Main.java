
import com.jw.dw.Phases;
import com.jw.dw.chars.CharAction;
import com.jw.dw.chars.Hero;

import com.jw.dw.gui.Rasterizer;
import com.jw.dw.gui.WorldField;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;


import javax.swing.*;

import javafx.scene.Scene;

import javafx.scene.Group;

import javafx.scene.paint.Color;


/**
 * Created by vahma on 27.04.15.
 * Dungeon walker
 */
public class Main extends Application {

    private static Timer timer;
    private static Group root;
    private static Rasterizer rastr;

    //gui initialization

    private AnimationTimer at = new AnimationTimer() {
        @Override
        public void handle(long now) {
            rastr.Draw(root);
        }
    };


    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Dungeon Walker");
        root = new Group();
        rastr = new Rasterizer();
        //

        Scene sC = new Scene(root, 1200, 880, Color.rgb(34, 34, 34, 1));

        //Start render
        Timer timerRendr = new Timer(1000, e -> rastr.startDraw = true);

        WorldField sf = WorldField.GetInstance();
        at.start();
        //


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

        Hero hero =  Hero.GetInstance();
        hero.SetHP(100);
        hero.SetDmg(5);




        CharAction act = CharAction.GetInstance();
        //act.StartBattle(hero, enemyList);

        TimerAllTasks task = new TimerAllTasks(hero, act);

        //Fighting phase
        //timer = new Timer(100, e -> task.run(timer));

        //timer.start();
        //

        //Mooving phase
        timer = new Timer(1000, e -> task.run(timer));
        task.phase = Phases.MOOVING;
        timer.start();
        //

        launch(args);


    }
}
