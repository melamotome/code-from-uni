package javafxexamples;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ResourcesExamples extends Application {

    private Label title;
    private ImageView picture;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(700);

        primaryStage.setTitle("Пример про работу с ресурсами");
        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        loadImage();
        loadText();
    }

    private Parent initInterface() {
        //Будет ImageView  для отображения картинки и лейбел под ним для текста.
        //Возьмём их из ресурсов
        picture = new ImageView();
        title = new Label();

        return new VBox(picture, title);
    }

    private void loadImage() {
        //через javafx.scene.Image
        try (InputStream image = ResourcesExamples.class.getResourceAsStream("cat.jpg")) {
            Image img = new Image(image);
            picture.setImage(img);
            picture.setFitHeight(600);
            picture.setFitWidth(600);
            picture.setPreserveRatio(true);
        } catch (IOException e) {

        }

        //Можно через url
//        URL picURL = ResourcesExamples.class.getResource("cat.jpg");
//        System.out.println(picURL);
//        new Image(picURL.toExternalForm());
    }

    private void loadText() {
        //1 способ InputStream
        try (InputStream text = ResourcesExamples.class.getResourceAsStream("title.jp")) {
            //Буфер делать не очень честно. Рассчитываем, что хватит на тайтл столько
            byte[] bytesFromInputStream = new byte[1024];
            int read = text.read(bytesFromInputStream);
            String titleText = new String(bytesFromInputStream, 0, read, StandardCharsets.UTF_8);
            title.setText(titleText);
            title.setFont(Font.font(40));
        } catch (IOException e) {
            title.setText("Не удалось загрузить текст");
        }
    }
}
