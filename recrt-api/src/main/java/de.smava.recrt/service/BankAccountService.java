package de.smava.recrt.service;

import de.smava.recrt.exception.RecrtServiceException;
import de.smava.recrt.model.BankAccount;

import java.util.List;

public interface BankAccountService {

    List<? extends BankAccount> getByAppUser(String appUserName) throws RecrtServiceException;

    BankAccount create(BankAccount account) throws RecrtServiceException;

    /**
     *
     * @param account
     * @return BankAccount
     * @throws RecrtServiceException
     *
     * This method should create acccount asynchronously after proper validation
     */
    BankAccount createAsync(BankAccount account) throws RecrtServiceException;

}
