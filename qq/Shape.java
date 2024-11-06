abstract class Shape {
    protected String name;

    public Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calculateArea();

    @Override
    public String toString() {
        return "Фигура: " + getType() + ", Имя: " + name + ", Площадь: " + calculateArea();
    }

    protected abstract String getType();
}



