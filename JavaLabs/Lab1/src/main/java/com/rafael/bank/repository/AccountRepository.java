package com.rafael.bank.repository;

import com.rafael.bank.model.Account;

/**
 * Интерфейс репозитория аккаунтов.
 * Определяет операции для создания и аутентификации аккаунтов.
 */
public interface AccountRepository {
    /**
     * Создает новый аккаунт.
     *
     * @return созданный аккаунт
     */
    Account createAccount();

    /**
     * Аутентифицирует пользователя по идентификатору и паролю.
     *
     * @param accountID идентификатор аккаунта
     * @param password  пароль аккаунта
     * @return аккаунт пользователя, если аутентификация успешна, null в противном случае
     */
    Account userAuthenticate(String accountID, int password);
}
