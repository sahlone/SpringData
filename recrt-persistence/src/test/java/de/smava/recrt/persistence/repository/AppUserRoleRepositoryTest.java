package de.smava.recrt.persistence.repository;

import de.smava.recrt.model.UserRole;
import de.smava.recrt.persistence.model.AppUserRoleEntity;
import de.smava.recrt.persistence.repository.fixtures.TestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static junit.framework.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class AppUserRoleRepositoryTest extends TestBase {

    @Rollback
    @Test
    public void testFindByKeyAppUser() throws Exception {
        Set<AppUserRoleEntity> roles = new HashSet<>();
        AppUserRoleEntity entity = new AppUserRoleEntity();
        entity.setRole(UserRole.ROLE_USER);
        entity.setAppUser(appUser);
        roles.add(entity);
        appUser.setPassword(pass);
        appUser.setEmail(email);
        appUserRepository.save(appUser);
        appUserRoleRepository.save(entity);
        List<AppUserRoleEntity> rolesReturned=appUserRoleRepository.findByKeyAppUser(appUser);

        assertEquals (rolesReturned.size(),1);
        assertEquals (rolesReturned.get(0).getRole() , UserRole.ROLE_USER);
    }
}
