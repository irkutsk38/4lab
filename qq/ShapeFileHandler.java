import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShapeFileHandler {
    private ShapeGroup shapeGroup;

    public ShapeFileHandler(ShapeGroup shapeGroup) {
        this.shapeGroup = shapeGroup;
    }

    // Добавить фигуру в файл
    public void addShapeToFile(String filename, Shape shape) {
        if (shape == null) {
            System.out.println("Ошибка: фигура не может быть null.");
            return;
        }
    
        // Проверяем на уникальность имени фигуры
        List<Shape> existingShapes = readShapesFromFile(filename);
        for (Shape existingShape : existingShapes) {
            if (existingShape.getName().equals(shape.getName())) {
                System.out.println("Ошибка: фигура с именем '" + shape.getName() + "' уже существует.");
                return;
            }
        }
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(shapeToString(shape));
            writer.newLine();
            System.out.println("Фигура успешно добавлена в файл: " + filename);
        } catch (IOException e) {
            System.err.println("Ошибка при добавлении фигуры в файл: " + e.getMessage());
        }
    }
    // Вывести фигуры определенного типа из файла
    public void printShapesByTypeFromFile(String filename, String type) {
        List<Shape> shapes = readShapesFromFile(filename);
        boolean found = false;

        for (Shape shape : shapes) {
                if ((type.equalsIgnoreCase("Circle") && shape instanceof Circle) ||
                (type.equalsIgnoreCase("Rectangle") && shape instanceof Rectangle)) {
                System.out.println(shapeToString(shape));
                found = true;
            }
        }

        if (!found) {
            System.out.println("Фигуры типа '" + type + "' не найдены в файле: " + filename);
        }
    }

    // Удалить фигуру из файла
    public void removeShapeFromFile(String filename, String shapeName) {
        List<Shape> shapes = readShapesFromFile(filename);
        boolean removed = shapes.removeIf(shape -> shape.getName().equals(shapeName));
        if (removed) {
            writeShapesToFile(filename, shapes);
            System.out.println("Фигура успешно удалена из файла: " + filename);
        } else {
            System.out.println("Фигура с именем '" + shapeName + "' не найдена.");
        }
    }

    // Найти фигуру в файле
    public void findShapeInFile(String filename, String shapeName) {
        List<Shape> shapes = readShapesFromFile(filename);
        for (Shape shape : shapes) {
            if (shape.getName().equals(shapeName)) {
                System.out.println("Фигура найдена: " + shape);
                return;
            }
        }
        System.out.println("Фигура не найдена: " + shapeName);
    }

    // Сортировать фигуры в файле
    public void sortShapesByAreaInFile(String filename, boolean ascending) {
        List<Shape> shapes = readShapesFromFile(filename);
        if (shapes.isEmpty()) {
            System.out.println("Ошибка: Нет фигур для сортировки.");
            return;
        }

        System.out.println("Фигуры перед сортировкой: " + shapes); // Для отладки

        if (ascending) {
            Collections.sort(shapes, Comparator.comparingDouble(Shape::calculateArea));
        } else {
            Collections.sort(shapes, Comparator.comparingDouble(Shape::calculateArea).reversed());
        }

        writeShapesToFile(filename, shapes);
        System.out.println("Фигуры успешно отсортированы и записаны в файл: " + filename);
    }

    // Прочитать фигуры из файла
    private List<Shape> readShapesFromFile(String filename) {
        List<Shape> shapes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Shape shape = stringToShape(line);
                if (shape != null) {
                    shapes.add(shape);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении фигур из файла: " + e.getMessage());
        }
        return shapes;
    }

    // Записать фигуры в файл
    private void writeShapesToFile(String filename, List<Shape> shapes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Shape shape : shapes) {
                writer.write(shapeToString(shape));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи фигур в файл: " + e.getMessage());
        }
    }

    // Преобразовать фигуру в строку для записи в файл
    private String shapeToString(Shape shape) {
        if (shape instanceof Circle) {
            return String.format("Фигура: Круг, Имя: %s, Площадь: %.2f", shape.getName(), ((Circle) shape).calculateArea());
        } else if (shape instanceof Rectangle) {
            return String.format("Фигура: Прямоугольник, Имя: %s, Площадь: %.2f", shape.getName(), ((Rectangle) shape).calculateArea());
        }
        return null; // Или выбросить исключение
    }

    // Преобразовать строку в фигуру
    // Преобразовать строку в фигуру
    private Shape stringToShape(String line) {
        String[] parts = line.split(", Площадь: ");
        if (parts.length != 2) {
            System.err.println("Ошибка: некорректный формат строки: " + line);
            return null;
        }

        String[] nameParts = parts[0].replace("Фигура: ", "").split(", Имя: ");
        String type = nameParts[0].trim();
        String name = nameParts[1].trim();

        try {
            double area = Double.parseDouble(parts[1].trim().replace(",", ".")); // Заменяем запятую на точку для парсинга
            if ("Круг".equals(type)) {
                double radius = Math.sqrt(area / Math.PI); // Вычисляем радиус
                return new Circle(name, radius);
            } else if ("Прямоугольник".equals(type)) {
                double width = Math.sqrt(area); // Пример, используйте конкретную логику
                double height = area / width; // Вычисляем высоту
                return new Rectangle(name, width, height);
            }
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: некорректные данные для площади в строке: " + line);
        }
        return null;
    }
}








