package interfacelambdaanonclasses;
//1.Создайте интерфейс Printable с одним методом void print(),
// этот метод означает что объект печатает что-то на экране.
// Сделайте так, что класс Drawing реализует этот интерфейс.
// Проверьте, что получилось.
//2.Создайте класс PrintableLetter, реализующий интерфейс Printable.
// Пользоваться классом нужно так:
//      PrintableLetter pl = new PrintableLetter("x", 10);
//      pl.print(); //печатает букву x 10 раз
//3. Создайте класс PrintableString, реализующий интерфейс Printable,
// этот класс хранит строку, пользоваться им нужно так:
//      PrintableString ps = new PrintableString("asdf");
//      ps.print(); //печатает asdf
//4. Создайте массив типа Printable[], заполните его
// 1) Drawing 2) PrintableLetter 3) PrintableString
// 4) анонимным классом 5) лямбда выражением.
// В цикле попросите всех что-нибудь напечатать.
public interface Printable {
    void print();
}
