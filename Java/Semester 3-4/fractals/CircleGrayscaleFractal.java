package fractals;

public class CircleGrayscaleFractal implements Fractal{
    //Внутри по расстоянию до центра серое, дальше 1 - чёрный
    @Override
    public double getColorIndex(double x, double y) {
        double result = Math.sqrt(x*x + y*y);
        if (result > 1)
            return 1;
        else
            return result;
    }
}
