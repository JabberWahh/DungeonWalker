package com.jw.dw.AI;

/**
 * Created by vahma on 01.05.15.
 * AStar
 */

import com.jw.dw.Ambient.AmbientWall;
import com.jw.dw.chars.Aim;
import com.jw.dw.chars.Hero;
import com.jw.dw.gui.WorldField;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class AStar {

    private Cell start;
    private Cell finish;
    public ArrayList<Integer> arX;
    public ArrayList<Integer> arY;
    public boolean routeFound = false;

    private static AStar instance;

    private AStar() {
    }


    public static AStar GetInstance() {
        if (instance == null) {
            instance = new AStar();
        }
        return instance;
    }


    public void Start() {
        WorldField wFObj = WorldField.GetInstance();
        arX = new ArrayList<>();
        arY = new ArrayList<>();
        // Создадим все нужные списки
        Table<Cell> cellList = new Table<>(wFObj.WIDTH, wFObj.HEIGHT);
        Table blockList = new Table(wFObj.WIDTH, wFObj.HEIGHT);
        LinkedList<Cell> openList = new LinkedList<>();
        LinkedList<Cell> closedList = new LinkedList<>();
        LinkedList<Cell> tmpList = new LinkedList<>();

        // Создадим преграду


        String[][] wF = wFObj.GetField();

        Hero hero = Hero.GetInstance();
        Aim aim = Aim.GetInstance();

        for (int i = 0; i < wFObj.WIDTH; i++) {
            for (int j = 0; j < wFObj.HEIGHT; j++) {
                //Это преграда
                if (Objects.equals(wF[i][j], AmbientWall.icon)) {
                    blockList.add(new Cell(i, j, true));
                }
            }
        }


        // Заполним карту как-то клетками, учитывая преграду
        for (int i = 0; i < wFObj.WIDTH; i++) {
            for (int j = 0; j < wFObj.HEIGHT; j++) {
                cellList.add(new Cell(j, i, blockList.get(j, i).blocked));
            }
        }

        // Стартовая и конечная


        cellList.get(hero.posX, hero.posY).setAsStart();
        start = cellList.get(hero.posX, hero.posY);

        for (int i = 0; i < wFObj.WIDTH; i++) {
            for (int j = 0; j < wFObj.HEIGHT; j++) {
                /*if (Objects.equals(wF[i][j], Hero.icon)) {
                    cellList.get(i, j).setAsStart();
                    start = cellList.get(i, j);
                }*/
                /*if (Objects.equals(wF[i][j], Enemy.icon)) {
                    cellList.get(i, j).setAsFinish();
                    finish = cellList.get(i, j);
                    aim.SetPosition(i,j);

                }*/
                cellList.get(aim.posX, aim.posY).setAsFinish();
                finish = cellList.get(aim.posX, aim.posY);
                arX.add(aim.posX);
                arY.add(aim.posY);
            }
        }

        /*cellList.get(1, 4).setAsStart();
        cellList.get(6, 5).setAsFinish();
        Cell start = cellList.get(1, 4);
        Cell finish = cellList.get(6, 5);*/

        //cellList.printp();

        // Фух, начинаем
        boolean found = false;
        boolean noroute = false;

        //1) Добавляем стартовую клетку в открытый список.
        openList.push(start);

        //2) Повторяем следующее:
        while (!found && !noroute) {
            //a) Ищем в открытом списке клетку с наименьшей стоимостью F. Делаем ее текущей клеткой.
            Cell min = openList.getFirst();
            for (Cell cell : openList) {
                // тут я специально тестировал, при < или <= выбираются разные пути,
                // но суммарная стоимость G у них совершенно одинакова. Забавно, но так и должно быть.
                if (cell.F < min.F) min = cell;
            }

            //b) Помещаем ее в закрытый список. (И удаляем с открытого)
            closedList.push(min);
            openList.remove(min);
            //System.out.println(openList);

            //c) Для каждой из соседних 8-ми клеток ...
            tmpList.clear();
            //tmpList.add(cellList.get(min.x - 1, min.y - 1)); //Не двигаемся по диагонале
            tmpList.add(cellList.get(min.x, min.y - 1));
            //tmpList.add(cellList.get(min.x + 1, min.y - 1));
            tmpList.add(cellList.get(min.x + 1, min.y));
            //tmpList.add(cellList.get(min.x + 1, min.y + 1));
            tmpList.add(cellList.get(min.x, min.y + 1));
            //tmpList.add(cellList.get(min.x - 1, min.y + 1));
            tmpList.add(cellList.get(min.x - 1, min.y));

            for (Cell neightbour : tmpList) {
                //Если клетка непроходимая или она находится в закрытом списке, игнорируем ее. В противном случае делаем следующее.
                if (neightbour.blocked || closedList.contains(neightbour)) continue;

                //Если клетка еще не в открытом списке, то добавляем ее туда. Делаем текущую клетку родительской для это клетки. Расчитываем стоимости F, G и H клетки.
                if (!openList.contains(neightbour)) {
                    openList.add(neightbour);
                    neightbour.parent = min;
                    neightbour.H = neightbour.mandist(finish);
                    neightbour.G = start.price(min);
                    neightbour.F = neightbour.H + neightbour.G;
                    continue;
                }

                // Если клетка уже в открытом списке, то проверяем, не дешевле ли будет путь через эту клетку. Для сравнения используем стоимость G.
                if (neightbour.G + neightbour.price(min) < min.G) {
                    // Более низкая стоимость G указывает на то, что путь будет дешевле. Эсли это так, то меняем родителя клетки на текущую клетку и пересчитываем для нее стоимости G и F.
                    neightbour.parent = min; // вот тут я честно хз, надо ли min.parent или нет
                    neightbour.H = neightbour.mandist(finish);
                    neightbour.G = start.price(min);
                    neightbour.F = neightbour.H + neightbour.G;
                }

                // Если вы сортируете открытый список по стоимости F, то вам надо отсортировать свесь список в соответствии с изменениями.
            }

            //d) Останавливаемся если:
            //Добавили целевую клетку в открытый список, в этом случае путь найден.
            //Или открытый список пуст и мы не дошли до целевой клетки. В этом случае путь отсутствует.

            if (openList.contains(finish)) {
                found = true;
            }

            if (openList.isEmpty()) {
                noroute = true;
            }
        }

        //3) Сохраняем путь. Двигаясь назад от целевой точки, проходя от каждой точки к ее родителю до тех пор, пока не дойдем до стартовой точки. Это и будет наш путь.
        if (!noroute) {
            Cell rd = finish.parent;
            routeFound = true;
            while (!rd.equals(start)) {
                arX.add(rd.x);
                arY.add(rd.y);
                rd.road = true;
                rd = rd.parent;
                if (rd == null) break;
            }

            //cellList.printp();
            //return cellList;
        } else {
            System.out.println("NO ROUTE");
        }
    }
}
