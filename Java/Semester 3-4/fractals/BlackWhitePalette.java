package fractals;

import javafx.scene.paint.Color;

public class BlackWhitePalette implements Palette {
    //Пополам чёрное и белое
    @Override
    public Color getColor(double ind) {
        if (ind < 0.5)
            return Color.BLACK;
        else
            return Color.WHITE;
    }
}
