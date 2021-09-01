/*Выведите на экран все файлы в текущем каталоге (в каталоге .),
 директории выводить не нужно.
 Не забудьте создать несколько директорий в каталоге для тестирования, 
 чтобы убедиться, что папки не выводятся.*/

 /*Выведите дерево каталогов, начиная с текущей директории. 
 Содержимое каждой новой директории должно сдвигаться на четыре пробела вправо. 
 Например:
         .
             a.txt
             b.txt
             subdir1
                 aa.txt
                 bb.txt
                 subsubdir
                     aaa.txt
             subdir2
                 cc.txt
Для этого создайте функцию void printDirectory(File directory, String indent), 
которой передается директория для печати и indent — строка,
 содержащая нужное количество пробелов для отступа.*/

import java.io.*;
public class Files11 {
	public static void main(String[] args) {
		currentFileFileNames();
		System.out.println();
		printDirectory(new File("."), "");
	}

	//Первое задание
	public static void currentFileFileNames() {
		File f = new File(".");
		File[] listOfFiles = f.listFiles();
		if (listOfFiles != null)
			for (File nameOfFile : listOfFiles)
				if (nameOfFile.isFile())
					System.out.println(nameOfFile.getName());
	}

	//Второе задание
	public static void printDirectory(File directory, String indent) {
		System.out.println(indent + directory.getName());
		indent += "    ";
		File[] listOfFiles = directory.listFiles();
		if (listOfFiles != null)
			for (File currentFile : listOfFiles) {
				if (currentFile.isFile())
					System.out.println(indent + currentFile.getName());
				if (currentFile.isDirectory())
					printDirectory(currentFile, indent);
			}
	}
}

