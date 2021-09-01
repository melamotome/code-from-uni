package fractals;

public class CircleFractal implements Fractal {
    //Внутри круга цвет 1, снаружи 0
    @Override
    public double getColorIndex(double x, double y) {
        if(x*x + y*y < 1)
            return 1;
        else
            return 0;
    }
}
