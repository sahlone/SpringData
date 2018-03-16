package de.smava.recrt.service.impl;

import de.smava.recrt.model.BankAccount;
import de.smava.recrt.service.impl.fixtures.TestsBase;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.easymock.EasyMock.replay;

public class BankAccountServiceImplTest extends TestsBase {

    @Test
    public void testGetByAppUser() throws Exception {
        mockFindOneUser(userRepository);
        mockBankAccForUser(bankRepository);
        replay(userRepository);
        replay(bankRepository);
        List<? extends BankAccount> account = bankAccountService.getByAppUser(appUser.getUsername());
        assertEquals (account.size() , 1);
        assertEquals (account.get(0).getBic() , bankAccount.getBic());
        assertEquals (account.get(0).getIban() , bankAccount.getIban());
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
        assertNotNull (savedAcc);
        assertEquals (savedAcc.getIban() , bankAccount.getIban());
        assertEquals (savedAcc.getBic() , bankAccount.getBic());
        // Find after save
        List<? extends BankAccount> foundAcc = bankAccountService.getByAppUser(appUser.getUsername());
        assertEquals (foundAcc.size() , 1);
        assertEquals (foundAcc.get(0).getBic() , bankAccount.getBic());
        assertEquals (foundAcc.get(0).getIban() , bankAccount.getIban());
    }

    @Test
    public void testCreateUserNotFound() throws Exception {
        bankAccount.setAppUser(appUser);
        BankAccount account = bankAccountService.create(bankAccount);
        assertNull (account);
    }
}
