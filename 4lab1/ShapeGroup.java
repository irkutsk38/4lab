import java.util.ArrayList;

public class ShapeGroup {
    private ArrayList<Shape> shapes;

    public ShapeGroup() {
        shapes = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public boolean removeShape(String name) {
        return shapes.removeIf(shape -> shape.getName().equals(name));
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void displayShapes() {
        if (shapes.isEmpty()) {
            System.out.println("Нет фигур для отображения.");
            return;
        }

        System.out.println("Фигуры:");
        for (Shape shape : shapes) {
            String shapeType = shape instanceof Circle ? "круг" :
                    shape instanceof Rectangle ? "прямоугольник" :
                            shape instanceof Sphere ? "шар" :
                                    shape instanceof Cylinder ? "цилиндр" : "неизвестный тип";

            System.out.printf("Фигура: %s, Тип: %s, Площадь: %.2f", shape.getName(), shapeType, shape.calculateArea());

            if (shape instanceof Circle || shape instanceof Rectangle) {
                System.out.printf(", Периметр: %.2f", shape.calculatePerimeter());
            }

            if (shape instanceof Sphere) {
                System.out.printf(", Объем: %.2f", ((Sphere) shape).calculateVolume());
            } else if (shape instanceof Cylinder) {
                System.out.printf(", Объем: %.2f", ((Cylinder) shape).calculateVolume());
            }

            System.out.println();
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
}







