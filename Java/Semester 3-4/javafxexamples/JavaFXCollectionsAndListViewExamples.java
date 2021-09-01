package javafxexamples;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class JavaFXCollectionsAndListViewExamples extends Application {

    private ListView<Integer> listView;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Примеры коллекций и ListView");
        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        initData();
    }

    private void initData() {
        ObservableList<Integer> nums = FXCollections.observableArrayList(10, 20, 30);
        listView.setItems(nums);
        nums.add(42);

        nums = new ObservableListBase<Integer>() {
            @Override
            public Integer get(int index) {
                return index + 1;
            }

            @Override
            public int size() {
                return 1000;
            }
        };
        listView.setItems(nums);
    }

    private Parent initInterface() {
        listView = new ListView<>();

        //Научим LV показывать элементы как-то нестандартно
        listView.setCellFactory(
                (lv) -> new ListCell<Integer>() {
                    @Override
                    protected void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty){
                            setText("");}
                        else {
                            String i = Integer.toString(item);
                            String oddity = item % 2 == 0 ? "чёт" : "нечет";
                            setText(i + " " + oddity);
                        }
                    }
                }
        );

        return listView;
    }
}
