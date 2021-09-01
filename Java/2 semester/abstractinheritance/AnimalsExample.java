package objects.abstractinheritance;

public class AnimalsExample {

    public static void main(String[] args) {
        //у абстрактного класса нельзя  создать Объект

        //Animal a = new Animal("Шарик");
        //ошибка, потому что класс абстрактный

        Animal a = new Cat("Шарик");
        //а вот так можно.

        Animal[] zoo = new Animal[]{
                new Cat("Барсик"),
                new Dog("Шарик"),
                //анонимные классы
                new Animal("Вуу") {  //после оператора new сразу стоят фигурные скобки. Внутри них - тело
                    @Override             //класса. Там могут быть поля, методы. Это "безымянные классы", потому что
                    public String getSound() {  //у них нет имени. Этот класс, зато, наследник Animal.
                        return "Вуууу";
                    }
                }
        };

        for (Animal animal: zoo
             ) {
            animal.greet();
        }
    }
}
