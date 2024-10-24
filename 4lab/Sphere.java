public class Sphere extends Shape {
    private double radius; // Радиус шара

    public Sphere(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return 4 * Math.PI * radius * radius; // Площадь поверхности
    }

    public double calculateVolume() {
        return (4.0 / 3) * Math.PI * Math.pow(radius, 3); // Объем
    }

    @Override
    public double calculatePerimeter() {
        return 0; // У шара нет периметра, можно оставить 0 или выбросить исключение
    }
}

