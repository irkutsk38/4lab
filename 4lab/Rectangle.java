public class Rectangle extends Shape {
    private double width; // Ширина прямоугольника
    private double height; // Высота прямоугольника

    public Rectangle(String name, double width, double height) {
        super(name);
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height; // Расчет площади
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height); // Расчет периметра
    }
}
