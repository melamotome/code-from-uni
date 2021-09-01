package exam;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Test extends Application {

    private Label questionLabel;
    private ImageView imageV;
    private int iterNum = 0;
    private ArrayList<Integer> x;
    private ArrayList<Integer> y;
    private ArrayList<Integer> w;
    private ArrayList<Integer> h;
    private ArrayList<String> questions;
    private int questionsTotal;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(225);
        primaryStage.setTitle("Answer the question by clicking");
        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));
        initInteraction();
        primaryStage.show();
    }

    private Parent initInterface() {
        VBox root = new VBox();
        root.setBackground(new Background(
                new BackgroundFill(Color.BLANCHEDALMOND, null, null)
        ));
        questionLabel = new Label();
        questionLabel.setStyle("-fx-font: 30 courier;");
        imageV = new ImageView();

        //Загружаем как ресурс картинку
        InputStream picStream = Test.class.getResourceAsStream("pic.jpg");
        imageV.setImage(new Image(picStream));

        //Загружаем из ресурса текста в массивы всякие штуки
        try (Scanner in = new Scanner(Test.class.getResourceAsStream("qna.txt"), "UTF-8")) {
            x = new ArrayList<>();
            y = new ArrayList<>();
            w = new ArrayList<>();
            h = new ArrayList<>();
            questions = new ArrayList<>();

            int i = 0;
            while (in.hasNext()) {
                x.add(in.nextInt());
                y.add(in.nextInt());
                w.add(in.nextInt());
                h.add(in.nextInt());
                questions.add(in.nextLine());
                i++;
            }
            questionsTotal = i;
        } catch (Exception e) {
            questionLabel.setText("Произошла ошибка чтения");
            e.printStackTrace();
        }

        root.getChildren().addAll(questionLabel, imageV);
        return root;
    }

    private void initInteraction() {
        ArrayList<Boolean> results = new ArrayList<>();
        questionLabel.setText(questions.get(iterNum));
        imageV.addEventHandler(
            MouseEvent.MOUSE_CLICKED,
            a -> {
                double xOfClick = a.getX();
                double yOfClick = a.getY();
                if (xOfClick >= x.get(iterNum) && (xOfClick <= (x.get(iterNum) + w.get(iterNum))) &&
                        (yOfClick >= y.get(iterNum)) && (yOfClick <= (y.get(iterNum) + h.get(iterNum)))) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                iterNum++;
                if (iterNum >= questionsTotal){
                    //Сделаю строку с ответами верныминеверными
                    StringBuilder resultText = new StringBuilder();
                    for (boolean result : results) {
                        if (result)
                            resultText.append("1");
                        else
                            resultText.append("0");
                    }
                    //Покажу результаты
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Тест завершён");
                    alert.setHeaderText(null);
                    alert.setContentText("Результаты: " + resultText);
                    alert.showAndWait();
                    //Начинаем тест заново
                    iterNum = 0;
                    results.clear();
                }
                questionLabel.setText(questions.get(iterNum));
            }
        );
    }
}
