package fractals;

import javafx.scene.paint.Color;

public class GraysPalette implements Palette {
    //0 - ч, до 1 - б
    @Override
    public Color getColor(double ind) {
        return Color.gray(ind);
    }
}
