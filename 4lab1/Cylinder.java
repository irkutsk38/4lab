public class Cylinder extends Shape {
    private double radius; // Радиус основания
    private double height; // Высота цилиндра

    public Cylinder(String name, double radius, double height) {
        super(name);
        this.radius = radius;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return 2 * Math.PI * radius * (radius + height); // Площадь поверхности
    }

    public double calculateVolume() {
        return Math.PI * radius * radius * height; // Объем
    }

    @Override
    public double calculatePerimeter() {
        return 0; // У цилиндра нет периметра, можно оставить 0 или выбросить исключение
    }
}

