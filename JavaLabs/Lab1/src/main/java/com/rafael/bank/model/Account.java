package com.rafael.bank.model;

import java.util.Objects;

/**
 * Класс, реализующий модель аккаунта пользователя.
 * Содержит информацию об идентификаторе и пароле аккаунта.
 */
public class Account {
    private String accountID;
    private int password;

    /**
     * Создает новый аккаунт с указанными параметрами.
     *
     * @param accountID идентификатор аккаунта
     * @param password  пароль аккаунта
     */
    public Account(String accountID, int password) {
        this.accountID = accountID;
        this.password = password;
    }

    /**
     * Возвращает идентификатор аккаунта.
     *
     * @return идентификатор аккаунта
     */
    public String getAccountID() {
        return accountID;
    }

    /**
     * Возвращает пароль аккаунта.
     *
     * @return пароль аккаунта
     */
    public int getPassword() {
        return password;
    }

    /**
     * Сравнивает текущий аккаунт с указанным объектом.
     *
     * @param o объект для сравнения
     * @return true, если объекты равны, false в противном случае
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountID, account.accountID) && Objects.equals(password, account.password);
    }

    /**
     * Возвращает хеш-код аккаунта.
     *
     * @return хеш-код аккаунта
     */
    @Override
    public int hashCode() {
        return Objects.hash(accountID, password);
    }
}