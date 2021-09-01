package objects.interfaces;

public class InterfacesExample {

    /*
    Интерфейсы - это абстрактные классы, в которых все методы абстрактные. Т.е нет реализации ни одного метода.

    В Java нельзя наследоваться от нескольких классов, но можно от нескольких интерфейсов.

    Колодец extends Строение, РезервуарДляВоды

    В Java так нельзя, если Строение и РезервуарДляВоды - классы, но можно, если это интерфейсы.
     */

    public static void main(String[] args) {
        Animal c1 = new Cat("Барсик");
        // Animal a1 = new Animal(); // нельзя
        Animal a2 = new Animal() {
            @Override
            public String getSound() {
                return "meeeeew";
            }

            @Override
            public String getName() {
                return "Барсик";
            }
        }; // a2 - анонимный класс
    }
}
