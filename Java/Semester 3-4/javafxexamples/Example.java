package javafxexamples;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class Example extends Application {

    //реализуем метод старст, который объясняет, как запускается приложение.
    //альт энтер на ошибке где экстендс
    //В качестве аргумента в метод передается stage - ссылка на окно приложения
    @Override
    public void start(Stage primaryStage) {
        //задаём тайтл
        primaryStage.setTitle("Hello World javafxexamples Application");

        Parent root = initInterface();
        //задаём сцену, сцена содержит информацию о парент
        primaryStage.setScene(new Scene(root /* , width, height*/));

        primaryStage.show();
    }

    private Parent initInterface() {
        //в конструкторе можно сразу указать детей и внешний отступ
        HBox root = new HBox();
        root.setSpacing(10); //можно указать отступ позже

        Button b1 = new Button("Hello");
        Label l1 = new Label("Just a text");
        TextField tf1 = new TextField("Enter something");

        //добавляем детей, обращаясь к их списку
        // ещё можно при создании указать детей Hbox root1 = new HBox(b1, l1, tf1)
        root.getChildren().addAll(b1, l1, tf1);

        //Constraints:
        HBox.setHgrow(tf1, Priority.ALWAYS);
        HBox.setMargin(b1, new Insets(8));

        //параметры шбокса
        root.setAlignment(Pos.BOTTOM_CENTER);
        return root;
    }
}
