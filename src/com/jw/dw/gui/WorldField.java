package com.jw.dw.gui;

import com.jw.dw.Ambient.*;
import com.jw.dw.chars.Aim;
import com.jw.dw.chars.Hero;
import com.jw.dw.randInt;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by vahma on 30.04.15.
 * WorldField
 */
public class WorldField {


    private static WorldField instance;

    public String[][] worldField;
    public final int WIDTH = 50;
    public final int HEIGHT = 50;
    private boolean seted;
    public int lvl = 1;
    //private int emptimess = 0;
    private ArrayList<Integer> doorPositionX;
    private ArrayList<Integer> doorPositionY;
    private ArrayList<Integer> doorDirectionX;
    private ArrayList<Integer> doorDirectionY;
    public ArrayList<ActionSpot> actionSpots;

    private WorldField() {
    }

    public String[][] GetField() {
        if (!seted) {
            SetField();
        }
        return worldField;
    }

    public static WorldField GetInstance() {
        if (instance == null) {
            instance = new WorldField();
            instance.SetField();
            instance.seted = true;
        }
        return instance;
    }

    public void SetField() {

        worldField = new String[WIDTH][HEIGHT];
        doorPositionX = new ArrayList<>();
        doorPositionY = new ArrayList<>();
        doorDirectionX = new ArrayList<>();
        doorDirectionY = new ArrayList<>();
        //doorTryingToBuildCount = new ArrayList<>();
        actionSpots = new ArrayList<>();

        //Новый алгоритм

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                worldField[i][j] = AmbientWall.icon;
            }
        }

        seted = true;

        //Комната инициализации
        int enterPointX = 0;
        int enterPointY = 0;
        CreateInitialRoom(enterPointX, enterPointY);


        for (int i = 0; i < 700; i++) {
            CreateRoom();
        }


        for (ActionSpot actionSpot : actionSpots) {
            worldField[actionSpot.x][actionSpot.y] = "A";
        }

        ConvertDoor();

        Aim aim = Aim.GetInstance();
        aim.SetAimToRandomDoor();

    }


    /**
     * Создаём начальную комнату входа
     *
     * @param x min value
     * @param y max value
     */
    private void CreateInitialRoom(int x, int y) {

        if (x == 0) {
            x = ((int) (Math.random() * (WIDTH - 7))) + 3;
            y = ((int) (Math.random() * (HEIGHT - 7))) + 3;
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    worldField[x + i][y + j] = AmbientEmpty.icon;
                }
            }

        } else {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    worldField[x + i][y + j] = AmbientEmpty.icon;
                }
            }
        }

        Hero hero = Hero.GetInstance();
        hero.SetPosition(x, y);
        worldField[x][y] = Hero.icon;

        actionSpots.add(new ActionSpot(x, x, y, y));

        if (x > 2) {
            worldField[x - 2][y] = AmbientDoor.icon;
            CreateDoor(x - 2, y);//, actionSpots.get(actionSpots.size() - 1));
        }
        if (y > 2) {
            worldField[x][y - 2] = AmbientDoor.icon;
            CreateDoor(x, y - 2);//, actionSpots.get(actionSpots.size() - 1));
        }
        if (x < WIDTH - 2) {
            worldField[x + 2][y] = AmbientDoor.icon;
            CreateDoor(x + 2, y);//, actionSpots.get(actionSpots.size() - 1));
        }
        if (y < HEIGHT - 2) {
            worldField[x][y + 2] = AmbientDoor.icon;
            CreateDoor(x, y + 2);//, actionSpots.get(actionSpots.size() - 1));
        }

        actionSpots.clear();
        //actionSpots.remove(actionSpots.size() - 1); //deleting unnesesary spot


    }

    private void CreateDoor(int x, int y) {
        doorPositionX.add(x);
        doorPositionY.add(y);
        //as.AddActionPoint(x, y);
        if (Objects.equals(worldField[x - 1][y], AmbientEmpty.icon)) {
            doorDirectionX.add(1);
            doorDirectionY.add(0);

        }
        if (Objects.equals(worldField[x + 1][y], AmbientEmpty.icon)) {
            doorDirectionX.add(-1);
            doorDirectionY.add(0);
            // as.AddActionPoint(x, y);
        }
        if (Objects.equals(worldField[x][y - 1], AmbientEmpty.icon)) {
            doorDirectionY.add(1);
            doorDirectionX.add(0);
            // as.AddActionPoint(x, y);
        }
        if (Objects.equals(worldField[x][y + 1], AmbientEmpty.icon)) {
            doorDirectionY.add(-1);
            doorDirectionX.add(0);
            // as.AddActionPoint(x, y);
        }
    }


    private void CreateRoom() {

        int shape = randInt.GetRandInt(1, 2);
        int sizeX = randInt.GetRandInt(3, 6);
        int sizeY = randInt.GetRandInt(3, 6);

        if (sizeX - sizeY > 2) {
            sizeX = sizeX - 1;
            sizeY = sizeY + 1;
        } else if (sizeY - sizeX > 2) {
            sizeX = sizeX + 1;
            sizeY = sizeY - 1;
        }

        int xUp = 0;
        int yUp = 0;
        int xDown = 0;
        int yDown = 0;
        int door;

        //Random door

        door = randInt.GetRandInt(0, doorPositionX.size() - 1);


        if (doorPositionX.size() > 1) {


            if (shape > 1) {//Если больше 1, значит коридор, иначе - комната

                if (doorDirectionX.get(door) > 0) {
                    xUp = doorPositionX.get(door) + 1;
                    xDown = xUp + sizeX - 1;
                    yUp = doorPositionY.get(door) - randInt.GetRandInt(0, sizeY - 1);
                    yDown = yUp + sizeY - 1;
                }
                if (doorDirectionX.get(door) < 0) {
                    xDown = doorPositionX.get(door) - 1;
                    xUp = xDown - sizeX + 1;
                    yUp = doorPositionY.get(door) - randInt.GetRandInt(0, sizeY - 1);
                    yDown = yUp + sizeY - 1;
                }
                if (doorDirectionY.get(door) > 0) {//
                    xUp = doorPositionX.get(door) - randInt.GetRandInt(0, sizeX - 1);
                    xDown = xUp + sizeX - 1;
                    yUp = doorPositionY.get(door) + 1;
                    yDown = yUp + sizeY - 1;
                }
                if (doorDirectionY.get(door) < 0) {
                    xUp = doorPositionX.get(door) - randInt.GetRandInt(0, sizeX - 1);
                    xDown = xUp + sizeX - 1;
                    yDown = doorPositionY.get(door) - 1;
                    yUp = yDown - sizeY + 1;
                }

            } else {


                if (doorDirectionX.get(door) != 0) {
                    sizeY = 1;
                } else {
                    sizeX = 1;
                }

                if (doorDirectionX.get(door) > 0) {
                    xUp = doorPositionX.get(door) + 1;
                    xDown = xUp + sizeX - 1;
                    yUp = doorPositionY.get(door);
                    yDown = yUp + sizeY - 1;
                }
                if (doorDirectionX.get(door) < 0) {
                    xDown = doorPositionX.get(door);
                    xUp = xDown - sizeX;
                    yUp = doorPositionY.get(door);
                    yDown = yUp + sizeY - 1;
                }
                if (doorDirectionY.get(door) > 0) {//
                    xUp = doorPositionX.get(door);
                    xDown = xUp + sizeX - 1;
                    yUp = doorPositionY.get(door) + 1;
                    yDown = yUp + sizeY - 1;
                }
                if (doorDirectionY.get(door) < 0) {
                    xUp = doorPositionX.get(door);
                    xDown = xUp + sizeX - 1;
                    yDown = doorPositionY.get(door);
                    yUp = yDown - sizeY;
                }
            }

            boolean success = true; //Проверка возможности вставки

            int numOfDoors = 0;
            if (xUp > 0 && xDown < WIDTH - 1 && yUp > 0 && yDown < HEIGHT - 1) {

                for (int i = xUp - 1; i < xDown + 2; i++) {
                    for (int j = yUp - 1; j < yDown + 2; j++) {
                        if (!Objects.equals(worldField[i][j], AmbientWall.icon) && !Objects.equals(worldField[i][j], AmbientDoor.icon)) {
                            success = false;
                        } else {
                            if (Objects.equals(worldField[i][j], AmbientDoor.icon)) {
                                numOfDoors = numOfDoors + 1;
                            }
                        }

                    }
                }
            } else {
                success = false;
            }

            if (numOfDoors > 1) {
                success = false;
            }

            if (success) {//рисуем
                for (int i = xUp; i < xDown + 1; i++) {
                    for (int j = yUp; j < yDown + 1; j++) {
                        worldField[i][j] = AmbientEmpty.icon;
                    }
                }




                //Добавление дверей

                //верхний ряд
                int rnd;
                rnd = randInt.GetRandInt(0, sizeX - 1);
                if (xUp + rnd + 1 > 0 && yUp - 2 > 0) {
                    if (Objects.equals(worldField[xUp + rnd][yUp - 2], AmbientWall.icon)
                            && Objects.equals(worldField[xUp + rnd - 1][yUp - 1], AmbientWall.icon)
                            && Objects.equals(worldField[xUp + rnd + 1][yUp - 1], AmbientWall.icon)) {
                        worldField[xUp + rnd][yUp - 1] = AmbientDoor.icon;
                        CreateDoor(xUp + rnd, yUp - 1);//, actionSpots.get(actionSpots.size() - 1));
                    }
                }

                //нижний ряд
                rnd = randInt.GetRandInt(0, sizeX - 1);
                if (xUp + rnd + 1 > 0 && yDown + 2 < HEIGHT) {
                    if (Objects.equals(worldField[xUp + rnd][yDown + 2], AmbientWall.icon)
                            && Objects.equals(worldField[xUp + rnd - 1][yDown + 1], AmbientWall.icon)
                            && Objects.equals(worldField[xUp + rnd - 1][yDown + 1], AmbientWall.icon)) {
                        worldField[xUp + rnd][yDown + 1] = AmbientDoor.icon;
                        CreateDoor(xUp + rnd, yDown + 1);//, actionSpots.get(actionSpots.size() - 1));
                    }
                }

                //правый ряд
                rnd = randInt.GetRandInt(0, sizeY - 1);
                if (xDown + 2 < WIDTH && yUp + rnd + 1 < HEIGHT) {
                    if (Objects.equals(worldField[xDown + 2][yUp + rnd], AmbientWall.icon)
                            && Objects.equals(worldField[xDown + 1][yUp + rnd - 1], AmbientWall.icon)
                            && Objects.equals(worldField[xDown + 1][yUp + rnd + 1], AmbientWall.icon)) {
                        worldField[xDown + 1][yUp + rnd] = AmbientDoor.icon;
                        CreateDoor(xDown + 1, yUp + rnd);//, actionSpots.get(actionSpots.size() - 1));
                    }
                }

                //левый ряд
                rnd = randInt.GetRandInt(0, sizeY - 1);
                if (xUp - 2 > 0 && yUp + rnd + 1 < HEIGHT) {
                    if (Objects.equals(worldField[xUp - 2][yUp + rnd], AmbientWall.icon)
                            && Objects.equals(worldField[xUp - 1][yUp + rnd - 1], AmbientWall.icon)
                            && Objects.equals(worldField[xUp - 1][yUp + rnd + 1], AmbientWall.icon)) {
                        worldField[xUp - 1][yUp + rnd] = AmbientDoor.icon;
                        CreateDoor(xUp - 1, yUp + rnd);//, actionSpots.get(actionSpots.size() - 1));
                    }
                }

                //Добавим в текущий ActionSpots все двери вокруг него
                ActionSpot curAs = new ActionSpot(xUp, xDown, yUp, yDown);
                actionSpots.add(curAs);
                curAs = actionSpots.get(actionSpots.size() - 1);
                for (int i = xUp - 1; i < xDown + 2; i++) {
                    for (int j = yUp - 1; j < yDown + 2; j++) {
                        if (worldField[i][j] == AmbientDoor.icon) {
                            curAs.AddActionPoint(i, j);
                        }
                    }
                }


                doorDirectionY.remove(door);
                doorDirectionX.remove(door);
                doorPositionX.remove(door);
                doorPositionY.remove(door);

            }

        }
    }

    private void ConvertDoor() {
        for (int i = 1; i < HEIGHT - 1; i++) {
            for (int j = 1; j < WIDTH - 1; j++) {
                if (worldField[i][j] == AmbientDoor.icon) {
                    boolean itsDoor = false;
                    if (worldField[i - 1][j] == AmbientEmpty.icon || worldField[i - 1][j] == "A") {
                        if (worldField[i + 1][j] == AmbientEmpty.icon || worldField[i + 1][j] == "A") {
                            itsDoor = true;

                        }
                    }
                    if (worldField[i][j - 1] == AmbientEmpty.icon || worldField[i][j - 1] == "A") {
                        if (worldField[i][j + 1] == AmbientEmpty.icon || worldField[i][j + 1] == "A") {
                            itsDoor = true;

                        }
                    }

                    if (!itsDoor) {
                        if (randInt.GetRandInt(1, 20) == 20) {
                            worldField[i][j] = AmbientChest.icon;
                        } else {
                            worldField[i][j] = AmbientWall.icon;
                        }
                    }
                }

            }
        }
    }
}


