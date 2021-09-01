package javafxexamples;
//Создайте интерфейс мессенджера, который мы обсудили на паре.
//Добавьте в список контактов несколько человек,
// научите кнопку посылать сообщение в историю сообщений,
// запретите изменения в истории сообщений
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class GridPaneExample extends Application{

    private Button send;
    private TextField message;
    private TextArea history;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Messenger");
        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));
        initInteraction();
        primaryStage.show();
    }

    private Parent initInterface() {
        //реализуем интерфейс мессенджера с помощью GridPaneExample (который в Panes сделали)
        GridPane root = new GridPane();

        history = new TextArea();
        message = new TextField();
        send = new Button("Send");
        Label contactLabel = new Label("Контакты");

        //text are not editable
        history.setEditable(false);

        //задаю имена в список контактов
        ObservableList<String> names = FXCollections.observableArrayList(
                "Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");
        ListView<String> contacts = new ListView<>(names);

        //можно constraint указать традиционно
        GridPane.setColumnIndex(history, 0);
        //в каком столбце поставить элемент
        GridPane.setRowIndex(history, 0);
        GridPane.setColumnSpan(history, 2);
        //сколько столбцов занимает
        GridPane.setRowSpan(history, 2);
        root.getChildren().add(history);

        //можно проще
        root.add(message, 0, 2);
        //что и где
        root.add(send, 1, 2);
        root.add(contactLabel, 2, 0);
        root.add(contacts, 2, 1, 1,2);
        //для контактс дополнительно указали колко строк и столбцов что занимает

        //Заставим элементы расстягиваться
        //Нужно казывать, как растягиваются строки и столбцы
        //рястянем первый столбец и вторую строку для наших целей
        ColumnConstraints cc0 = new ColumnConstraints();
        cc0.setHgrow(Priority.ALWAYS);

        RowConstraints rc0 = new RowConstraints();
        RowConstraints rc1 = new RowConstraints();
        rc1.setVgrow(Priority.ALWAYS);
        //Есть много других полезных ограничений
        //колумн роу констрейнтс - изначально пустые списки.
        //Чтобы ограничить вторую строку, нужно сделать первый пустой просто

        root.getColumnConstraints().add(cc0);
        root.getRowConstraints().addAll(rc0, rc1);

        //Список контактовнужно сделать не очень высоким,
        //а то последняя строка тоже становится высокой
        contacts.setPrefSize(200, 0);

        return root;
    }

    private void initInteraction() {
        EventHandler<ActionEvent> sendAction = a -> {
            String text = message.getText();
            if (text.trim().isEmpty())
                return;

            history.appendText(text + "\n");
            message.setText("");
            message.requestFocus();
        };

        send.setOnAction(sendAction);
        message.setOnAction(sendAction);
    }
}

