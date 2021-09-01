package fractals;

public interface Fractal {
    //Число от 0 до 1, потом палитра числу будет сопоставлять цвет
    double getColorIndex(double x, double y);
}
