import java.util.Scanner;
import java.util.InputMismatchException;
public class ShapeMenu {
    private ShapeGroup shapeGroup;

    public ShapeMenu() {
        shapeGroup = new ShapeGroup();
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Меню ===");
            System.out.println("1. Добавить фигуру");
            System.out.println("2. Удалить фигуру");
            System.out.println("3. Найти фигуру");
            System.out.println("4. Сортировать фигуры по возрастанию площади");
            System.out.println("5. Сортировать фигуры по убыванию площади");
            System.out.println("6. Вывести фигуры по типу");
            System.out.println("7. Показать все фигуры");
            System.out.println("8. Выход");
            System.out.print("Введите ваш выбор (1 - 8): ");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: Введите числовое значение.");
                scanner.nextLine(); // очищаем буфер сканера
                continue;
            }

            switch (choice) {
                case 1:
                    addShape(scanner);
                    break;
                case 2:
                    removeShape(scanner);
                    break;
                case 3:
                    findShape(scanner);
                    break;
                case 4:
                    shapeGroup.sortShapesByArea(true);
                    System.out.println("Фигуры отсортированы по возрастанию площади.");
                    break;
                case 5:
                    shapeGroup.sortShapesByArea(false);
                    System.out.println("Фигуры отсортированы по убыванию площади.");
                    break;
                case 6:
                    printShapesByType(scanner);
                    break;
                case 7:
                    shapeGroup.printAllShapes();
                    break;
                case 8:
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Некорректный выбор. Попробуйте еще раз.");
            }
        }
    }

    private void addShape(Scanner scanner) {
        System.out.print("Введите имя фигуры: ");
        String name = scanner.next();

        if (shapeGroup.findShape(name) != null) {
            System.out.println("Ошибка: Фигура с таким именем уже существует.");
            return;
        }

        System.out.println("Выберите тип фигуры:");
        System.out.println("1. Круг");
        System.out.println("2. Прямоугольник");
        System.out.print("Введите ваш выбор (1 или 2): ");

        int shapeType;
        try {
            shapeType = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Ошибка: Введите числовое значение.");
            scanner.nextLine();
            return;
        }

        Shape shape = null;
        try {
            if (shapeType == 1) {
                System.out.print("Введите радиус круга: ");
                double radius = scanner.nextDouble();
                shape = new Circle(name, radius);
            } else if (shapeType == 2) {
                System.out.print("Введите ширину прямоугольника: ");
                double width = scanner.nextDouble();
                System.out.print("Введите высоту прямоугольника: ");
                double height = scanner.nextDouble();
                shape = new Rectangle(name, width, height);
            } else {
                System.out.println("Ошибка: Некорректный выбор типа фигуры.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Ошибка: Введите числовое значение.");
            scanner.nextLine();
            return;
        }

        shapeGroup.addShape(shape);
        System.out.println("Фигура добавлена.");
    }

    private void removeShape(Scanner scanner) {
        System.out.print("Введите имя фигуры для удаления: ");
        String name = scanner.next();
        Shape shape = shapeGroup.findShape(name);

        if (shape != null) {
            shapeGroup.removeShape(name);
            System.out.println("Фигура удалена.");
        } else {
            System.out.println("Ошибка: Фигура с таким именем не найдена.");
        }
    }

    private void findShape(Scanner scanner) {
        System.out.print("Введите имя фигуры для поиска: ");
        String name = scanner.next();
        Shape shape = shapeGroup.findShape(name);

        if (shape != null) {
            System.out.println(shape);
        } else {
            System.out.println("Ошибка: Фигура не найдена.");
        }
    }

    private void printShapesByType(Scanner scanner) {
        System.out.println("Выберите тип фигуры для вывода:");
        System.out.println("1. Круг");
        System.out.println("2. Прямоугольник");
        System.out.print("Введите ваш выбор (1 или 2): ");

        int typeChoice;
        try {
            typeChoice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Ошибка: Введите числовое значение.");
            scanner.nextLine();
            return;
        }

        String type;
        if (typeChoice == 1) {
            type = "Круг";
        } else if (typeChoice == 2) {
            type = "Прямоугольник";
        } else {
            System.out.println("Ошибка: Некорректный выбор типа фигуры.");
            return;
        }

        shapeGroup.printShapesByType(type);
    }

    public static void main(String[] args) {
        ShapeMenu menu = new ShapeMenu();
        menu.showMenu();
    }
}






