package de.smava.recrt.service;

import de.smava.recrt.exception.RecrtServiceException;
import de.smava.recrt.model.AppUser;
import de.smava.recrt.model.BankAccount;

import java.util.List;

public interface AppUserService {

    List<? extends AppUser> getAll() throws RecrtServiceException;

    AppUser get(String username) throws RecrtServiceException;

    List<? extends BankAccount> getAccounts(String username)
            throws RecrtServiceException;

}
