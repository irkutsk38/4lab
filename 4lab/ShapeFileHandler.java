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

    public void addShapeToFile(String filename, Shape shape) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(formatShapeInfo(shape));
            writer.newLine();
            System.out.println("Фигура успешно добавлена в файл: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

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
                        writer.newLine();
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

    private String formatShapeInfo(Shape shape) {
        StringBuilder shapeInfo = new StringBuilder();
        shapeInfo.append("Фигура: ").append(shape.getName()).append(", ");
        shapeInfo.append("Тип: ").append(shape instanceof Circle ? "круг" :
                shape instanceof Rectangle ? "прямоугольник" :
                        shape instanceof Sphere ? "шар" :
                                shape instanceof Cylinder ? "цилиндр" : "неизвестный тип").append(", ");
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
}







