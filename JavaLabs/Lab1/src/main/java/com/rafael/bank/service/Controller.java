package com.rafael.bank.service;

import com.rafael.bank.model.Account;
import com.rafael.bank.repository.ATMRepository;
import com.rafael.bank.repository.ATMRepositoryImpl;
import com.rafael.bank.repository.AccountRepositoryImpl;

/**
 * Контроллер, управляющий бизнес-логикой банкомата.
 * Обрабатывает операции аутентификации и регистрации пользователей.
 */
public class Controller {
    private final ATMRepositoryImpl atm;
    private final AccountRepositoryImpl account;

    /**
     * Создает новый экземпляр контроллера с новыми репозиториями.
     */
    public Controller() {
        this.atm = new ATMRepositoryImpl();
        this.account = new AccountRepositoryImpl();
    }

    /**
     * Создает новый экземпляр контроллера с указанными репозиториями.
     *
     * @param atm     репозиторий банкомата
     * @param account репозиторий аккаунтов
     */
    public Controller(ATMRepositoryImpl atm, AccountRepositoryImpl account) {
        this.atm = atm;
        this.account = account;
    }

    /**
     * Выполняет вход пользователя в систему.
     *
     * @param id       идентификатор пользователя
     * @param password пароль пользователя
     * @throws Exception если аутентификация не удалась
     */
    public void login(String id, int password) throws Exception {
        Account acc = account.userAuthenticate(id, password);
        if (acc == null) {
            throw new Exception("Error to login");
        }
        atm.addAccount(acc);
    }

    /**
     * Регистрирует нового пользователя в системе.
     *
     * @return созданный аккаунт пользователя
     */
    public Account register() {
        Account acc = account.createAccount();
        atm.addAccount(acc);
        return acc;
    }

    /**
     * Возвращает репозиторий банкомата.
     *
     * @return репозиторий банкомата
     */
    public ATMRepository getATMRepository() {
        return atm;
    }
}
