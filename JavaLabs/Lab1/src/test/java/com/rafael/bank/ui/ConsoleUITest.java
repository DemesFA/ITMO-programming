package com.rafael.bank.ui;

import com.rafael.bank.model.Account;
import com.rafael.bank.service.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleUITest {
    private ConsoleUI consoleUI;
    private Controller controller;
    private Account testAccount;
    private ByteArrayOutputStream outputStream;
    private ByteArrayInputStream inputStream;
    private PrintStream originalOut;
    private PrintStream testPrintStream;

    @BeforeEach
    void setUp() {
        controller = new Controller();
        outputStream = new ByteArrayOutputStream();
        testPrintStream = new PrintStream(outputStream);
        originalOut = System.out;
        System.setOut(testPrintStream);
    }

    @Nested
    @DisplayName("Тесты метода login")
    class LoginTests {
        @Test
        @DisplayName("Успешный вход")
        void successfulLogin() throws Exception {
            // Подготовка
            testAccount = controller.register();
            String input = testAccount.getAccountID() + "\n" + testAccount.getPassword() + "\n";
            inputStream = new ByteArrayInputStream(input.getBytes());
            consoleUI = new ConsoleUI(controller, inputStream, testPrintStream);

            // Выполнение
            consoleUI.login();

            // Проверка
            assertTrue(outputStream.toString().contains("Успешный вход!"));
        }

        @Test
        @DisplayName("Неуспешный вход - неверные учетные данные")
        void failedLogin() {
            // Подготовка
            String input = "123\n123";
            inputStream = new ByteArrayInputStream(input.getBytes());
            consoleUI = new ConsoleUI(controller, inputStream, testPrintStream);
            // Проверка
            assertThrows(Exception.class, () -> consoleUI.login());
        }
    }

    @Nested
    @DisplayName("Тесты метода register")
    class RegisterTests {
        @Test
        @DisplayName("Успешная регистрация")
        void successfulRegistration() {
            // Подготовка
            inputStream = new ByteArrayInputStream("".getBytes());
            consoleUI = new ConsoleUI(controller, inputStream, testPrintStream);

            // Выполнение
            consoleUI.register();

            // Проверка
            String output = outputStream.toString();
            assertTrue(output.contains("Регистрация успешна!"));
            assertTrue(output.contains("Ваш ID: "));
            assertTrue(output.contains("Ваш пароль: "));
        }
    }

    @Nested
    @DisplayName("Тесты метода withdraw")
    class WithdrawTests {
        @Test
        @DisplayName("Успешное снятие средств")
        void successfulWithdraw() throws Exception {
            // Подготовка
            testAccount = controller.register();
            controller.login(testAccount.getAccountID(), testAccount.getPassword());

            String input = "100.0\n100\n";
            inputStream = new ByteArrayInputStream(input.getBytes());
            consoleUI = new ConsoleUI(controller, inputStream, testPrintStream);
            consoleUI.setCurrentAccount(testAccount);

            // Выполнение
            consoleUI.deposit();

            consoleUI.withdraw();

            // Проверка
            assertTrue(outputStream.toString().contains("Операция успешна!"));
        }

        @Test
        @DisplayName("Неуспешное снятие средств - недостаточно средств")
        void failedWithdraw() throws Exception {
            // Подготовка
            testAccount = controller.register();
            controller.login(testAccount.getAccountID(), testAccount.getPassword());
            String input = "1000.0\n";
            inputStream = new ByteArrayInputStream(input.getBytes());
            consoleUI = new ConsoleUI(controller, inputStream, testPrintStream);
            consoleUI.setCurrentAccount(testAccount);

            // Выполнение
            consoleUI.withdraw();

            // Проверка
            assertTrue(outputStream.toString().contains("Error"));
        }

    }

    @Nested
    @DisplayName("Тесты метода deposit")
    class DepositTests {
        @Test
        @DisplayName("Успешное пополнение счета")
        void successfulDeposit() throws Exception {
            // Подготовка
            testAccount = controller.register();
            controller.login(testAccount.getAccountID(), testAccount.getPassword());
            String input = "500.0\n";
            inputStream = new ByteArrayInputStream(input.getBytes());
            consoleUI = new ConsoleUI(controller, inputStream, testPrintStream);
            consoleUI.setCurrentAccount(testAccount);

            // Выполнение
            consoleUI.deposit();

            // Проверка
            String output = outputStream.toString();
            assertTrue(output.contains("Операция успешна!"), "Должно быть сообщение об успешной операции");
            assertFalse(output.contains("Exception"), "Не должно быть сообщений об исключениях");
        }

        @Test
        @DisplayName("Пополнение счета с отрицательной суммой")
        void depositWithNegativeAmount() throws Exception {
            // Подготовка
            testAccount = controller.register();
            controller.login(testAccount.getAccountID(), testAccount.getPassword());
            String input = "-100.0\n";
            inputStream = new ByteArrayInputStream(input.getBytes());
            consoleUI = new ConsoleUI(controller, inputStream, testPrintStream);
            consoleUI.setCurrentAccount(testAccount);

            // Выполнение
            consoleUI.deposit();

            // Проверка
            String output = outputStream.toString();
            assertFalse(output.contains("Операция успешна!"), "Операция не должна быть успешной");
            assertTrue(output.contains("Exception") || output.contains("Error"), "Должно быть сообщение об ошибке");
        }

    }

    @Nested
    @DisplayName("Тесты метода checkBalance")
    class CheckBalanceTests {
        @Test
        @DisplayName("Проверка баланса")
        void checkBalance() throws Exception {
            // Подготовка
            testAccount = controller.register();
            controller.login(testAccount.getAccountID(), testAccount.getPassword());
            inputStream = new ByteArrayInputStream("".getBytes());
            consoleUI = new ConsoleUI(controller, inputStream, testPrintStream);
            consoleUI.setCurrentAccount(testAccount);

            // Выполнение
            consoleUI.checkBalance();

            // Проверка
            assertTrue(outputStream.toString().contains("Текущий баланс: "));
        }
    }
} 