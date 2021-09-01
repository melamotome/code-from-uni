package objects.abstractinheritance;

public class AnimalsExample {
    public static void main(String[] args) {
        // У абстрактного класса нельзя создать объект

        // Animal a = new Animal("Шарик");
        // Ошибка, потому что класс абстрактный.

        Animal a = new Cat("Шарик");
        // а вот так можно.

        Animal[] zoo = new Animal[] {
                new Cat("Барсик"),
                new Dog("Шарик"),
                // анонимные классы
                new Animal("Вуу") { // После оператора new открываем фигурные скобки.
                    @Override // Внутри них - тело класса. Там могут быть поля, методы.
                    public String getSound() { // Это "безымянные классы", потому что у них нет имени.
                        return "Вууу"; // Этот класс наследник Animal.
                    }

                }
        };

        for (Animal animal: zoo)
            animal.greet();

    }
}
