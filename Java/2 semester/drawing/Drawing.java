package drawing;

import interfacelambdaanonclasses.Printable;

//1. Создайте конструктор, в котором указывается размер (сколько строк и столбцов)
// и символ, которым изначально все заполнить. Например, new Drawing(20, 30, ‘.’)
//2. Создайте метод print(), он печатает на экране изображение
//3. Метод setPoint(x, y, char) рисует один символ в изображении.
// Методу нужно указать, где и какой символ поставить.
//4. Методы drawVerticalLine(...), drawHorizontalLine(...)
// рисуют вертикальную или горизонтальную линию от заданной точки до заданной.
// Определите сами параметры для методов, они должны определять расположение линии и символ,
// которым линию рисовать.
//5. Метод drawRectangle() рисует прямоугольник по двум противоположным углам,
// стороны прямоугольника вертикальны и горизонтальны. Используйте методы, реализованные ранее.
//6. Необязательно: Рисование произвольной линии (см. необязательную задачу за прошлый семестр)
//7.Рисование круга (или окружности — необязательно).
// Пользователь указывает центр и радиус,
// программа должна пройтись по всем точкам поля и, если они лежат в круге, зарисовать их указанным символом.
//8.Реализуйте метод draw(x, y, Drawing d) нарисовать одно изображение на другом.
// При вызове метода указывается, где и какое изображение рисовать.
//9. (Необязательная задача). Сделайте Drawing неизменяемым: все функции рисования
// возвращают новый Drawing и не меняют текущий.
//10. Используйте все реализованные вами ранее методы, чтобы нарисовать домик.
public class Drawing implements Printable {
    private int rows;
    private int columns;
    private char[][] grid;

    public Drawing(int rows, int columns, char fill) {
        this.rows = rows;
        this.columns = columns;
        grid = new char[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = fill;
            }
        }
    }

    @Override
    public void print() {
        for (char[] lines : this.grid) {
            for (char item : lines) {
                System.out.printf("%c", item);
            }
            System.out.println();
        }
        System.out.println();
    }

    private Drawing copyDrawing() {
        Drawing finalDrawing = new Drawing(this.rows, this.columns, '0');
        for (int i = 0; i < rows; i++)
            System.arraycopy(this.grid[i], 0, finalDrawing.grid[i], 0, columns);
        return finalDrawing;
    }

    public Drawing setPoint(int c, int r, char ch) {
        r = r - 1;
        c = c - 1;
        Drawing finalDrawing = this.copyDrawing();
        if (c < this.columns && r < this.rows)
            finalDrawing.grid[r][c] = ch;
        return finalDrawing;
    }

    private char getPoint(int c, int r) {
        return grid[r][c];
    }

    public Drawing drawVerticalLine(int c, int r1, int r2, char fill) {
        if (r1 > r2) {
            int temp = r1;
            r1 = r2;
            r2 = temp;
        }
        Drawing finalDrawing = this.copyDrawing();
        for (int i = r1; i <= r2; i++) {
            finalDrawing = finalDrawing.setPoint(c, i, fill);
        }
        return finalDrawing;
    }

    public Drawing drawHorizontalLine(int c1, int c2, int r, char fill) {
        if (c1 > c2) {
            int temp = c1;
            c1 = c2;
            c2 = temp;
        }
        Drawing finalDrawing = this.copyDrawing();
        for (int i = c1; i <= c2; i++) {
            finalDrawing = finalDrawing.setPoint(i, r, fill);
        }
        return finalDrawing;
    }

    public Drawing drawLine(int c1,int r1,int c2, int r2, char fill) {
        Drawing finalDrawing = this.copyDrawing();
        int w = c2 - c1;
        int h = r2 - r1;
        int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
        if (w<0) dx1 = -1; else if (w>0) dx1 = 1;
        if (h<0) dy1 = -1; else if (h>0) dy1 = 1;
        if (w<0) dx2 = -1; else if (w>0) dx2 = 1;
        int longest = Math.abs(w);
        int shortest = Math.abs(h);
        if (!(longest>shortest)) {
            longest = Math.abs(h);
            shortest = Math.abs(w);
            if (h<0) dy2 = -1; else if (h>0) dy2 = 1;
            dx2 = 0;
        }
        int numerator = longest >> 1;
        for (int i=0; i<=longest; i++) {
            finalDrawing = finalDrawing.setPoint(c1, r1, fill);
            numerator += shortest;
            if (!(numerator<longest)) {
                numerator -= longest;
                c1 += dx1;
                r1 += dy1;
            } else {
                c1 += dx2;
                r1 += dy2;
            }
        }
        return finalDrawing;
    }

    public Drawing drawRectangle(int c1, int r1, int c2, int r2, char fill) {
        Drawing finalDrawing = this.copyDrawing();
        finalDrawing = finalDrawing.drawHorizontalLine(c1, c2, r1, fill);
        finalDrawing = finalDrawing.drawHorizontalLine(c1, c2, r2, fill);
        finalDrawing = finalDrawing.drawVerticalLine(c1, r1, r2, fill);
        finalDrawing = finalDrawing.drawVerticalLine(c2, r1, r2, fill);
        return finalDrawing;
    }

    public Drawing drawCircleFilled(int c, int r, int rad, char fill) {
        Drawing finalDrawing = this.copyDrawing();
        for (int i = 0; i < this.columns; i++) {
            for (int j = 0; j < this.rows; j++) {
                if ((c - i) * (c - i) + (r - j) * (r - j) <= rad*rad)
                    finalDrawing = finalDrawing.setPoint(i, j, fill);
            }
        }
        return finalDrawing;
    }

    public Drawing drawCircle(int c, int r, int rad, char fill) {
        Drawing finalDrawing = this.copyDrawing();
        int x = 0;
        int y = rad;
        int delta = 1 - 2 * rad;
        int error = 0;
        while (y >= 0) {
            try {
                finalDrawing = finalDrawing.setPoint(c + x, r + y, fill);
                finalDrawing = finalDrawing.setPoint(c + x, r - y, fill);
                finalDrawing = finalDrawing.setPoint(c - x, r + y, fill);
                finalDrawing = finalDrawing.setPoint(c - x, r - y, fill);
            } catch (Exception e) {
            }
            error = 2 * (delta + y) - 1;
            if ((delta < 0) && (error <= 0)) {
                delta += 2 * ++x + 1;
                continue;
            }
            error = 2 * (delta - x) - 1;
            if ((delta > 0) && (error > 0)) {
                delta += 1 - 2 * --y;
                continue;
            }
            x++;
            delta += 2 * (x - y);
            y--;
        }
        return finalDrawing;
    }

    public Drawing draw(int c, int r, Drawing d) {
        Drawing finalDrawing = this.copyDrawing();
        for (int i = 0; i < d.rows; i++) {
            for (int j = 0; j < d.columns; j++)
                finalDrawing = finalDrawing.setPoint(j + c, i + r, d.getPoint(j, i));
        }
        return finalDrawing;
    }
}
