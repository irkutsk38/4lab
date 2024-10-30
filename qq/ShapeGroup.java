import java.util.ArrayList;
import java.util.List;

public class ShapeGroup {
    private List<Shape> shapes;

    public ShapeGroup() {
        this.shapes = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public boolean removeShape(String name) {
        return shapes.removeIf(shape -> shape.getName().equals(name));
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void displayShapes() {
        if (shapes.isEmpty()) {
            System.out.println("Нет фигур для отображения.");
            return;
        }

        System.out.println("Фигуры:");
        for (Shape shape : shapes) {
            System.out.printf("Фигура: %s, Площадь: %.2f, Периметр: %.2f%n", shape.getName(), shape.calculateArea(), shape.calculatePerimeter());
        }
    }

    public Shape findShape(String name) {
        for (Shape shape : shapes) {
            if (shape.getName().equals(name)) {
                return shape;
            }
        }
        return null;
    }

    public void sortShapesByAreaAscending() {
        shapes.sort((s1, s2) -> Double.compare(s1.calculateArea(), s2.calculateArea()));
    }

    public void sortShapesByAreaDescending() {
        shapes.sort((s1, s2) -> Double.compare(s2.calculateArea(), s1.calculateArea()));
    }
}

