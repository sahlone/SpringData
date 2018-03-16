package de.smava.recrt.persistence.repository;

import de.smava.recrt.persistence.model.AppUserEntity;
import de.smava.recrt.persistence.repository.fixtures.TestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
public class AppUserRepositoryTest extends TestBase {

    @Autowired
    private AppUserRepository appUserRepository;


    @Rollback
    @Test
    public void testSave() throws Exception {
        appUserRepository.save(appUser);
        AppUserEntity result = appUserRepository.findOne(appUser.getUsername());
        assertEquals (result.getUsername() ,appUser.getUsername());
    }

    @Rollback
    @Test
    public void testFindOne() throws Exception {
        appUser.setEmail("testEmail");
        appUserRepository.save(appUser);
        AppUserEntity result = appUserRepository.findOne(appUser.getUsername());
        assertEquals (result.getEmail(), "testEmail");
    }

    @Rollback
    @Test
    public void testDelete() throws Exception {

        appUserRepository.save(appUser);
        AppUserEntity result = appUserRepository.findOne(appUser.getUsername());
        assertEquals (result.getUsername() ,appUser.getUsername());

        appUserRepository.delete(appUser.getUsername());
        result = appUserRepository.findOne(appUser.getUsername());
        assertNull (result);

    }

}
