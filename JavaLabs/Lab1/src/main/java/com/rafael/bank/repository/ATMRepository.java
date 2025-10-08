package com.rafael.bank.repository;

import com.rafael.bank.model.Account;

import java.math.BigDecimal;
import java.util.List;

/**
 * Интерфейс репозитория банкомата.
 * Определяет основные операции для работы с банковским счетом.
 */
public interface ATMRepository {
    /**
     * Получает текущий баланс аккаунта.
     *
     * @param account аккаунт пользователя
     * @return текущий баланс
     */
    BigDecimal getBalance(Account account);

    /**
     * Пополняет счет на указанную сумму.
     *
     * @param account аккаунт пользователя
     * @param amount  сумма для пополнения
     * @throws Exception если операция не удалась
     */
    void deposit(Account account, BigDecimal amount) throws Exception;

    /**
     * Снимает указанную сумму со счета.
     *
     * @param account аккаунт пользователя
     * @param amount  сумма для снятия
     * @throws Exception если операция не удалась
     */
    void withdraw(Account account, BigDecimal amount) throws Exception;

    /**
     * Получает историю транзакций аккаунта.
     *
     * @param account аккаунт пользователя
     * @return список транзакций
     */
    List<String> getTransactionHistory(Account account);
}
