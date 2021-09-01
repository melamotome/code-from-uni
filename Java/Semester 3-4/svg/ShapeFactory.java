package svg;

public class ShapeFactory {
    public Shape create(String shapeDescription) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return (Shape) Class.forName(shapeDescription).newInstance();
    }
}
