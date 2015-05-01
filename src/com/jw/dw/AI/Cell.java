package com.jw.dw.AI;

/**
 * Created by vahma on 01.05.15.
 * Cell
 */
class Cell {

    public int x = -1;
    public int y = -1;
    public Cell parent = this;
    public boolean blocked = false;
    private boolean start = false;
    private boolean finish = false;
    public boolean road = false;
    public int F = 0;
    public int G = 0;
    public int H = 0;

    /**
     * Создает клетку с координатами x, y.
     * @param blocked является ли клетка непроходимой
     */
    public Cell(int x, int y, boolean blocked) {
        this.x = x;
        this.y = y;
        this.blocked = blocked;
    }

    /**
     * Функция вычисления манхеттенского расстояния от текущей
     * клетки до finish
     * @param finish конечная клетка
     * @return расстояние
     */
    public int mandist(Cell finish) {
        return 10 * (Math.abs(this.x - finish.x) + Math.abs(this.y - finish.y));
    }

    /**
     * Вычисление стоимости пути до соседней клетки finish
     * @param finish соседняя клетка
     * @return 10, если клетка по горизонтали или вертикали от текущей, 14, если по диагонали
     * (это типа 1 и sqrt(2) ~ 1.44)
     */
    public int price(Cell finish) {
        if (this.x == finish.x || this.y == finish.y) {
            return 10;
        } else {
            return 14;
        }
    }

    /**
     * Устанавливает текущую клетку как стартовую
     */
    public void setAsStart() {
        this.start = true;
    }

    /**
     * Устанавливает текущую клетку как конечную
     */
    public void setAsFinish() {
        this.finish = true;
    }

    /**
     * Сравнение клеток
     * @param second вторая клетка
     * @return true, если координаты клеток равны, иначе - false
     */
    public boolean equals(Cell second) {
        return (this.x == second.x) && (this.y == second.y);
    }

    /**
     * Красиво печатаем
     * * - путь (это в конце)
     * + - стартовая или конечная
     * # - непроходимая
     * . - обычная
     * @return строковое представление клетки
     */
    public String toString() {
        if (this.road) {
            return " * ";
        }
        if (this.start || this.finish) {
            return " + ";
        }
        if (this.blocked) {
            return " # ";
        }
        return " . ";
    }

}