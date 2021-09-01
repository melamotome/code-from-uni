package multithreading;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class JavaFXTaskExample extends Application {
    ListView<Integer> ints = new ListView<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button start = new Button("Start");
        primaryStage.setScene(new Scene(new VBox(ints, start)));
        primaryStage.show();

        start.setOnAction((e) -> {
            Task<List<Integer>> task = new Task<List<Integer>>() {

                @Override
                protected List<Integer> call() {
                    //в методе call выполняются действия, которые должны быть в новом потоке
                    //тут и выполняться должна наши вычисления
                    List<Integer> primes = new ArrayList<>();

                    for (int i = 2; i < 100_000_000; i++) {
                        if (isPrime(i))
                            primes.add(i);
                        updateValue(
                                new ArrayList<>(primes)
                        ); //не сет. Создаем новый список, потому что иначе метод апдейт считает, что значение не меняется. чтобы он понимал, что список изменился, и вызывал слушателя и всякое
                    }
//                    после завершения метода изменится  значение свойства value. но мы хотим сделать так, чтобы по мере вычисления добавлялись числа
//                    можно делать самим апдейты updateValue();
                    return primes;
                }
            };
            new Thread(task).start(); // запускаем наш таск как поток
//            ints.setItems(primes);  такое уже не работает. поставим слушатель, чтобы узнать, что таск всё посчитал
//            task.setOnSucceeded(); можно поставить на удачное завершение вычисления
//            вообще мы сейчас создадим слушатель внутри слушателя но у нас нет времени норм это делать
            task.valueProperty().addListener(e2 -> {
                ints.setItems(FXCollections.observableList(
                        task.getValue()
                ));
            });
            //и теперь зависаний не должно быть, пока он считает мы может двигать окошко и все дела
//            при каждом нажатии кнопки создаются новые потоки расчёта
//          //но могут быть эксепшены!!
        });
    }

    private boolean isPrime(int n) {
        int n2 = (int)Math.floor(Math.sqrt(n)); //оптимизация перебора но надо аккуратно
        for (int i = 2; i <= n2; i++)
            if (n % i == 0)
                return false;
            return true;
    }

    /*
    * В джавафыкс приложении всегда есть поток пользовательского интерфейса
    * в нём отрисовываются элементы интерфейса и выполняются слушатели
    * пока кнопка производит вычисления внутри слушателя, в интерфейсе ничего не рисуется
    * Поэтому в хорошей программе с интерфейсом в  потоке UI (в частности слушателя) не будет делаться долгих вычислений
    * поэтому для вычислений нужно создавать отдельные потоки
    * но возникнут трудности с сихронизациями, получением результата вычисление из потока и остальное
    * поэтому есть вспомогательный класс Task<?>.
    * он запускается как поток, чтобы выполнить вычисления, а результат вычисления указывается в параметре типа
    * те если результат вычисления это число, то Task<Integer>, если список чисел, то Task<List<Integer>>
    *
    * */
}
