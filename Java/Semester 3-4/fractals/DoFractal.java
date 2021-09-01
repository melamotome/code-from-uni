package fractals;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DoFractal extends Application{

    //TODO Красивую палитру + переключение палитр
    private static Fractal f;
    private static Palette p;
    private static ImageView iv;
    private double x0;
    private double y0;
    private double dx;
    private static Task<WritableImage> task = null; //Это уже для рисования потоком
    private Pane root;

    @Override
    public void start(Stage primaryStage) {
        //чтобы окно очень маленьким не было
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(400);

        primaryStage.setTitle("Fractals!!!");
        root = initInterface();
        primaryStage.setScene(new Scene(root));
        initInteraction();
        primaryStage.show();

        //Изменение размера окна
        primaryStage.widthProperty().addListener(
                prop -> {
                    primaryStage.getWidth();
                    pasteNewImage();
                }
        );
        primaryStage.heightProperty().addListener(
                prop -> {
                    primaryStage.getHeight();
                    pasteNewImage();
                }
        );

        //Изменение всякого при нажатии клавиши
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED,
                event -> change(event.getCode()));
    }

    private Pane initInterface() {
        iv = new ImageView();

        Pane root = new Pane(iv);
        root.setBackground(new Background(
                new BackgroundFill(Color.BLANCHEDALMOND, null, null)
        ));
//        root.getChildren().add(iv);
        return root;
    }

    private void initInteraction() {
//        Fractal f = new CircleFractal();
//        Palette p = new BlackWhitePalette();

//        Fractal f = new CircleGrayscaleFractal();
//        Palette p = new GraysPalette();

//        Fractal f = new CircleGrayscaleFractal2();
//        Palette p = new GraysPalette();

//        Fractal f = new MandelbrotFractal();
//        Palette p = new GraysPalette();

        f = new MandelbrotFractal();
        p = new HsbPalette();
        x0 = -0.6;
        y0 = 0.6;
        dx = 6./20000;
        drawOnAllPixels(f, p);
    }

    private void drawOnAllPixels(Fractal fractal, Palette palette) {
        if (task != null)
            task.cancel();

        Task<WritableImage> t = new Task<WritableImage>() {
            @Override
            protected WritableImage call() {
                int width = (int)root.getWidth();
                int height = (int)root.getWidth();

                if (width == 0 || height == 0)
                    return null;

                WritableImage wi = new WritableImage(width, height);
                if (task.isCancelled())
                    return null;
                PixelWriter pw = wi.getPixelWriter();
                //x0, y0 - координаты верхнего левого угла картинки в математической системе.
                //x1, y1 - координаты по wi
                //dx - размер пикселя в мат. системе
                for (int x1 = 0; x1 < width; x1++) {
                    for (int y1 = 0; y1 < height; y1++) {
                        double x = x0 + x1*dx;
                        double y = y0 - y1*dx;
                        double colorInd = fractal.getColorIndex(x, y);
                        Color color = palette.getColor(colorInd);
                        pw.setColor(x1,y1,color);
                    }
                    updateValue(
                            new WritableImage(wi.getPixelReader(), width, height)
                    );
                    if (isCancelled()) {
                        System.out.println("cancelled");
                        return null;
                    }
                }
                return wi;
            }
        };
        //TODO
        t.valueProperty().addListener(
                prop -> iv.setImage(t.getValue())
        );
        new Thread(t).start();
        System.out.println("New task started");
        task = t;
        t.setOnSucceeded(
                a -> task = null
        );
    }

    private void pasteNewImage() {
        //Случается чисто при изменении размера окна
        drawOnAllPixels(f, p);
    }

    private void change(KeyCode pressedKey) {
        //Всякие пользовательские изменения
        double fourthW = root.getWidth() / 4;
        double fourthH = root.getHeight() / 4;
        switch(pressedKey) {
            case UP:
                y0 = y0 + fourthH * dx;
                break;
            case DOWN:
                y0 = y0 - fourthH * dx;
                break;
            case LEFT:
                x0 = x0 - fourthW * dx;
                break;
            case RIGHT:
                x0 = x0 + fourthW * dx;
                break;
            case ADD:
                x0 = x0 + 0.5 * root.getWidth() * (dx - dx / 1.5);
                y0 = y0 - 0.5 * root.getHeight() * (dx - dx / 1.5);
                dx = dx / 1.5;
                break;
            case SUBTRACT:
                x0 = x0 + 0.5 * root.getWidth() * (dx - dx * 1.5);
                y0 = y0 - 0.5 * root.getHeight() * (dx - dx * 1.5);
                dx = dx * 1.5;
                break;
        }
        drawOnAllPixels(f, p);
    }
}
