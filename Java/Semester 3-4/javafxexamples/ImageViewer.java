//Просмотр изображений.
//1/Создайте интерфейс программы просмотра изображений из каталога. Интерфейс состоит из:
//      SplitPane, который занимает всё пространство и вертикально разделяет окно на две части
//      ListView для предпросмотра всех изображений. Он и занимает всю левую панель SplitPane.
//      Выбор каталога для просмотра. Это кнопка «Выбрать» справа сверху на правой панели SplitPane.
//          И TextField с выбранным путем к каталогу. Он занимает все пространство слева от кнопки до ListView.
//      Всё оставшееся пространство на правой панели SplitPane занимает панель Pane с лежащей на ней
//          ImageView. Она используется для просмотра полного изображения.
//2/Для начала выберите какой-то фиксированный каталог с изображениями, напишите код,
//      который находит все файлы в этом каталоге и добавляет их в список ObservableList.
//      Считайте пока, что все файлы в этом каталоге являются изображениями.
//      В следующих пунктах нужно будет обрабатывать ошибки, и тогда
//      потребуется исключить из списка все файлы, которые не удалось загрузить как изображения.
//3/Сделайте так, что при выборе изображения в списке, оно бы отображалось в ImageView.
//      Для того, чтобы иметь доступ к выбранным элементам списка,
//      нужно обратиться к myListView.getSelectionModel().
//      В этой Модели есть информация о выбранном элементе (файле), его индексе (номеру в списке),
//      при этом выбранный элемент и его индекс — это свойства, поэтому вы можете следить
//      за их изменениями и, при необходимотсти, связывать их с другими свойствами.
//4/Сделайте так, что ListView при отображении элемента показывает картинку и имя файла.
//      Используйте CellFactory и метод setGraphics(new ImageView(...)) у вашего анонимного класса,
//      наследника ListCell. А при загрузке картинки с диска, укажите,
//      что вы хотите загрузить уменьшенную версию изображения,
//      для этого можно при загрузке указать в качестве дополнительных аргументов
//      размеры уменьшенной копии, например, 64 на 64.
//5/Сделайте так, что каталог можно выбирать. Используйте класс Directory Chooser.
//      Это должен быть один DirectoryChooser на всю программу, созадйте его один раз
//      при запуске программы, тогда он будет запоминать, какой каталог открывал в прошлый раз.
//6/Сделайте так, что если в данный момент в списке нет ни одного изображения,
//      список пишет «нет изображений».
//7/Сделайте так, что если не выбрано ни одного изображения, в просмотрете изображения
//      написано “выберите изображение”.
//8/Сделайте так, чтобы программа не ломалась при невозможности загрузить картинку,
//      а как-то сообщала об этом пользователю или просто не отображала в списке картинку,
//      которую невозможно загрузить.
package javafxexamples;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageViewer extends Application{

    private VBox right;
    private ListView<File> fileListView;
    private HBox search;
    private TextField pacNameField;
    private Button button;
    private Pane bg;
    private ImageView image;
    final private DirectoryChooser dc = new DirectoryChooser();
    private File imagePackageFile;

    @Override
    public void start(Stage primaryStage) {
        //чтобы окно очень маленьким не было
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(225);

//        //Выбор папки
//        button.setOnAction(
//                a -> {
//                    dc.setInitialDirectory(dc.showDialog(primaryStage));
//                    pacNameField.setText(dc.getInitialDirectory().getPath());
//                    imagePackageFile = new File(dc.getInitialDirectory().getPath());
//
//                    ObservableList<File> listOfImages = FXCollections.observableArrayList();
//                    Pattern imagePattern = Pattern.compile(".+\\.(jpeg|jpg|gif|png|bmp|wbmp)");
//                    for (File file : Objects.requireNonNull(imagePackageFile.listFiles())) {
//                        if (file.isFile()) {
//                            String fileName = file.getName();
//                            Matcher m = imagePattern.matcher(fileName);
//                            if (m.matches()) {
//                                listOfImages.add(file);
//                            }
//                        }
//                    }
//
//
//                    fileListView = new ListView<>(listOfImages);
//
//                }
//        );

        primaryStage.setTitle("Viewing the image");
        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));
        initInteraction(); //метод для поведений элементов интерфейса, делаем чтоб разделять для удобства
        primaryStage.show();
    }

    private Parent initInterface() {
        SplitPane root = new SplitPane();
        pacNameField = new TextField("./files/images");
        imagePackageFile = new File(pacNameField.getText());
        ObservableList<File> listOfImages = FXCollections.observableArrayList();
        Pattern imagePattern = Pattern.compile(".+\\.(jpeg|jpg|gif|png|bmp|wbmp)");
        for (File file : Objects.requireNonNull(imagePackageFile.listFiles())) {
            if (file.isFile()) {
                String fileName = file.getName();
                Matcher m = imagePattern.matcher(fileName);
                if (m.matches()) {
                    listOfImages.add(file);
                }
            }
        }


        fileListView = new ListView<>(listOfImages);
        right = new VBox();

        right.setBackground(new Background(
                new BackgroundFill(Color.BLANCHEDALMOND, null, null)
        ));

        search = new HBox();
        button = new Button("Обзор");

        bg = new Pane();
        image = new ImageView();

        fileListView.setMinWidth(200);

        HBox.setHgrow(pacNameField, Priority.ALWAYS);
        pacNameField.setMinWidth(200);
        button.setMinWidth(80);

        root.getItems().addAll(fileListView, right);
        right.getChildren().addAll(search, bg);
        search.getChildren().addAll(pacNameField, button);
        bg.getChildren().addAll(image);
        return root;
    }

    private void initInteraction() {
        //При выборе элемента в списке показывается та картиночка
        fileListView.getSelectionModel().selectedItemProperty().addListener(
                hhhh -> {
                    try {
                        image.setImage(new Image(new File(String.valueOf(fileListView.getSelectionModel().getSelectedItem())).toURI().toURL().toExternalForm()));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
        );

        //Изменение того, как будет отображаться листвью картиночек
        fileListView.setCellFactory(
            (lv) -> new ListCell<File>() {
                @Override
                protected void updateItem(File item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty){
                        setText("");
                    }
                    else {
                        setText(item.getName());
                        try {
                            ImageView pic = new ImageView(new Image(item.toURI().toURL().toExternalForm()));
                            pic.setFitWidth(64);
                            pic.setFitHeight(64);
                            setGraphic(pic);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        );

        //Свяжу каталог с текстфилдом
//        pacNameField = new TextField("./files/images");
//        imagePackageFile = new File(pacNameField.getText());
    }
}
