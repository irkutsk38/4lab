import java.util.Scanner;

public class ShapeMenu {
    private ShapeFileHandler shapeFileHandler;

    public ShapeMenu(ShapeFileHandler shapeFileHandler) {
        this.shapeFileHandler = shapeFileHandler;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Меню ===");
            System.out.println("1. Работа в терминале");
            System.out.println("2. Работа с файлами");
            System.out.println("3. Выход");
            System.out.print("Введите ваш выбор (1 - 3): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Handle terminal operations (if needed)
                    break;
                case 2:
                    handleFileOperations(scanner);
                    break;
                case 3:
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Некорректный выбор. Попробуйте еще раз.");
            }
        }
    }

    private void handleFileOperations(Scanner scanner) {
        while (true) {
            System.out.println("=== Работа с файлами ===");
            System.out.println("1. Добавить фигуру в файл");
            System.out.println("2. Удалить фигуру из файла");
            System.out.println("3. Найти фигуру в файле");
            System.out.println("4. Вернуться в главное меню");
            System.out.print("Введите ваш выбор (1 - 4): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addShapeToFile(scanner);
                    break;
                case 2:
                    removeShapeFromFile(scanner);
                    break;
                case 3:
                    findShapeInFile(scanner);
                    break;
                case 4:
                    return; // Return to main menu
                default:
                    System.out.println("Некорректный выбор. Попробуйте еще раз.");
            }
        }
    }

    private void addShapeToFile(Scanner scanner) {
        System.out.print("Введите имя файла: ");
        String filename = scanner.next();

        System.out.print("Введите имя фигуры: ");
        String shapeName = scanner.next();

        System.out.println("Выберите тип фигуры:");
        System.out.println("1. Круг");
        System.out.println("2. Прямоугольник");
        System.out.println("3. Цилиндр");
        System.out.println("4. Шар");
        System.out.print("Введите ваш выбор (1 - 4): ");
        int shapeTypeChoice = scanner.nextInt();

        Shape shape = createShape(shapeTypeChoice, shapeName, scanner);
        shapeFileHandler.addShapeToFile(filename, shape);
    }

    private void removeShapeFromFile(Scanner scanner) {
        System.out.print("Введите имя файла: ");
        String filename = scanner.next();

        System.out.print("Введите имя фигуры для удаления: ");
        String shapeName = scanner.next();

        shapeFileHandler.removeShapeFromFile(filename, shapeName);
    }

    private void findShapeInFile(Scanner scanner) {
        System.out.print("Введите имя файла: ");
        String filename = scanner.next();

        System.out.print("Введите имя фигуры для поиска: ");
        String shapeName = scanner.next();

        shapeFileHandler.findShapeInFile(filename, shapeName);
    }

    private Shape createShape(int choice, String shapeName, Scanner scanner) {
        switch (choice) {
            case 1:
                System.out.print("Введите радиус круга: ");
                double radius = scanner.nextDouble();
                return new Circle(shapeName, radius); // Adjust constructor as needed
            case 2:
                System.out.print("Введите ширину прямоугольника: ");
                double width = scanner.nextDouble();
                System.out.print("Введите высоту прямоугольника: ");
                double height = scanner.nextDouble();
                return new Rectangle(shapeName, width, height); // Adjust constructor as needed
            case 3:
                System.out.print("Введите радиус основания цилиндра: ");
                double baseRadius = scanner.nextDouble();
                System.out.print("Введите высоту цилиндра: ");
                double heightCylinder = scanner.nextDouble();
                return new Cylinder(shapeName, baseRadius, heightCylinder); // Adjust constructor as needed
            case 4:
                System.out.print("Введите радиус шара: ");
                double sphereRadius = scanner.nextDouble();
                return new Sphere(shapeName, sphereRadius); // Adjust constructor as needed
            default:
                System.out.println("Некорректный выбор типа фигуры. Фигура не будет создана.");
                return null; // Or handle as appropriate
        }
    }

    public static void main(String[] args) {
        // Create an instance of ShapeGroup (assuming you have this class)
        ShapeGroup shapeGroup = new ShapeGroup(); // Ensure this class exists
        ShapeFileHandler shapeFileHandler = new ShapeFileHandler(shapeGroup);
        ShapeMenu menu = new ShapeMenu(shapeFileHandler);
        menu.showMenu();
    }
}














