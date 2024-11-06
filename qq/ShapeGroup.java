import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
class ShapeGroup {
    private List<Shape> shapes;

    public ShapeGroup() {
        shapes = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void removeShape(String name) {
        shapes.removeIf(shape -> shape.getName().equals(name));
    }

    public Shape findShape(String name) {
        return shapes.stream()
                .filter(shape -> shape.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void sortShapesByArea(boolean ascending) {
        shapes.sort(ascending ? Comparator.comparingDouble(Shape::calculateArea)
                : Comparator.comparingDouble(Shape::calculateArea).reversed());
    }

    public void printShapesByType(String type) {
        shapes.stream()
                .filter(shape -> shape.getType().equalsIgnoreCase(type))
                .forEach(System.out::println);
    }

    public void printAllShapes() {
        shapes.forEach(System.out::println);
    }
}

