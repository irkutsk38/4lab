public class Circle extends Shape {
    private double radius;

    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    // Метод для получения радиуса
    public double getRadius() {
        return radius;
    }

    // Метод для вычисления площади
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    // Метод для вычисления периметра
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    // Переопределяем метод toString для красивого вывода
    @Override
    public String toString() {
        return "Фигура: Круг, Имя: " + getName() + ", Периметр: " + calculatePerimeter() + ", Площадь: " + calculateArea();
    }
}







