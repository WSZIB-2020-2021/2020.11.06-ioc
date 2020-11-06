package pl.edu.wszib.logowanie.gui;

import pl.edu.wszib.logowanie.database.IDataBase;
import pl.edu.wszib.logowanie.model.User;

import java.util.Scanner;

public class GUI {
    private static final GUI instance = new GUI();

    private Scanner scanner = new Scanner(System.in);
    private IDataBase dataBase;  //zg?aszam zapotrzebowanie

    private GUI() {
    }

    public static GUI getInstance() {
        return instance;
    }

    public void showMainMenu() {
        System.out.println("1. Zaloguj");
        System.out.println("2. Zarejestruj");
        System.out.println("3. Wyjdz");

        String choose = scanner.nextLine();

        switch (choose) {
            case "1":
                login();
                showMainMenu();
                break;
            case "2":
                register();
                showMainMenu();
                break;
            case "3":
                System.exit(0);
            case "9":
                showAllUsers();
                showMainMenu();
                break;

                default:
                    System.out.println("Nieprawidlowy wybor !!");
                    showMainMenu();
                    break;
        }
    }

    private void login() {
        System.out.println("Podaj login:");
        String login = scanner.nextLine();
        System.out.println("Podaj haslo:");
        String pass = scanner.nextLine();

        if(this.dataBase.authenticate(login, pass)) {
            System.out.println("Zalogowano !!");
        } else {
            System.out.println("Niezalogowano !!");
        }
    }

    private void register() {
        System.out.println("Podaj login:");
        String login = scanner.nextLine();
        System.out.println("Podaj haslo:");
        String pass = scanner.nextLine();

        if(this.dataBase.register(login, pass)) {
            System.out.println("Zarejestrowano !!");
        } else {
            System.out.println("Login zajety !!");
        }
    }

    private void showAllUsers() {
        for(User currentUser: this.dataBase.getAllUsers()) {
            System.out.println(currentUser);
        }
    }
}
