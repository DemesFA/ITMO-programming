package com.rafael.bank.repository;

import com.rafael.bank.model.ATM;
import com.rafael.bank.model.Account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ATMRepositoryImpl implements ATMRepository {

    private final Map<Account, ATM> accountMap = new HashMap<>();


    /**
     * Добавляет аккаунт в репозиторий банкомата.
     *
     * @param account аккаунт для добавления
     */
    public void addAccount(Account account) {
        accountMap.put(account, new ATM(new BigDecimal(0), new ArrayList<>()));
    }


    /**
     * Получает текущий баланс аккаунта.
     *
     * @param account аккаунт пользователя
     * @return текущий баланс
     */

    @Override
    public BigDecimal getBalance(Account account) {
        return accountMap.get(account).getBalance();
    }
    /**
     * Пополняет счет на указанную сумму.
     *
     * @param account аккаунт пользователя
     * @param amount  сумма для пополнения
     * @throws Exception если сумма отрицательная
     */

    @Override
    public void deposit(Account account, BigDecimal amount) throws Exception {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("Error: amount < 0");
        }
        List<String> transactionhistory = (accountMap.get(account).getTransactionHistory());
        transactionhistory.add("Deposit:" + amount);
        var balance = getBalance(account).add(amount);
        ATM atm = new ATM(balance, transactionhistory);
        accountMap.replace(account, atm);
    }



    /**
     * Снимает указанную сумму со счета.
     *
     * @param account аккаунт пользователя
     * @param amount сумма для снятия
     * @throws Exception если сумма превышает баланс или отрицательная
     */

    @Override
    public void withdraw(Account account, BigDecimal amount) throws Exception {
        var balance = getBalance(account).subtract(amount);
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("Error:Balance < 0");
        }
        List<String> transactionhistory = accountMap.get(account).getTransactionHistory();
        transactionhistory.add("Withdraw:" + amount);
        ATM atm = new ATM(balance, transactionhistory);
        accountMap.replace(account, atm);
    }

    /**
     * Получает историю транзакций аккаунта.
     *
     * @param account аккаунт пользователя
     * @return список транзакций
     */
    @Override
    public List<String> getTransactionHistory(Account account) {
        return accountMap.get(account).getTransactionHistory();
    }
}
