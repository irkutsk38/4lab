public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(String name, double width, double height) {
        super(name);
        this.width = width;
        this.height = height;
    }

    // Метод для получения ширины
    public double getWidth() {
        return width;
    }

    // Метод для получения высоты
    public double getHeight() {
        return height;
    }

    // Метод для вычисления площади
    public double calculateArea() {
        return width * height;
    }

    // Метод для вычисления периметра
    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    // Переопределяем метод toString для красивого вывода
    @Override
    public String toString() {
        return "Фигура: Прямоугольник, Имя: " + getName() + ", Периметр: " + calculatePerimeter() + ", Площадь: " + calculateArea();
    }
}








