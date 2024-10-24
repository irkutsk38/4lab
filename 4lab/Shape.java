public abstract class Shape {
    protected String name; // Имя фигуры

    public Shape(String name) {
        this.name = name;
    }

    public abstract double calculateArea();
    public abstract double calculatePerimeter();

    public String getName() {
        return name; // Геттер для имени
    }

    public String getInfo() {
        return "Фигура: " + name;
    }
}

