package drawing;

public class DrawingTest {
//    Нумерование с 1. r - строки "|" "y", c - столбцы "-" "x"
    public static void main(String[] args) {
//        Домик
        Drawing house = new Drawing(15, 50, '-');
        Drawing ground = new Drawing(9, 20, '~');
        house = house.draw(20, 2, ground);
        Drawing walls = new Drawing(10, 30, '=');
        house = house.draw(11, 4, walls);
        house = house.drawRectangle(11, 2, 41, 3, '/');
        house = house.drawCircleFilled(25, 8, 3, '@');
        house = house.drawHorizontalLine(22, 28, 8, '-');
        house = house.drawVerticalLine(25, 5, 11, '|');
        house = house.setPoint(41, 8, '0');
        house = house.drawLine(41, 11, 45, 13, '@');
        house = house.drawCircle(1,1, 3, '*');
        house.print();
    }
}
