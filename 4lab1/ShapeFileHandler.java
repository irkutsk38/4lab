import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShapeFileHandler {
    private ShapeGroup shapeGroup;

    public ShapeFileHandler(ShapeGroup shapeGroup) {
        this.shapeGroup = shapeGroup;
    }

    // Сохранение фигур в файл
    public void saveShapesToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            List<Shape> shapes = shapeGroup.getShapes();

            for (Shape shape : shapes) {
                String shapeInfo = formatShapeInfo(shape);
                writer.write(shapeInfo);
                writer.newLine(); // Каждый объект на новой строке
            }

            System.out.println("Фигуры успешно сохранены в файл: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    // Добавление фигуры в файл
    public void addShapeToFile(String filename, Shape shape) {
        if (shape == null) {
            System.out.println("Ошибка: фигура не может быть null.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(formatShapeInfo(shape)); // Запись фигуры в файл
            writer.newLine(); // Переход на новую строку после записи фигуры
            System.out.println("Фигура успешно добавлена в файл: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    // Удаление фигуры из файла
    public void removeShapeFromFile(String filename, String shapeName) {
        List<String> shapesInFile = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean shapeFound = false;

            while ((line = reader.readLine()) != null) {
                if (!line.contains(shapeName)) {
                    shapesInFile.add(line);
                } else {
                    shapeFound = true;
                }
            }

            if (shapeFound) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                    for (String shapeLine : shapesInFile) {
                        writer.write(shapeLine);
                        writer.newLine(); // Каждая фигура на новой строке
                    }
                    System.out.println("Фигура \"" + shapeName + "\" успешно удалена из файла: " + filename);
                }
            } else {
                System.out.println("Фигура \"" + shapeName + "\" не найдена в файле: " + filename);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом: " + e.getMessage());
        }
    }

    // Поиск фигуры в файле
    public void findShapeInFile(String filename, String shapeName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean shapeFound = false;

            while ((line = reader.readLine()) != null) {
                if (line.contains(shapeName)) {
                    System.out.println("Найдена фигура: " + line);
                    shapeFound = true;
                    break;
                }
            }

            if (!shapeFound) {
                System.out.println("Фигура \"" + shapeName + "\" не найдена в файле: " + filename);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    // Форматирование информации о фигуре
    private String formatShapeInfo(Shape shape) {
        StringBuilder shapeInfo = new StringBuilder();
        shapeInfo.append("Фигура: ").append(shape.getName()).append(", ");
        shapeInfo.append("Тип: ").append(getShapeType(shape)).append(", ");
        shapeInfo.append("Площадь: ").append(String.format("%.2f", shape.calculateArea()));

        if (shape instanceof Circle || shape instanceof Rectangle) {
            shapeInfo.append(", Периметр: ").append(String.format("%.2f", shape.calculatePerimeter()));
        }

        if (shape instanceof Sphere) {
            shapeInfo.append(", Объем: ").append(String.format("%.2f", ((Sphere) shape).calculateVolume()));
        } else if (shape instanceof Cylinder) {
            shapeInfo.append(", Объем: ").append(String.format("%.2f", ((Cylinder) shape).calculateVolume()));
        }
        return shapeInfo.toString();
    }

    // Получение типа фигуры
    private String getShapeType(Shape shape) {
        if (shape instanceof Circle) {
            return "круг";
        } else if (shape instanceof Rectangle) {
            return "прямоугольник";
        } else if (shape instanceof Sphere) {
            return "шар";
        } else if (shape instanceof Cylinder) {
            return "цилиндр";
        } else {
            return "неизвестный тип";
        }
    }
}










