import java.util.Scanner;

public class ShapeMenu {
    private ShapeGroup shapeGroup;
    private ShapeFileHandler shapeFileHandler;

    public ShapeMenu() {
        shapeGroup = new ShapeGroup();
        shapeFileHandler = new ShapeFileHandler(shapeGroup);
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("=== Меню ===");
            System.out.println("1. Работа в терминале");
            System.out.println("2. Работа с файлами");
            System.out.println("3. Выход");
            System.out.print("Введите ваш выбор (1 - 3): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    terminalMenu();
                    break;
                case 2:
                    handleFileOperations();
                    break;
                case 3:
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте еще раз.");
            }
        } while (choice != 3);
    }

    private void terminalMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("=== Работа в терминале ===");
            System.out.println("1. Добавить фигуру");
            System.out.println("2. Удалить фигуру");
            System.out.println("3. Найти фигуру");
            System.out.println("4. Вывести информацию о фигурах");
            System.out.println("5. Вернуться в главное меню");
            System.out.print("Введите ваш выбор (1 - 5): ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Считывание новой строки

            switch (choice) {
                case 1:
                    addShape();
                    break;
                case 2:
                    removeShape();
                    break;
                case 3:
                    findShape();
                    break;
                case 4:
                    displayShapes();
                    break;
                case 5:
                    System.out.println("Возвращение в главное меню.");
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте еще раз.");
            }
        } while (choice != 5);
    }

    private void addShape() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя фигуры: ");
        String name = scanner.nextLine();

        System.out.print("Введите тип фигуры (круг, прямоугольник, шар, цилиндр): ");
        String type = scanner.nextLine();

        Shape shape = null;

        switch (type.toLowerCase()) {
            case "круг":
                System.out.print("Введите радиус: ");
                double radius = scanner.nextDouble();
                shape = new Circle(name, radius);
                break;
            case "прямоугольник":
                System.out.print("Введите ширину: ");
                double width = scanner.nextDouble();
                System.out.print("Введите высоту: ");
                double height = scanner.nextDouble();
                shape = new Rectangle(name, width, height);
                break;
            case "шар":
                System.out.print("Введите радиус: ");
                double sphereRadius = scanner.nextDouble();
                shape = new Sphere(name, sphereRadius);
                break;
            case "цилиндр":
                System.out.print("Введите радиус: ");
                double cylinderRadius = scanner.nextDouble();
                System.out.print("Введите высоту: ");
                double cylinderHeight = scanner.nextDouble();
                shape = new Cylinder(name, cylinderRadius, cylinderHeight);
                break;
            default:
                System.out.println("Некорректный тип фигуры.");
                return;
        }

        shapeGroup.addShape(shape);
        System.out.println("Фигура добавлена: " + shape);
    }

    private void removeShape() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя фигуры для удаления: ");
        String name = scanner.nextLine();
        shapeGroup.removeShape(name);
        System.out.println("Фигура с именем \"" + name + "\" удалена.");
    }

    private void findShape() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя фигуры для поиска: ");
        String name = scanner.nextLine();
        Shape shape = shapeGroup.findShape(name);
        if (shape != null) {
            System.out.println("Фигура найдена: " + shape);
        } else {
            System.out.println("Фигура с именем \"" + name + "\" не найдена.");
        }
    }

    private void displayShapes() {
        System.out.println("=== Фигуры ===");
        shapeGroup.displayShapes();
    }

    private void handleFileOperations() {
        Scanner scanner = new Scanner(System.in);
        int fileChoice;

        do {
            System.out.println("=== Работа с файлами ===");
            System.out.println("1. Добавить фигуру в файл");
            System.out.println("2. Удалить фигуру из файла");
            System.out.println("3. Найти фигуру в файле");
            System.out.println("4. Вернуться в главное меню");
            System.out.print("Введите ваш выбор (1 - 4): ");
            fileChoice = scanner.nextInt();
            scanner.nextLine(); // Считывание новой строки

            switch (fileChoice) {
                case 1:
                    System.out.print("Введите имя файла: ");
                    String addFilename = scanner.nextLine();
                    // Создание фигуры для добавления
                    Shape newShape = createShape();
                    shapeFileHandler.addShapeToFile(addFilename, newShape);
                    break;
                case 2:
                    System.out.print("Введите имя файла: ");
                    String removeFilename = scanner.nextLine();
                    System.out.print("Введите имя фигуры для удаления: ");
                    String shapeToRemove = scanner.nextLine();
                    shapeFileHandler.removeShapeFromFile(removeFilename, shapeToRemove);
                    break;
                case 3:
                    System.out.print("Введите имя файла: ");
                    String findFilename = scanner.nextLine();
                    System.out.print("Введите имя фигуры для поиска: ");
                    String shapeToFind = scanner.nextLine();
                    shapeFileHandler.findShapeInFile(findFilename, shapeToFind);
                    break;
                case 4:
                    System.out.println("Возвращение в главное меню.");
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте еще раз.");
            }
        } while (fileChoice != 4);
    }

    private Shape createShape() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя фигуры: ");
        String name = scanner.nextLine();

        System.out.print("Введите тип фигуры (круг, прямоугольник, шар, цилиндр): ");
        String type = scanner.nextLine();

        Shape shape = null;

        switch (type.toLowerCase()) {
            case "круг":
                System.out.print("Введите радиус: ");
                double radius = scanner.nextDouble();
                shape = new Circle(name, radius);
                break;
            case "прямоугольник":
                System.out.print("Введите ширину: ");
                double width = scanner.nextDouble();
                System.out.print("Введите высоту: ");
                double height = scanner.nextDouble();
                shape = new Rectangle(name, width, height);
                break;
            case "шар":
                System.out.print("Введите радиус: ");
                double sphereRadius = scanner.nextDouble();
                shape = new Sphere(name, sphereRadius);
                break;
            case "цилиндр":
                System.out.print("Введите радиус: ");
                double cylinderRadius = scanner.nextDouble();
                System.out.print("Введите высоту: ");
                double cylinderHeight = scanner.nextDouble();
                shape = new Cylinder(name, cylinderRadius, cylinderHeight);
                break;
            default:
                System.out.println("Некорректный тип фигуры.");
                return null;
        }
        return shape;
    }

    public static void main(String[] args) {
        ShapeMenu menu = new ShapeMenu();
        menu.showMenu();
    }
}











