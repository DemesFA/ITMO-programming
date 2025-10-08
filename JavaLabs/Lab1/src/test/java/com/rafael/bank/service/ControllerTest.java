package com.rafael.bank.service;

import com.rafael.bank.model.Account;
import com.rafael.bank.repository.ATMRepository;
import com.rafael.bank.repository.ATMRepositoryImpl;
import com.rafael.bank.repository.AccountRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller controller;
    private ATMRepositoryImpl atm;
    private AccountRepositoryImpl account;

    @BeforeEach
    void setUp() {
        atm = new ATMRepositoryImpl();
        account = new AccountRepositoryImpl();
        controller = new Controller(atm, account);
    }

    @Nested
    @DisplayName("Тесты метода login")
    class LoginTests {
        @Test
        @DisplayName("Успешный вход")
        void successfulLogin() throws Exception {
            // Подготовка
            Account testAccount = account.createAccount();
            String id = testAccount.getAccountID();
            int password = testAccount.getPassword();

            // Выполнение
            controller.login(id, password);
        }

        @Test
        @DisplayName("Неуспешный вход - неверные учетные данные")
        void failedLogin() {
            // Проверка
            Exception exception = assertThrows(Exception.class, () -> {
                controller.login("wrongId", 1234);
            });
            assertEquals("Error to login", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Тесты метода register")
    class RegisterTests {
        @Test
        @DisplayName("Успешная регистрация")
        void successfulRegistration() {
            // Выполнение
            Account result = controller.register();

            // Проверка
            assertNotNull(result);
            assertNotNull(result.getAccountID());
            assertNotNull(result.getPassword());
        }
    }

    @Nested
    @DisplayName("Тесты метода getATMRepository")
    class GetATMRepositoryTests {
        @Test
        @DisplayName("Получение репозитория ATM")
        void getATMRepository() {
            // Выполнение
            ATMRepository result = controller.getATMRepository();

            // Проверка
            assertNotNull(result);
            assertEquals(atm, result);
        }
    }
} 