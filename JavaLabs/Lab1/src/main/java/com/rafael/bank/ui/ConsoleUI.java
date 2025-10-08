package com.rafael.bank.ui;

import com.rafael.bank.model.Account;
import com.rafael.bank.service.Controller;

import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Scanner;

/**
 * Класс, реализующий консольный пользовательский интерфейс для банкомата.
 * Обрабатывает ввод пользователя и отображает результаты операций.
 */
public class ConsoleUI {
    private final Controller controller;
    private final Scanner scanner;
    private final InputStream inputStream;
    private final PrintStream outputStream;
    private Account currentAccount;

    /**
     * Создает новый экземпляр ConsoleUI.
     *
     * @param controller   контроллер для управления банковскими операциями
     * @param inputStream  поток ввода для чтения данных от пользователя
     * @param outputStream поток вывода для отображения информации пользователю
     */
    public ConsoleUI(Controller controller, InputStream inputStream, PrintStream outputStream) {
        this.controller = controller;
        this.scanner = new Scanner(inputStream);
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.currentAccount = null;
    }

    /**
     * Устанавливает текущий аккаунт пользователя.
     *
     * @param account аккаунт пользователя
     */
    public void setCurrentAccount(Account account) {
        this.currentAccount = account;
    }

    /**
     * Запускает основной цикл работы банкомата.
     */
    public void start() {
        while (true) {
            if (currentAccount == null) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }

    /**
     * Отображает меню входа в систему.
     */
    public void showLoginMenu() {
        outputStream.println("\n=== Банкомат ===");
        outputStream.println("1. Войти");
        outputStream.println("2. Зарегистрироваться");
        outputStream.println("3. Выход");
        outputStream.println("Выберите действие: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1 -> login();
                case 2 -> register();
                case 3 -> System.exit(0);
                default -> outputStream.println("Неверный выбор!");
            }
        } catch (Exception e) {
            outputStream.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Отображает главное меню банкомата.
     */
    public void showMainMenu() {
        outputStream.println("\n=== Главное меню ===");
        outputStream.println("1. Проверить баланс");
        outputStream.println("2. Снять наличные");
        outputStream.println("3. Пополнить счет");
        outputStream.println("4. История транзакций");
        outputStream.println("5. Выйти");
        outputStream.print("Выберите действие: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1 -> checkBalance();
                case 2 -> withdraw();
                case 3 -> deposit();
                case 4 -> showTransactionHistory();
                case 5 -> currentAccount = null;
                default -> outputStream.println("Неверный выбор!");
            }
        } catch (Exception e) {
            outputStream.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Выполняет вход пользователя в систему.
     *
     * @throws Exception если вход не удался
     */
    public void login() throws Exception {
        outputStream.print("Введите ID: ");
        String id = scanner.nextLine();
        outputStream.print("Введите пароль: ");
        int password = scanner.nextInt();
        scanner.nextLine();

        controller.login(id, password);
        outputStream.println("Успешный вход!");
    }

    /**
     * Регистрирует нового пользователя.
     */
    public void register() {
        Account newAccount = controller.register();
        currentAccount = newAccount;
        outputStream.println("Регистрация успешна!");
        outputStream.println("Ваш ID: " + newAccount.getAccountID());
        outputStream.println("Ваш пароль: " + newAccount.getPassword());
    }

    /**
     * Отображает текущий баланс пользователя.
     */
    public void checkBalance() {
        outputStream.println("Текущий баланс: " + controller.getATMRepository().getBalance(currentAccount));
    }

    /**
     * Выполняет операцию снятия средств.
     */
    public void withdraw() {
        outputStream.print("Введите сумму для снятия: ");
        String input = scanner.nextLine();
        try {
            BigDecimal amount = new BigDecimal(input);
            controller.getATMRepository().withdraw(currentAccount, amount);
            outputStream.println("Операция успешна!");
        } catch (Exception e) {
            outputStream.println(e.getMessage());
        }
    }

    /**
     * Выполняет операцию пополнения счета.
     */
    public void deposit() {
        outputStream.print("Введите сумму для пополнения: ");
        String input = scanner.nextLine();
        try {
            BigDecimal amount = new BigDecimal(input);
            controller.getATMRepository().deposit(currentAccount, amount);
            outputStream.println("Операция успешна!");
        } catch (Exception e) {
            outputStream.println(e.getMessage());
        }
    }

    /**
     * Отображает историю транзакций пользователя.
     */
    public void showTransactionHistory() {
        outputStream.println("\n=== История транзакций ===");
        try {
            List<String> transactions = controller.getATMRepository().getTransactionHistory(currentAccount);
            if (transactions.isEmpty()) {
                outputStream.println("История транзакций пуста");
                return;
            }
            
            for (String transaction : transactions) {
                outputStream.println(transaction);
            }
        } catch (Exception e) {
            outputStream.println("Ошибка при получении истории транзакций: " + e.getMessage());
        }
    }
}
