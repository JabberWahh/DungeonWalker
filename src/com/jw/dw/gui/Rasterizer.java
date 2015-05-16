package com.jw.dw.gui;


import com.jw.dw.Ambient.*;
import com.jw.dw.Items.ArmourPart;
import com.jw.dw.Items.Flask;
import com.jw.dw.Items.FlaskKind;
import com.jw.dw.chars.CharAction;
import com.jw.dw.chars.Hero;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Objects;


/**
 * Created by vahma on 28.04.15.
 * Drawer
 */
public class Rasterizer {
    public boolean startDraw;
    public ShowingStage showingStage = ShowingStage.MAP;

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
            boolean fighting = false;
            if (showingStage == ShowingStage.MAP) {
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

                            if (field[i][j].enemy != null) {
                                if (field[i][j].enemy.getHP() > 0) {
                                    text.setFill(Color.rgb(255, 71, 25));
                                    fighting = true;
                                } else {
                                    text.setFill(Color.rgb(5, 5, 5));
                                }
                            }
                            //Hero
                            if (Objects.equals(ch, Hero.icon)) {
                                if (fighting) {
                                    text.setFill(Color.rgb(255, 71, 25));
                                } else {
                                    text.setFill(Color.rgb(51, 173, 255));
                                }
                                if (Hero.getInstance().getHP() <= 0) {
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

                            //text.setRotate(0);

                            root.getChildren().add(text);

                        }
                    }
                }
            }

            if (showingStage == ShowingStage.INVENTORY) { //Inventory
                for (int i = 1; i < sf.WIDTH - 1; i++) {
                    Text text = new Text((i * 15) + 15, 17, "═");
                    text.setFont(Font.font("Droid Sans Bold", 16));
                    text.setFill(Color.rgb(107, 71, 36));
                    root.getChildren().add(text);

                    text = new Text((i * 15) + 15, 850, "═");
                    text.setFont(Font.font("Droid Sans Bold", 16));
                    text.setFill(Color.rgb(107, 71, 36));
                    root.getChildren().add(text);

                    text = new Text((i * 15) + 15, 153, "═");
                    text.setFont(Font.font("Droid Sans Bold", 16));
                    text.setFill(Color.rgb(107, 71, 36));
                    root.getChildren().add(text);

                    text = new Text((i * 15) + 15, 512, "═");
                    text.setFont(Font.font("Droid Sans Bold", 16));
                    text.setFill(Color.rgb(107, 71, 36));
                    root.getChildren().add(text);

                    text = new Text(15, (i * 17) + 17, "║");
                    text.setFont(Font.font("Droid Sans Bold", 16));
                    text.setFill(Color.rgb(107, 71, 36));
                    root.getChildren().add(text);

                    text = new Text(750, (i * 17) + 17, "║");
                    text.setFont(Font.font("Droid Sans Bold", 16));
                    text.setFill(Color.rgb(107, 71, 36));
                    root.getChildren().add(text);
                }
                Text text = new Text(15, 17, "╔");
                text.setFont(Font.font("Droid Sans Bold", 16));
                text.setFill(Color.rgb(107, 71, 36));
                root.getChildren().add(text);

                text = new Text(15, 850, "╚");
                text.setFont(Font.font("Droid Sans Bold", 16));
                text.setFill(Color.rgb(107, 71, 36));
                root.getChildren().add(text);

                text = new Text(750, 17, "╗");
                text.setFont(Font.font("Droid Sans Bold", 16));
                text.setFill(Color.rgb(107, 71, 36));
                root.getChildren().add(text);

                text = new Text(750, 850, "╝");
                text.setFont(Font.font("Droid Sans Bold", 16));
                text.setFill(Color.rgb(107, 71, 36));
                root.getChildren().add(text);

                text = new Text(15, 153, "╠");
                text.setFont(Font.font("Droid Sans Bold", 16));
                text.setFill(Color.rgb(107, 71, 36));
                root.getChildren().add(text);

                text = new Text(750, 153, "╣");
                text.setFont(Font.font("Droid Sans Bold", 16));
                text.setFill(Color.rgb(107, 71, 36));
                root.getChildren().add(text);

                text = new Text(15, 512, "╠");
                text.setFont(Font.font("Droid Sans Bold", 16));
                text.setFill(Color.rgb(107, 71, 36));
                root.getChildren().add(text);

                text = new Text(750, 512, "╣");
                text.setFont(Font.font("Droid Sans Bold", 16));
                text.setFill(Color.rgb(107, 71, 36));
                root.getChildren().add(text);


                //Info

                text = new Text(30, 34, "Weapon:");
                text.setFont(Font.font("Droid Sans Bold", 12));
                text.setFill(Color.rgb(255, 255, 153));
                root.getChildren().add(text);

                if (Hero.getInstance().weapon != null) {
                    text = new Text(120, 34, Hero.getInstance().weapon.weaponName + " lvl." + Hero.getInstance().weapon.lvl);
                    text.setFont(Font.font("Droid Sans Bold", 12));
                    text.setFill(Hero.getInstance().weapon.color);
                    root.getChildren().add(text);
                }
                //////////////////////////////////////////////////////

                text = new Text(30, 51, "Armour");
                text.setFont(Font.font("Droid Sans Bold", 12));
                text.setFill(Color.rgb(255, 255, 153));
                root.getChildren().add(text);

                text = new Text(60, 68, "Head:");
                text.setFont(Font.font("Droid Sans Bold", 12));
                text.setFill(Color.rgb(255, 255, 153));
                root.getChildren().add(text);

                text = new Text(120, 68, Hero.getInstance().getArmourByKind(ArmourPart.Head));
                text.setFont(Font.font("Droid Sans Bold", 12));
                text.setFill(Hero.getInstance().getArmourColorByKind(ArmourPart.Head));
                root.getChildren().add(text);

                //////////////////////////////////
                text = new Text(60, 85, "Chest:");
                text.setFont(Font.font("Droid Sans Bold", 12));
                text.setFill(Color.rgb(255, 255, 153));
                root.getChildren().add(text);

                text = new Text(120, 85, Hero.getInstance().getArmourByKind(ArmourPart.Chest));
                text.setFont(Font.font("Droid Sans Bold", 12));
                text.setFill(Hero.getInstance().getArmourColorByKind(ArmourPart.Chest));
                root.getChildren().add(text);

                //////////////////////////////////
                text = new Text(60, 102, "Arms:");
                text.setFont(Font.font("Droid Sans Bold", 12));
                text.setFill(Color.rgb(255, 255, 153));
                root.getChildren().add(text);

                text = new Text(120, 102, Hero.getInstance().getArmourByKind(ArmourPart.Arms));
                text.setFont(Font.font("Droid Sans Bold", 12));
                text.setFill(Hero.getInstance().getArmourColorByKind(ArmourPart.Arms));
                root.getChildren().add(text);

                //////////////////////////////////
                text = new Text(60, 119, "Legs:");
                text.setFont(Font.font("Droid Sans Bold", 12));
                text.setFill(Color.rgb(255, 255, 153));
                root.getChildren().add(text);

                text = new Text(120, 119, Hero.getInstance().getArmourByKind(ArmourPart.Legs));
                text.setFont(Font.font("Droid Sans Bold", 12));
                text.setFill(Hero.getInstance().getArmourColorByKind(ArmourPart.Legs));
                root.getChildren().add(text);

                //////////////////////////////////
                text = new Text(60, 136, "Shield:");
                text.setFont(Font.font("Droid Sans Bold", 12));
                text.setFill(Color.rgb(255, 255, 153));
                root.getChildren().add(text);

                text = new Text(120, 136, Hero.getInstance().getArmourByKind(ArmourPart.Shield));
                text.setFont(Font.font("Droid Sans Bold", 12));
                text.setFill(Hero.getInstance().getArmourColorByKind(ArmourPart.Shield));
                root.getChildren().add(text);

                //Bag

                text = new Text(30, 187, "Bag:");
                text.setFont(Font.font("Droid Sans Bold", 12));
                text.setFill(Color.rgb(255, 255, 153));
                root.getChildren().add(text);

                if (Hero.getInstance().flasks.size() > 0) {
                    for (int i = 0; i < Hero.getInstance().flasks.size() && i < 21; i++) {
                        text = new Text(30, 204 + (i * 17), Hero.getInstance().flasks.get(i).flaskName);
                        text.setFont(Font.font("Droid Sans Bold", 12));
                        text.setFill(Color.rgb(255, 255, 153));
                        root.getChildren().add(text);

                        if (Hero.getInstance().flasks.get(i).activated) {
                            text = new Text(200, 204 + (i * 17), "" + Hero.getInstance().flasks.get(i).value);
                            text.setFont(Font.font("Droid Sans Bold", 12));
                            text.setFill(Color.rgb(51, 204, 51));
                            root.getChildren().add(text);
                        }
                    }
                }


                //////////////////////////////////

                text = new Text(30, 529, "Statistic:");
                text.setFont(Font.font("Droid Sans Bold", 12));
                text.setFill(Color.rgb(255, 255, 153));
                root.getChildren().add(text);

                text = new Text(30, 546, "Deathes:   " + Hero.getInstance().deathCount);
                text.setFont(Font.font("Droid Sans Bold", 12));
                text.setFill(Color.rgb(255, 255, 153));
                root.getChildren().add(text);

                text = new Text(150, 546, "Enemys killed:   " + Hero.getInstance().monstersKilled);
                text.setFont(Font.font("Droid Sans Bold", 12));
                text.setFill(Color.rgb(255, 255, 153));
                root.getChildren().add(text);

                if (Hero.getInstance().strongestEnemy != null) {
                    text = new Text(300, 546, "Strongest enemy:   " + Hero.getInstance().strongestEnemy.enemyName + " lvl. " + Hero.getInstance().strongestEnemy.level);
                    text.setFont(Font.font("Droid Sans Bold", 12));
                    text.setFill(Color.rgb(255, 255, 153));
                    root.getChildren().add(text);
                }

            }

            //Name
            Text text = new Text(800, 28, "Hero: " + Hero.getInstance().heroName);
            text.setFont(Font.font("IncisedBlack Normal", 12));
            text.setFill(Color.rgb(255, 255, 153));
            root.getChildren().add(text);

            //Hero lvl
            text = new Text(800, 48, "Lvl: " + Hero.getInstance().lvl);
            text.setFont(Font.font("Droid Sans Bold", 12));
            text.setFill(Color.rgb(255, 255, 153));
            root.getChildren().add(text);

            //Hero xp
            text = new Text(870, 48, "exp.: " + Hero.getInstance().xp);
            text.setFont(Font.font("Droid Sans Bold", 12));
            text.setFill(Color.rgb(255, 255, 153));
            root.getChildren().add(text);

            //Dmg
            text = new Text(1000, 48, "dmg.: " + (int) Hero.getInstance().getStandartDmg() + (Flask.isActivatedFlaskByKind(FlaskKind.Damage) ? " x4" : ""));
            text.setFont(Font.font("Droid Sans Bold", 12));
            text.setFill(Color.rgb(255, 255, 153));
            root.getChildren().add(text);

            //Armour
            text = new Text(1100, 48, "armour.: " + (int) Hero.getInstance().getStandartArmour() + (Flask.isActivatedFlaskByKind(FlaskKind.Armor) ? " x4" : ""));
            text.setFont(Font.font("Droid Sans Bold", 12));
            text.setFill(Color.rgb(255, 255, 153));
            root.getChildren().add(text);


            //Hero HP
            text = new Text(800, 68, "HP: ");
            text.setFont(Font.font("Droid Sans Bold", 12));
            text.setFill(Color.rgb(255, 255, 153));
            root.getChildren().add(text);

            String hp = "";
            for (int i = 1; i < (Hero.getInstance().getHP()) / ((100 * ((Hero.getInstance().lvl * 0.2) + 0.8))) * 20; i++) {
                hp = hp + "▓";
            }

            text = new Text(830, 68, hp);
            text.setFont(Font.font("Droid Sans Bold", 12));
            text.setFill(Color.rgb(255, 51, 51));
            root.getChildren().add(text);

            String hpS = "";
            for (int i = 0; i < (hp.length() / 2) - 3; i++) {
                hpS = hpS + " ";
            }
            text = new Text(830, 68, hpS + Hero.getInstance().getHP() + (Hero.getInstance().getMAXHP() < Hero.getInstance().getHP() ? " +++" : ""));
            text.setFont(Font.font("Droid Sans Bold", 12));
            text.setFill(Color.rgb(255, 255, 255));
            root.getChildren().add(text);


            //Dungeon lvl
            text = new Text(800, 88, "Dungeon level: " + sf.lvl);
            text.setFont(Font.font("Droid Sans Bold", 12));
            text.setFill(Color.rgb(255, 255, 153));
            root.getChildren().add(text);

            if (field[Hero.getInstance().posX][Hero.getInstance().posY].enemy != null) {
                //Enemy
                if (field[Hero.getInstance().posX][Hero.getInstance().posY].enemy.getHP() > 0) {

                    hp = "▓";
                    for (int i = 1; i < ((field[Hero.getInstance().posX][Hero.getInstance().posY].enemy.getHP() * 20) / (field[Hero.getInstance().posX][Hero.getInstance().posY].enemy.hpmax)); i++) {
                        hp = hp + "▓";
                    }

                    text = new Text(860, 108, hp);
                    text.setFont(Font.font("Droid Sans Bold", 12));
                    text.setFill(Color.rgb(255, 51, 51));
                    root.getChildren().add(text);

                    text = new Text(800, 108, "Enemy: ");
                    text.setFont(Font.font("Droid Sans Bold", 12));
                    text.setFill(Color.rgb(255, 255, 153));
                    root.getChildren().add(text);


                    text = new Text(860, 108, field[Hero.getInstance().posX][Hero.getInstance().posY].enemy.enemyName + " lvl. " + field[Hero.getInstance().posX][Hero.getInstance().posY].enemy.level);
                    text.setFont(Font.font("Droid Sans Bold", 12));
                    text.setFill(Color.rgb(255, 255, 153));
                    root.getChildren().add(text);

                    text = new Text(800, 128, "" + CharAction.getInstance().dmgToEnemy + " dmg. VS " + CharAction.getInstance().dmgToHero + " dmg.");
                    text.setFont(Font.font("Droid Sans Bold", 12));
                    text.setFill(Color.rgb(255, 51, 51));
                    root.getChildren().add(text);
                }
            }

            //Info line
            text = new Text(800, 788, "[m] map  [i] inventory");
            text.setFont(Font.font("Droid Sans Bold", 12));
            text.setFill(Color.rgb(153, 153, 92));
            root.getChildren().add(text);

            startDraw = false;
        }


    }

}


