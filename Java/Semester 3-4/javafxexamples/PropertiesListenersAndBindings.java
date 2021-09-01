package javafxexamples;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PropertiesListenersAndBindings extends Application {
    //рефакторингом из инитинтерфейса сделали полями чтобы
    //в инитинтеракшене прописать всякое в ним же

    private TextField textField1;
    private Button button1;

    private Label label2 = new Label();
    private TextField textField2 = new TextField("Введите текст");
    private Label label3 = new Label();
    private TextField textField3 = new TextField("Введите текст");
    private Label label4 = new Label();
    private TextField textField4 = new TextField("Введите текст");
    private Label label5 = new Label();
    private TextField textField5 = new TextField("Введите текст");
    private Label label6 = new Label();
    private TextField textField6 = new TextField("Введите текст");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Properties, listeners and bindings");
        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));
        initInteraction(); //метод для поведений элементов интерфейса, делаем чтоб разделять для удобства
        primaryStage.show();
    }

    private Parent initInterface() {
        FlowPane root = new FlowPane();

        VBox example1 = new VBox();
        textField1 = new TextField();
        button1 = new Button("Пример 1");

        //имена давать осмысленные!! Тут 1 потому что пример 1
        example1.getChildren().addAll(textField1, button1);
        //Раскрасим панельку
        example1.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web("#FF0000"),
                                new CornerRadii(0), //закруглять не хотим
                                Insets.EMPTY //пустой паддинг
                        )
                )
        );
        example1.setStyle("-fx-background-color: #00FF00");
        //другой способ покороче пользуемся сиэсэс со стилями из жавафыкс

        VBox example2 = new VBox(new Label("Пример 2"), textField2, label2);
        VBox example3 = new VBox(new Label("Пример 3"), textField3, label3);
        VBox example4 = new VBox(new Label("Пример 4"), textField4, label4);
        VBox example5 = new VBox(new Label("Пример 5"), textField5, label5);
        VBox example6 = new VBox(new Label("Пример 6"), textField6, label6);

        root.setHgap(8); // отступы
        root.setVgap(8);
        root.getChildren().addAll(example1, example2, example3, example4, example5, example6);
        return root;
    }


    private void initInteraction() {
        //-------------Пример 1-----------------
        //кнопочку учим делать всякое
        //универсальный но сложный способ:
        //В javafxexamples реализована работа с событиями.
        //Разные объекты генерируют события
        //Можно добавлять слушателей(обработиков то есть хендлерво)
        //они реагируют на интересующие их события
        //Кнопка - ActionExent.ACTION в момент нажатия на кнопку
        //В принципе все ноды генерируют собития мыши там всякие
        //но тут неинтересно оно
        button1.addEventHandler(
                ActionEvent.ACTION, //какое событие кнопки интересует
                a -> System.out.println("pressed" + a) // действие лямбда - выражение.
                // а - инфа о действии
                //в этой инфе можно найти источник событие.
                //Если бы было собитие от мыши
                //могли бы узнать положение курсоры
        );
        //или
        button1.setOnAction(a -> System.out.println("Pressed (onAction) " + a));

        //Листенер изменений
        textField1.textProperty().set("");
        System.out.println(textField1.textProperty().get());
        textField1.textProperty().addListener(
                prop -> System.out.println("Значение изменилось на " + textField1.textProperty().get())
        );
        //Prop - ссылка на тот property, который изменился. У нас это тот же самый textField1.textProperty
        //или
        textField1.textProperty().addListener(
                (prop, oldValue, newValue) -> System.out.println(
                        "Значение изменилось с " + oldValue + " на " +  newValue
                )
        );
        //-------------Пример 2-----------------
        //Сделаем так, что значение текст метки всегда будет совпадать с текстом текстфилд два
        textField2.textProperty().addListener(
                prop -> label2.setText(textField2.getText())
        );
        label2.setText(textField2.getText()); //чтобы изначально уже было

        //-------------Пример 3-----------------
        //Свяжем свойства напрямую, чтобы значение свойства текст
        // в метке всегда было равно значению свойства текст в
        // текстовом поле
        label3.textProperty().bind(textField3.textProperty());

        //-------------Пример 4-----------------
        //Хотим, чтобы в метке дополнительно рисовались скобочки
        label4.textProperty().bind( //javafx.beans.bindings
                Bindings.concat( //аналог операции плюс для строк,
                        // но здесь не строки а проперти
                        "[",
                        textField4.textProperty(),
                        "]"
                )
        );

        //-------------Пример 5-----------------
        //Хотим, чтобы на метке текст был всегда в верхнем регистре
        label5.textProperty().bind(
                Bindings.createStringBinding(
                        () -> textField5.getText().toUpperCase(),
                        textField5.textProperty()
                )
        );

        //-------------Пример 6-----------------


    }
}
