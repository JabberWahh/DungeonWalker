
import com.jw.dw.chars.EnemyCreator;
import com.jw.dw.chars.Hero;

import com.jw.dw.gui.Rasterizer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;


import java.util.ArrayList;


import javax.swing.*;

import javafx.scene.Scene;

import javafx.scene.Group;

import javafx.scene.paint.Color;


/**
 * Created by vahma on 27.04.15.
 * Main process
 */
class Main extends Application {

    private static Timer timer;
    private static Timer timerRendr;
    private static Group root;
    private static Rasterizer rastr;

    private static Stage pS;
    private static Scene sC;
    //gui initialization

    private AnimationTimer at = new AnimationTimer() {
        @Override
        public void handle(long now) {
            rastr.Draw(root);
        }
    };


    @Override
    public void start(Stage primaryStage) {

        pS = primaryStage;

        //primaryStage.setTitle("Drawing Text");
        root = new Group();
        rastr = new Rasterizer();
        //
        //rastr.Draw(root);
        sC = new Scene(root, 800, 600, Color.rgb(34, 34, 34, 1));

        timerRendr = new Timer(1000, e -> rastr.startDraw = true);

        /*       timerRendr = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                rastr.startDraw = true;

            }
        });*/

        at.start();
        pS.setScene(sC);
        pS.show();


        timerRendr.start();

        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });

        /*
              primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
         */


    }

    ///////////////////////////////

    public static void main(String[] args) {


        System.out.println("Started...");

        Hero hero = new Hero();
        hero.SetHP(100);
        hero.SetDmg(5);

        EnemyCreator ec = new EnemyCreator();
        ArrayList enemyList = ec.GetEnemys();


        CharAction act = CharAction.GetInstance();
        act.StartBattle(hero, enemyList);


        TimerAllTasks task = new TimerAllTasks(hero, ec, act, enemyList);


        timer = new Timer(100, e -> task.run(timer));

        //timer.start();


        launch(args);

    }
}
