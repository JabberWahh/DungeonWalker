import com.jw.dw.Ambient.*;
import com.jw.dw.Items.ArmourPart;
import com.jw.dw.Items.Flask;
import com.jw.dw.Items.FlaskKind;
import com.jw.dw.chars.CharAction;
import com.jw.dw.chars.Hero;
import com.jw.dw.gui.ShowingStage;
import com.jw.dw.gui.WorldField;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Objects;

/**
 * Created by vahma on 17.05.15.
 */
public class Rasterizer2 {


    public boolean startDraw;
    public ShowingStage showingStage = ShowingStage.MAP;

    public void Draw(Group root) {

        int x = 0;
        int y = 0;
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

            Image imgEmpty = new Image(getClass().getClassLoader().getResourceAsStream("floor.png"));
            Image imgWall = new Image(getClass().getClassLoader().getResourceAsStream("wall.png"));
            Image imgDoor = new Image(getClass().getClassLoader().getResourceAsStream("door.png"));
            Image imgDoorCracked = new Image(getClass().getClassLoader().getResourceAsStream("open-door.png"));
            Image imgHero = new Image(getClass().getClassLoader().getResourceAsStream("hero.png"));
            Image imgHeroFight = new Image(getClass().getClassLoader().getResourceAsStream("hero-fight.png"));
            Image imgChest = new Image(getClass().getClassLoader().getResourceAsStream("chest.png"));
            Image imgExit = new Image(getClass().getClassLoader().getResourceAsStream("dngn_enter.png"));

            Image imgEnemy = new Image(getClass().getClassLoader().getResourceAsStream("enemy.png"));
            Image imgEnemySpider = new Image(getClass().getClassLoader().getResourceAsStream("spider.png"));
            Image imgEnemyGoblin = new Image(getClass().getClassLoader().getResourceAsStream("goblin.png"));
            Image imgEnemyOrc = new Image(getClass().getClassLoader().getResourceAsStream("orc.png"));
            Image imgEnemyDemon = new Image(getClass().getClassLoader().getResourceAsStream("demon.png"));
            Image imgEnemyKobold = new Image(getClass().getClassLoader().getResourceAsStream("kobold.png"));
            Image imgEnemyBeholder = new Image(getClass().getClassLoader().getResourceAsStream("beholder.png"));
            Image imgEnemyWywern = new Image(getClass().getClassLoader().getResourceAsStream("wyvern.png"));
            Image imgEnemyElemental = new Image(getClass().getClassLoader().getResourceAsStream("elemental.png"));
            Image imgEnemyManticora = new Image(getClass().getClassLoader().getResourceAsStream("manticore.png"));
            Image imgEnemyDragon = new Image(getClass().getClassLoader().getResourceAsStream("dragon.png"));

            Image imgBlood = new Image(getClass().getClassLoader().getResourceAsStream("blood.png"));

            if (showingStage == ShowingStage.MAP) {
                for (int i = 0; i < sf.WIDTH; i++) {
                    for (int j = 0; j < sf.HEIGHT; j++) {
                        if (field[i][j].visible) {
                            ImageView img = new ImageView();
                            img.setScaleX(0.5);
                            img.setScaleY(0.5);

                            String ch = AmbientEmpty.icon;
                            if (field[i][j] != null) {
                                ch = field[i][j].icon;
                            }


                            Text text = new Text(x + (i * 16), y + (j * 16), ch);
                            //text.setFont(Font.font("Droid Sans Bold", 16));

                            //Defaul color
                            text.setFill(Color.rgb(0, 153, 51, 1));
                            if (Objects.equals(ch, AmbientWall.icon)) {
                                img.setImage(imgWall);
                                img.setX(x + (i * 16));
                                img.setY(y + (j * 16));
                            }

                            if (field[i][j].icon == AmbientEmpty.icon) {
                                img.setImage(imgEmpty);
                                img.setX(x + (i * 16));
                                img.setY(y + (j * 16));
                                img.setOpacity(0.1);
                            }

                            if (field[i][j].enemy != null) {
                                img.setX(x + (i * 16));
                                img.setY(y + (j * 16));
                                if (field[i][j].enemy.getHP() > 0) {
                                    ImageView tmpView = new ImageView();
                                    if (field[i][j].enemy.icon == "s") {
                                        img.setImage(imgEnemySpider);
                                        tmpView.setImage(imgEnemySpider);
                                    } else if (field[i][j].enemy.icon == "g") {
                                        img.setImage(imgEnemyGoblin);
                                        tmpView.setImage(imgEnemyGoblin);
                                    } else if (field[i][j].enemy.icon == "z") {
                                        img.setImage(imgEnemyOrc);
                                        tmpView.setImage(imgEnemyOrc);
                                    } else if (field[i][j].enemy.icon == "d") {
                                        img.setImage(imgEnemyDemon);
                                        tmpView.setImage(imgEnemyDemon);
                                    } else if (field[i][j].enemy.icon == "k") {
                                        img.setImage(imgEnemyKobold);
                                        tmpView.setImage(imgEnemyKobold);
                                    } else if (field[i][j].enemy.icon == "b") {
                                        img.setImage(imgEnemyBeholder);
                                        tmpView.setImage(imgEnemyBeholder);
                                    } else if (field[i][j].enemy.icon == "W") {
                                        img.setImage(imgEnemyWywern);
                                        tmpView.setImage(imgEnemyWywern);
                                    } else if (field[i][j].enemy.icon == "E") {
                                        img.setImage(imgEnemyElemental);
                                        tmpView.setImage(imgEnemyElemental);
                                    } else if (field[i][j].enemy.icon == "M") {
                                        img.setImage(imgEnemyManticora);
                                        tmpView.setImage(imgEnemyManticora);
                                    } else if (field[i][j].enemy.icon == "D") {
                                        img.setImage(imgEnemyDragon);
                                        tmpView.setImage(imgEnemyDragon);
                                    } else {
                                        img.setImage(imgEnemy);
                                        tmpView.setImage(imgEnemy);
                                    }

                                    //Enemy icon
                                    tmpView.setX(850);
                                    tmpView.setY(132);
                                    root.getChildren().add(tmpView);
                                    //

                                    fighting = true;
                                } else {
                                    img.setImage(imgBlood);
                                }
                            }
                            //Hero
                            if (Objects.equals(ch, Hero.icon)) {
                                if (fighting) {
                                    img.setImage(imgHeroFight);
                                    img.setX(x + (i * 16));
                                    img.setY(y + (j * 16));
                                } else {
                                    img.setImage(imgHero);
                                    img.setX(x + (i * 16));
                                    img.setY(y + (j * 16));
                                }
                                if (Hero.getInstance().getHP() <= 0) {
                                    text.setFill(Color.rgb(5, 5, 5));
                                }
                            }
                            if (Objects.equals(ch, AmbientDoor.icon)) {
                                if (field[i][j].activated) {
                                    img.setImage(imgDoorCracked);
                                    img.setX(x + (i * 16));
                                    img.setY(y + (j * 16));
                                } else {
                                    img.setImage(imgDoor);
                                    img.setX(x + (i * 16));
                                    img.setY(y + (j * 16));
                                }

                            }

                            if (field[i][j].exit) {
                                img.setImage(imgExit);
                                img.setX(x + (i * 16));
                                img.setY(y + (j * 16));
                            }
                            if (Objects.equals(ch, AmbientChest.icon)) {
                                img.setScaleX(0.5);
                                img.setScaleY(0.5);
                                img.setImage(imgChest);
                                img.setX(x + (i * 16));
                                img.setY(y + (j * 16));
                            }

                            //text.setRotate(0);
                            root.getChildren().add(img);
                            //root.getChildren().add(text);

                        }
                    }
                }
            }

            if (showingStage == ShowingStage.INVENTORY) { //Inventory

                Image imgInventory = new Image(getClass().getClassLoader().getResourceAsStream("inventory.png"));
                ImageView img = new ImageView();
                img.setImage(imgInventory);
                root.getChildren().add(img);
                /*for (int i = 1; i < sf.WIDTH - 1; i++) {
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
                }*/
                /*Text text = new Text(15, 17, "╔");
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
                */

                //Info

                Text text = new Text(30, 34, "Weapon:");
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
            Text text = new Text(850, 28, "Hero: " + Hero.getInstance().heroName);
            text.setFont(Font.font("IncisedBlack Normal", 12));
            text.setFill(Color.rgb(255, 255, 153));
            root.getChildren().add(text);

            //Hero lvl
            text = new Text(850, 48, "Lvl: " + Hero.getInstance().lvl);
            text.setFont(Font.font("Droid Sans Bold", 12));
            text.setFill(Color.rgb(255, 255, 153));
            root.getChildren().add(text);

            //Hero xp
            text = new Text(910, 48, "exp.: " + Hero.getInstance().xp);
            text.setFont(Font.font("Droid Sans Bold", 12));
            text.setFill(Color.rgb(255, 255, 153));
            root.getChildren().add(text);

            //Dmg
            text = new Text(1040, 48, "dmg.: " + (int) Hero.getInstance().getStandartDmg() + (Flask.isActivatedFlaskByKind(FlaskKind.Damage) ? " x4" : ""));
            text.setFont(Font.font("Droid Sans Bold", 12));
            text.setFill(Color.rgb(255, 255, 153));
            root.getChildren().add(text);

            //Armour
            text = new Text(1140, 48, "armour.: " + (int) Hero.getInstance().getStandartArmour() + (Flask.isActivatedFlaskByKind(FlaskKind.Armor) ? " x4" : ""));
            text.setFont(Font.font("Droid Sans Bold", 12));
            text.setFill(Color.rgb(255, 255, 153));
            root.getChildren().add(text);


            //Hero HP
            text = new Text(850, 68, "HP: ");
            text.setFont(Font.font("Droid Sans Bold", 12));
            text.setFill(Color.rgb(255, 255, 153));
            root.getChildren().add(text);

            String hp = "";
            for (int i = 1; i < (Hero.getInstance().getHP()) / ((100 * ((Hero.getInstance().lvl * 0.2) + 0.8))) * 20; i++) {
                hp = hp + "▓";
            }

            text = new Text(880, 68, hp);
            text.setFont(Font.font("Droid Sans Bold", 12));
            text.setFill(Color.rgb(255, 51, 51));
            root.getChildren().add(text);

            String hpS = "";
            for (int i = 0; i < (hp.length() / 2) - 3; i++) {
                hpS = hpS + " ";
            }
            text = new Text(880, 68, hpS + Hero.getInstance().getHP() + (Hero.getInstance().getMAXHP() < Hero.getInstance().getHP() ? " +++" : ""));
            text.setFont(Font.font("Droid Sans Bold", 12));
            text.setFill(Color.rgb(255, 255, 255));
            root.getChildren().add(text);


            //Dungeon lvl
            text = new Text(850, 88, "Dungeon level: " + sf.lvl);
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

                    text = new Text(900, 108, hp);
                    text.setFont(Font.font("Droid Sans Bold", 12));
                    text.setFill(Color.rgb(255, 51, 51));
                    root.getChildren().add(text);

                    text = new Text(850, 108, "Enemy: ");
                    text.setFont(Font.font("Droid Sans Bold", 12));
                    text.setFill(Color.rgb(255, 255, 153));
                    root.getChildren().add(text);


                    text = new Text(900, 108, field[Hero.getInstance().posX][Hero.getInstance().posY].enemy.enemyName + " lvl. " + field[Hero.getInstance().posX][Hero.getInstance().posY].enemy.level);
                    text.setFont(Font.font("Droid Sans Bold", 12));
                    text.setFill(Color.rgb(255, 255, 153));
                    root.getChildren().add(text);

                    text = new Text(850, 128, "" + CharAction.getInstance().dmgToEnemy + " dmg. VS " + CharAction.getInstance().dmgToHero + " dmg.");
                    text.setFont(Font.font("Droid Sans Bold", 12));
                    text.setFill(Color.rgb(255, 51, 51));
                    root.getChildren().add(text);
                }
            }

            //Info line
            text = new Text(850, 788, "[m] map  [i] inventory");
            text.setFont(Font.font("Droid Sans Bold", 12));
            text.setFill(Color.rgb(153, 153, 92));
            root.getChildren().add(text);

            startDraw = false;

            //Crossing lines
            //System.out.println(crossingLines(1, 1, 100, 100, 2, 3, 4, 5));

            if (showingStage == ShowingStage.MAP) {
                Image imgDarkness = new Image(getClass().getClassLoader().getResourceAsStream("darkness.png"));

                int heroI = 0;
                int heroJ = 0;
                for (int i = 0; i < sf.WIDTH; i++) {
                    for (int j = 0; j < sf.HEIGHT; j++) {
                        if (field[i][j].icon == Hero.icon) {
                            heroI = i;
                            heroJ = j;
                            break;
                        }
                    }
                }

                for (int i = 0; i < sf.WIDTH; i++) {
                    for (int j = 0; j < sf.HEIGHT; j++) {
                        ImageView img = new ImageView();
                        img.setImage(imgDarkness);
                        img.setX(x + (i * 16));
                        img.setY(y + (j * 16));
                        double alpha = 0.9;
                        //double alpha = 0.0;
                        if (field[i][j].visible) {
                            if (Math.abs(i - heroI) <= 1 && Math.abs(j - heroJ) <= 1) {
                                alpha = 0;
                            } else {
                                if (Math.abs(i - heroI) <= 10 && Math.abs(j - heroJ) <= 10) {
                                    alpha = lightSquare(heroI, heroJ, i, j, field);
                                }
                            }
                        }
                        img.setOpacity(alpha);
                        root.getChildren().add(img);
                    }
                }
            }
        }
    }

    private double lightSquare(int heroI, int heroJ, int sqI, int sqJ, CellMap[][] field) {

        int upI = heroI;
        int upJ = heroJ;
        int downI = sqI;
        int downJ = sqJ;
        if (upI > downI) {
            upI = sqI;
            downI = heroI;
        }
        if (upJ > downJ) {
            upJ = sqJ;
            downJ = heroJ;
        }

        double alpha = 0;

        double alphaMult = 0.0125;

        for (int i = upI; i < downI + 1 && alpha < 0.9; i++) {
            for (int j = upJ; j < downJ + 1 && alpha < 0.9; j++) {
                if ((i == heroI && j == heroJ) || (i == sqI && j == sqJ)) {
                    continue;
                } else {
//                    if (field[i][j].icon == AmbientWall.icon) {
//                        if (crossingLines(heroI * 16, heroJ * 16, sqI * 16, sqJ * 16, i * 16 - 8, j * 16 - 8, i * 16 + 8, j * 16 + 8)) {
//                            alpha = alpha + 0.425;
//                        }
//
//                        if (crossingLines(heroI * 16, heroJ * 16, sqI * 16, sqJ * 16, i * 16 - 8, j * 16 + 8, i * 16 + 8, j * 16 - 8)) {
//                            alpha = alpha + 0.425;
//                        }
//                    }
                    if (field[i][j].icon == AmbientWall.icon) {
                        //1 corner

                        if (crossingLines(heroI * 16, heroJ * 16, sqI * 16 - 8, sqJ * 16 - 8, i * 16 - 8, j * 16 - 8, i * 16 + 8, j * 16 + 8)) {
                            alpha = alpha + alphaMult;
                        }

                        if (crossingLines(heroI * 16, heroJ * 16, sqI * 16 - 8, sqJ * 16 - 8, i * 16 - 8, j * 16 + 8, i * 16 + 8, j * 16 - 8)) {
                            alpha = alpha + alphaMult;
                        }
                        //2 corner
                        if (crossingLines(heroI * 16, heroJ * 16, sqI * 16 - 8, sqJ * 16 + 8, i * 16 - 8, j * 16 - 8, i * 16 + 8, j * 16 + 8)) {
                            alpha = alpha + alphaMult;
                        }

                        if (crossingLines(heroI * 16, heroJ * 16, sqI * 16 - 8, sqJ * 16 + 8, i * 16 - 8, j * 16 + 8, i * 16 + 8, j * 16 - 8)) {
                            alpha = alpha + alphaMult;
                        }
                        //3 corner
                        if (crossingLines(heroI * 16, heroJ * 16, sqI * 16 + 8, sqJ * 16 + 8, i * 16 - 8, j * 16 - 8, i * 16 + 8, j * 16 + 8)) {
                            alpha = alpha + alphaMult;
                        }

                        if (crossingLines(heroI * 16, heroJ * 16, sqI * 16 + 8, sqJ * 16 + 8, i * 16 - 8, j * 16 + 8, i * 16 + 8, j * 16 - 8)) {
                            alpha = alpha + alphaMult;
                        }
                        //4 corner
                        if (crossingLines(heroI * 16, heroJ * 16, sqI * 16 + 8, sqJ * 16 - 8, i * 16 - 8, j * 16 - 8, i * 16 + 8, j * 16 + 8)) {
                            alpha = alpha + alphaMult;
                        }

                        if (crossingLines(heroI * 16, heroJ * 16, sqI * 16 + 8, sqJ * 16 - 8, i * 16 - 8, j * 16 + 8, i * 16 + 8, j * 16 - 8)) {
                            alpha = alpha + alphaMult;
                        }

                    }
                }
            }
        }

        double d = ((Math.sqrt(Math.pow(Math.abs(heroI - sqI), 2) + Math.pow(Math.abs(heroJ - sqJ), 2))) % 16) * 0.04;
        alpha = alpha + d;
        if (alpha > 0.9) {
            alpha = 0.9;
        }

        return alpha;
    }

    private boolean crossingLines(double rayX1, double rayY1, double rayX2, double rayY2, double aimX1, double aimY1, double aimX2, double aimY2) {

        boolean findCross = false;
        double v1 = (aimX2 - aimX1) * (rayY1 - aimY1) - (aimY2 - aimY1) * (rayX1 - aimX1);
        double v2 = (aimX2 - aimX1) * (rayY2 - aimY1) - (aimY2 - aimY1) * (rayX2 - aimX1);
        double v3 = (rayX2 - rayX1) * (aimY1 - rayY1) - (rayY2 - rayY1) * (aimX1 - rayX1);
        double v4 = (rayX2 - rayX1) * (aimY2 - rayY1) - (rayY2 - rayY1) * (aimX2 - rayX1);
        if ((v1 * v2 < 0) && (v3 * v4 < 0)) {
            findCross = true;
        }
        return findCross;
    }

}
