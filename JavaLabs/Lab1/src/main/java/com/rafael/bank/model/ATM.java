package com.rafael.bank.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Класс, представляющий банкомат.
 * Содержит информацию о балансе и истории транзакций.
 */
public class ATM {

    private BigDecimal balance;

    private List<String> transactionHistory;

    /**
     * Создает новый экземпляр банкомата.
     *
     * @param balance            начальный баланс
     * @param transactionHistory история транзакций
     */
    public ATM(BigDecimal balance, List<String> transactionHistory) {
        this.balance = balance;
        this.transactionHistory = transactionHistory;
    }

    /**
     * Возвращает текущий баланс банкомата.
     *
     * @return текущий баланс
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Возвращает историю транзакций банкомата.
     *
     * @return список транзакций
     */
    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

}
