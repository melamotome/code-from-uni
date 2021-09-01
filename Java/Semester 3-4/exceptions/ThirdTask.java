package exceptions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

//Дан список имен файлов.
// Предполагается, что эти файлы лежат в одном заранее выбранном вами каталоге.
// Нужно переименовать каждый файл так,
// чтобы в начало к нему был приписан номер файла в списке.
// Например, если был список a.txt, b.txt,
// то нужно переименовать файлы в 1 a.txt и 2 b.txt.
// Для каждого файла выведите, удалось ли его переименовать,
// а если нет, то что именно пошло не так.
// Проследите, чтобы не выводилось лишней информации об ошибках
// наподобие содержимого стека.
// Используйте метод Files.move().
public class ThirdTask {
    public static void main(String[] args) {
        List<String> myFiles = new ArrayList<>();
        myFiles.add("a.txt");
        myFiles.add("b.txt");
//        myFiles.add("");
        for (String file : renameFiles(myFiles)) {
            System.out.println(file);
        }
    }

    private static List<String> renameFiles(List<String> fileNames) {
        List<String> newFiles = new ArrayList<>();
        int i = 1;
        for (String fileName : fileNames) {
            try {
                Path currentFile = Paths.get("./files/exceptions", fileName);
                Path newFile = Paths.get("./files/exceptions", Integer.toString(i) + fileName);
                Path newFilePath = Files.move(currentFile, newFile, REPLACE_EXISTING);
                newFiles.add(newFilePath.getName(newFilePath.getNameCount() - 1).toString());
            } catch (java.nio.file.DirectoryNotEmptyException e) {
                System.out.println("Директория с нужным именем существует и не пуста, " + fileName);
            } catch (SecurityException e) {
                System.out.println("Доступа к файлу нет, " + fileName);
            } catch (java.io.IOException e) {
                System.out.println("Ошибка записи или чтения, " + fileName);
            }
            i++;
        }
        return newFiles;
    }
}
