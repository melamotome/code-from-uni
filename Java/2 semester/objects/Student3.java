package objects;
/*
у каждого элемента класса есть модификатор доступа, их 4 штуки
private - элемент виден только внутри класса
public - элемент виден везде
protected - виден только в пакете и наследниках
пустой(не написан) - виден только в пакете(каталоге(папочке)) класса

Методы и конструкторы всегда паблик. Приватными будем делать только внутренние вспомогательные.
Поля - private. Чтобы снаружи не менять, они же внутренние. Внутренне состояние - НАШЕ. Может, мы имя курса и фамилию берём из базы данных
 */
public class Student3 {
    private String name;
    private int course;

    public Student3(String name, int course) {
        this.name = name;
        this.course = course;
    }
    public Student3() {}

    public void greet() {
        System.out.println("Привет, я " + this.name + " с курса " + this.course);
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //как написать то же самое для курса - alt + ins

    public int getCourse() {
        return course;
    }
}
