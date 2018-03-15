package de.smava.recrt.service.impl;

import de.smava.recrt.model.BankAccount;
import org.junit.Test;

import java.util.List;

import static org.easymock.EasyMock.replay;

public class BankAccountServiceImplTest extends TestsBase {

    @Test
    public void testGetByAppUser() throws Exception {
        mockFindOneUser(userRepository);
        mockBankAccForUser(bankRepository);
        replay(userRepository);
        replay(bankRepository);
        List<? extends BankAccount> account = bankAccountService.getByAppUser(appUser.getUsername());
        assert (account.size() == 1);
        assert (account.get(0).getBic() == bankAccount.getBic());
        assert (account.get(0).getIban() == bankAccount.getIban());
    }

    @Test
    public void testCreate() throws Exception {
        bankAccount.setAppUser(appUser);
        mockFindOneUser(userRepository); // for create
        mockFindOneUser(userRepository); // for find after create
        mockCreateBankAccForUser(bankRepository);
        mockBankAccForUser(bankRepository);
        replay(userRepository);
        replay(bankRepository);
        BankAccount savedAcc = bankAccountService.create(bankAccount);
        assert (savedAcc != null);
        assert (savedAcc.getIban() == bankAccount.getIban());
        assert (savedAcc.getBic() == bankAccount.getBic());
        // Find after save
        List<? extends BankAccount> foundAcc = bankAccountService.getByAppUser(appUser.getUsername());
        assert (foundAcc.size() == 1);
        assert (foundAcc.get(0).getBic() == bankAccount.getBic());
        assert (foundAcc.get(0).getIban() == bankAccount.getIban());
    }

    @Test
    public void testCreateUserNotFound() throws Exception {
        bankAccount.setAppUser(appUser);
        BankAccount account = bankAccountService.create(bankAccount);
        assert (account == null);
    }
}
