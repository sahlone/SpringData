package de.smava.recrt.persistence.repository.fixtures;

import de.smava.recrt.model.UserRole;
import de.smava.recrt.persistence.config.PersistenceConfig;
import de.smava.recrt.persistence.model.AppUserEntity;
import de.smava.recrt.persistence.model.AppUserRoleEntity;
import de.smava.recrt.persistence.model.BankAccountEntity;
import de.smava.recrt.persistence.repository.AppUserRepository;
import de.smava.recrt.persistence.repository.AppUserRoleRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = PersistenceConfig.class)
@ActiveProfiles("recrt")
@Transactional
public  class TestBase extends  DataFixtures{


    @Autowired
    protected AppUserRoleRepository appUserRoleRepository;

    @Autowired
    protected AppUserRepository appUserRepository;

    @PersistenceContext
    protected EntityManager em;

    @Before
    public void before(){

    }
}

class DataFixtures {

    protected String user = "testUser";
    protected String pass = "testPass";
    protected String email = "test@email.com";
    protected UserRole userRole= UserRole.ROLE_ADMIN;
    protected String bic= "bic";
    protected String iban = "iban";

    protected AppUserEntity appUser = new AppUserEntity(user);

    protected AppUserRoleEntity role = new AppUserRoleEntity() ;
    protected BankAccountEntity bankAccount = new BankAccountEntity(iban,bic,appUser) ;


}


