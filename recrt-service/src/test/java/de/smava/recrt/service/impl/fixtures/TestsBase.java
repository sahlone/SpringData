package de.smava.recrt.service.impl.fixtures;

import de.smava.recrt.persistence.repository.AppUserRepository;
import de.smava.recrt.persistence.repository.AppUserRoleRepository;
import de.smava.recrt.persistence.repository.BankAccountRepository;
import de.smava.recrt.service.BankAccountService;
import de.smava.recrt.service.impl.AppUserRoleServiceImpl;
import de.smava.recrt.service.impl.AppUserServiceImpl;
import de.smava.recrt.service.impl.BankAccountServiceImpl;
import de.smava.recrt.service.impl.fixtures.TestFixtures;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;

public class TestsBase extends TestFixtures{

    protected static AppUserRoleRepository roleRepository;
    protected static AppUserRepository userRepository;
    protected static BankAccountRepository bankRepository;

    protected static AppUserRoleServiceImpl roleService;
    protected static AppUserServiceImpl appUserService;
    protected static BankAccountServiceImpl bankAccountService;

    @BeforeClass
    public static void setup() {

        roleRepository = mock(AppUserRoleRepository.class);
        userRepository= mock(AppUserRepository.class);
        bankRepository= mock(BankAccountRepository.class);

        roleService = new AppUserRoleServiceImpl();
        roleService.setRepository(roleRepository);

        appUserService= new AppUserServiceImpl();
        appUserService.setAppUserRepository(userRepository);

        bankAccountService=new BankAccountServiceImpl();
        bankAccountService.setAppUserRepository(userRepository);
        bankAccountService.setBankAccountRepository(bankRepository);

    }

    @Before
    public void before() throws Exception {
        reset(roleRepository); // reset the mock after each test
        reset(userRepository);
        reset(bankRepository);
    }
}
