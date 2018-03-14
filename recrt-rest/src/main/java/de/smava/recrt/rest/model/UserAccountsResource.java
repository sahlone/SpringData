package de.smava.recrt.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.smava.recrt.model.AppUser;
import de.smava.recrt.model.BankAccount;

import java.util.List;

public class UserAccountsResource extends DefaultResource implements  AppUser {

    protected String username;
    protected List<? extends BankAccount> accounts;

    private UserAccountsResource() {
    }

    public UserAccountsResource(String username,List<? extends BankAccount> accounts) {
        this.username = username;
        for(BankAccount account:accounts){
            account.setAppUser(null);
        }
        this.accounts = accounts;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    public List<? extends BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void setPassword(String password) {
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public void setEmail(String email) {
    }
}


