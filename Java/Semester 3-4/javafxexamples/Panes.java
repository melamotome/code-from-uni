package javafxexamples;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Panes extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Different Panes");
        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private Parent initInterface() {
        HBox root = new HBox();
//        root.setMinWidth(60);
        VBox leftPane = new VBox();
        HBox.setHgrow(leftPane, Priority.ALWAYS);
        VBox rightPane = new VBox();
//        rightPane.setMinWidth(30);

        TextArea ta = new TextArea();
        ta.setMinWidth(200);
        VBox.setVgrow(ta, Priority.ALWAYS);
        HBox.setHgrow(ta, Priority.ALWAYS);
        HBox tfBBottomPane = new HBox();
        TextField tf =  new TextField("");
        HBox.setHgrow(tf, Priority.ALWAYS);
        Button b = new Button("Send");

        Label l = new Label("Контакты");
        ListView lv = new ListView();
        lv.setMinWidth(200);
        VBox.setVgrow(lv, Priority.ALWAYS);

        root.getChildren().addAll(leftPane, rightPane);

        leftPane.getChildren().addAll(ta, tfBBottomPane);
        tfBBottomPane.getChildren().addAll(tf, b);

        rightPane.getChildren().addAll(l, lv);

        return root;
    }
}
