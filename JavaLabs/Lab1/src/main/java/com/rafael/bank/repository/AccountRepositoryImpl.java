package com.rafael.bank.repository;

import com.rafael.bank.model.Account;

import java.util.Set;
import java.util.UUID;
import java.util.HashSet;
import java.util.Random;

public class AccountRepositoryImpl implements AccountRepository {

    private final Set<Account> accountSet = new HashSet<>();


    /**
     * Создает новый аккаунт с уникальным идентификатором.
     *
     * @return созданный аккаунт
     */
    @Override
    public Account createAccount() {
        Account account = new Account(UUID.randomUUID().toString(), new Random().nextInt());
        accountSet.add(account);
        return account;
    }

    /**
     * Аутентифицирует пользователя по идентификатору и паролю.
     *
     * @param accountID идентификатор аккаунта
     * @param password  пароль аккаунта
     * @return аккаунт пользователя, если аутентификация успешна, null в противном случае
     */
    @Override
    public Account userAuthenticate(String accountID, int password) {
        Account account = new Account(accountID, password);
        if (accountSet.contains(account)) {
            return account;
        }
        return null;
    }
}
