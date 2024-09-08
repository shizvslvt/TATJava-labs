package lab1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Показать список недвижимости");
        System.out.println("2. Добавить недвижимость");
        System.out.println("3. Включить/выключить подключение к базе данных");

        while (true) {
            System.out.println("\nСтатус базы данных: " + (db.status ? "Подключена" : "Отключена"));
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    db.loadDataFromDatabase();
                    System.out.println("\nСписок недвижимости:");
                    for (Estate estate : Estates.getInstance().getEstateList()) {
                        System.out.println(estate);
                    }
                    break;

                case 2:
                    System.out.print("Введите ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введите название: ");
                    String title = scanner.nextLine();
                    System.out.print("Введите цену: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Введите город: ");
                    String city = scanner.nextLine();

                    Estates.getInstance().addEstate(new Estate(id, title, price, city));

                    db.saveDataToDatabase();
                    break;

                case 3:
                    db.toggleStatus();
                    break;

                default:
                    return;
            }
        }
    }
}