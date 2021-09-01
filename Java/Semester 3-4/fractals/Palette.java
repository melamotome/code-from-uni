package fractals;

import javafx.scene.paint.Color;

public interface Palette {
    //Палитра сопоставляет числу от 0 до 1 цвет по своему правилу
    Color getColor(double ind);
}
