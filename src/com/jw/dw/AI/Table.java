package com.jw.dw.AI;


/**
 * Created by vahma on 01.05.15.
 * Table
 */

class Table<T extends Cell> {

    private int width;
    private int height;
    private Cell[][] table;

    /**
     * Создаем карту игры с размерами width и height
     */
    public Table(int width, int height) {
        this.width = width;
        this.height = height;
        this.table = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                table[i][j] = new Cell(0, 0, false);
            }
        }
    }

    /**
     * Добавить клетку на карту
     */
    public void add(Cell cell) {
        table[cell.x][cell.y] = cell;
    }

    /**
     * Получить клетку по координатам x, y
     *
     * @return клетка, либо фейковая клетка, которая всегда блокирована (чтобы избежать выхода за границы)
     */
    @SuppressWarnings("unchecked")
    public T get(int x, int y) {
        if (x < width && x >= 0 && y < height && y >= 0) {
            return (T) table[x][y];
        }
        // а разве так можно делать в Java? оО но работает оО
        return (T) (new Cell(0, 0, true));
    }

    /**
     * Печать всех клеток поля. Красиво.
     */
    /*public void printp() {

        WorldField wFObj = WorldField.GetInstance();
        for (int i = 0; i < wFObj.WIDTH; i++) {
            for (int j = 0; j < wFObj.HEIGHT; j++) {
                System.out.print(this.get(j, i));
            }
            System.out.println();
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
    */

}

