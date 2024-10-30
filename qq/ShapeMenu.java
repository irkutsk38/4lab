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
            System.out.println("1. Работа с файлами");
            System.out.println("2. Выход");
            System.out.print("Введите ваш выбор (1 - 2): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleFileOperations(scanner);
                    break;
                case 2:
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
            System.out.println("4. Сортировать фигуры по возрастанию площади в файле");
            System.out.println("5. Сортировать фигуры по убыванию площади в файле");
            System.out.println("6. Вывод фигур по типу");
            System.out.println("7. Вернуться в главное меню");
            System.out.print("Введите ваш выбор (1 - 7): ");
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
                    sortShapesByArea(scanner, true); // Сортировка по возрастанию
                    break;
                case 5:
                    sortShapesByArea(scanner, false); // Сортировка по убыванию
                    break;
                case 6:
                    scanner.nextLine(); // Очистка буфера после nextInt()
                    System.out.print("Введите имя файла: ");
                    String filename = scanner.nextLine();
                    
                    System.out.println("Выберите тип фигуры для вывода:");
                    System.out.println("1. Круг");
                    System.out.println("2. Прямоугольник");
                    System.out.print("Введите ваш выбор (1 или 2): ");
                    int typeChoice = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера после nextInt()
                    
                    String type = null;
                    if (typeChoice == 1) {
                        type = "Circle";
                    } else if (typeChoice == 2) {
                        type = "Rectangle";
                    } else {
                        System.out.println("Некорректный выбор типа фигуры.");
                        break;
                    }
                
                    shapeFileHandler.printShapesByTypeFromFile(filename, type);
                    break;
                case 7:
                    return; 
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
        System.out.print("Введите ваш выбор (1 - 2): ");
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

    private void sortShapesByArea(Scanner scanner, boolean ascending) {
        System.out.print("Введите имя файла для сортировки: ");
        String filename = scanner.next();
        shapeFileHandler.sortShapesByAreaInFile(filename, ascending);
    }

    private Shape createShape(int choice, String shapeName, Scanner scanner) {
        switch (choice) {
            case 1:
                System.out.print("Введите радиус круга: ");
                double radius = scanner.nextDouble();
                return new Circle(shapeName, radius); 
            case 2:
                System.out.print("Введите ширину прямоугольника: ");
                double width = scanner.nextDouble();
                System.out.print("Введите высоту прямоугольника: ");
                double height = scanner.nextDouble();
                return new Rectangle(shapeName, width, height); 
            default:
                System.out.println("Некорректный выбор типа фигуры. Фигура не будет создана.");
                return null; 
        }
    }

    public static void main(String[] args) {
        ShapeGroup shapeGroup = new ShapeGroup(); 
        ShapeFileHandler shapeFileHandler = new ShapeFileHandler(shapeGroup);
        ShapeMenu menu = new ShapeMenu(shapeFileHandler);
        menu.showMenu();
    }
}







