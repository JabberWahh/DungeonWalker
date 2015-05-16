package com.jw.dw.gui;

import com.jw.dw.Ambient.*;
import com.jw.dw.chars.Aim;
import com.jw.dw.chars.Enemy;
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

    public CellMap[][] worldField;
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

    public CellMap[][] GetField() {
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

        worldField = new CellMap[WIDTH][HEIGHT];
        doorPositionX = new ArrayList<>();
        doorPositionY = new ArrayList<>();
        doorDirectionX = new ArrayList<>();
        doorDirectionY = new ArrayList<>();
        //doorTryingToBuildCount = new ArrayList<>();
        actionSpots = new ArrayList<>();

        //Новый алгоритм

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                worldField[i][j] = new CellMap(AmbientWall.icon, AmbientEnum.Wall);
            }
        }

        seted = true;

        //Комната инициализации
        CreateInitialRoom();


        for (int i = 0; i < 700; i++) {
            CreateRoom();
        }


        /*for (ActionSpot actionSpot : actionSpots) {
            worldField[actionSpot.x][actionSpot.y].icon = "○";
        }*/

        ConvertDoor();

        Aim aim = Aim.GetInstance();
        ActionSpot.RemoveUnnecessaryPoints();
        aim.SetAim(false);


    }


    /**
     * Создаём начальную комнату входа
     */
    private void CreateInitialRoom() {

        Hero hero = Hero.getInstance();
        int x;
        int y;

        x = ((int) (Math.random() * (WIDTH - 7))) + 3;
        y = ((int) (Math.random() * (HEIGHT - 7))) + 3;
        hero.SetPosition(x, y);
        worldField[x][y].icon = Hero.icon;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                worldField[x + i][y + j].icon = AmbientEmpty.icon;
                worldField[x + i][y + j].wall = false;
                worldField[x + i][y + j].kind = AmbientEnum.Empty;
            }
        }
        for (int i = -2; i < 3; i++) {
            for (int j = -2; j < 3; j++) {
                worldField[x + i][y + j].visible = true;
            }
        }

        for (int i = 0; i < WIDTH; i++) {
            worldField[i][0].visible = true;
            worldField[i][HEIGHT - 1].visible = true;
            worldField[0][i].visible = true;
            worldField[WIDTH - 1][i].visible = true;
        }


        //actionSpots.add(new ActionSpot(x, x, y, y));

        if (x > 2) {
            worldField[x - 2][y].icon = AmbientDoor.icon;
            worldField[x - 2][y].wall = false;
            CreateDoor(x - 2, y);
        }
        if (y > 2) {
            worldField[x][y - 2].icon = AmbientDoor.icon;
            worldField[x][y - 2].wall = false;
            CreateDoor(x, y - 2);
        }
        if (x < WIDTH - 2) {
            worldField[x + 2][y].icon = AmbientDoor.icon;
            worldField[x + 2][y].wall = false;
            CreateDoor(x + 2, y);
        }
        if (y < HEIGHT - 2) {
            worldField[x][y + 2].icon = AmbientDoor.icon;
            worldField[x][y + 2].wall = false;
            CreateDoor(x, y + 2);
        }

        //actionSpots.clear();
    }

    private void CreateDoor(int x, int y) {
        doorPositionX.add(x);
        doorPositionY.add(y);
        worldField[x][y].kind = AmbientEnum.Door;
        //as.AddActionPoint(x, y);
        if (!worldField[x - 1][y].wall) {
            doorDirectionX.add(1);
            doorDirectionY.add(0);

        }
        if (!worldField[x + 1][y].wall) {
            doorDirectionX.add(-1);
            doorDirectionY.add(0);
        }
        if (!worldField[x][y - 1].wall) {
            doorDirectionY.add(1);
            doorDirectionX.add(0);
        }
        if (!worldField[x][y + 1].wall) {
            doorDirectionY.add(-1);
            doorDirectionX.add(0);
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
                        if (!worldField[i][j].wall && !Objects.equals(worldField[i][j].icon, AmbientDoor.icon)) {
                            success = false;
                        } else {
                            if (Objects.equals(worldField[i][j].icon, AmbientDoor.icon)) {
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
                        worldField[i][j].icon = AmbientEmpty.icon;
                        worldField[i][j].wall = false;
                    }
                }


                //Добавление дверей

                //верхний ряд
                int rnd;
                rnd = randInt.GetRandInt(0, sizeX - 1);
                if (xUp + rnd + 1 > 0 && yUp - 2 > 0) {
                    if (worldField[xUp + rnd][yUp - 2].wall
                            && worldField[xUp + rnd - 1][yUp - 1].wall
                            && worldField[xUp + rnd + 1][yUp - 1].wall) {
                        worldField[xUp + rnd][yUp - 1].icon = AmbientDoor.icon;
                        worldField[xUp + rnd][yUp - 1].wall = false;
                        CreateDoor(xUp + rnd, yUp - 1);
                    }
                }

                //нижний ряд
                rnd = randInt.GetRandInt(0, sizeX - 1);
                if (xUp + rnd + 1 > 0 && yDown + 2 < HEIGHT) {
                    if (worldField[xUp + rnd][yDown + 2].wall
                            && worldField[xUp + rnd - 1][yDown + 1].wall
                            && worldField[xUp + rnd - 1][yDown + 1].wall) {
                        worldField[xUp + rnd][yDown + 1].icon = AmbientDoor.icon;
                        worldField[xUp + rnd][yDown + 1].wall = false;
                        CreateDoor(xUp + rnd, yDown + 1);
                    }
                }

                //правый ряд
                rnd = randInt.GetRandInt(0, sizeY - 1);
                if (xDown + 2 < WIDTH && yUp + rnd + 1 < HEIGHT) {
                    if (worldField[xDown + 2][yUp + rnd].wall
                            && worldField[xDown + 1][yUp + rnd - 1].wall
                            && worldField[xDown + 1][yUp + rnd + 1].wall) {
                        worldField[xDown + 1][yUp + rnd].icon = AmbientDoor.icon;
                        worldField[xDown + 1][yUp + rnd].wall = false;
                        CreateDoor(xDown + 1, yUp + rnd);
                    }
                }

                //левый ряд
                rnd = randInt.GetRandInt(0, sizeY - 1);
                if (xUp - 2 > 0 && yUp + rnd + 1 < HEIGHT) {
                    if (worldField[xUp - 2][yUp + rnd].wall
                            && worldField[xUp - 1][yUp + rnd - 1].wall
                            && worldField[xUp - 1][yUp + rnd + 1].wall) {
                        worldField[xUp - 1][yUp + rnd].icon = AmbientDoor.icon;
                        worldField[xUp - 1][yUp + rnd].wall = false;
                        CreateDoor(xUp - 1, yUp + rnd);
                    }
                }

                //Добавим в текущий ActionSpots все двери вокруг него
                ActionSpot curAs = new ActionSpot(xUp, xDown, yUp, yDown);
                actionSpots.add(curAs);
                worldField[curAs.x][curAs.y].kind = AmbientEnum.ActionSpot;
                worldField[curAs.x][curAs.y].enemy = new Enemy(lvl);
                worldField[curAs.x][curAs.y].icon = worldField[curAs.x][curAs.y].enemy.icon;
                curAs = actionSpots.get(actionSpots.size() - 1);
                for (int i = xUp - 1; i < xDown + 2; i++) {
                    for (int j = yUp - 1; j < yDown + 2; j++) {
                        if (Objects.equals(worldField[i][j].icon, AmbientDoor.icon)) {
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
                if (Objects.equals(worldField[i][j].icon, AmbientDoor.icon)) {
                    boolean itsDoor = false;
                    if ((!worldField[i - 1][j].wall && !worldField[i + 1][j].wall) || (!worldField[i][j - 1].wall && !worldField[i][j + 1].wall)) {
                        itsDoor = true;
                    }
                    if (!itsDoor) {
                        if (randInt.GetRandInt(1, 10) == 10) {
                            worldField[i][j].icon = AmbientChest.icon;
                            worldField[i][j].kind = AmbientEnum.Chest;
                        } else {
                            worldField[i][j].icon = AmbientWall.icon;
                            worldField[i][j].wall = true;
                            worldField[i][j].kind = AmbientEnum.Wall;
                        }
                    }
                }

            }
        }

        //!!!!!!!
        /*worldField[Hero.getInstance().posX+2][Hero.getInstance().posY-2].icon = AmbientChest.icon;
        worldField[Hero.getInstance().posX+2][Hero.getInstance().posY-2].kind = AmbientEnum.Chest;
        worldField[Hero.getInstance().posX+2][Hero.getInstance().posY-2].wall = false;*/
    }
}


