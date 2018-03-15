package de.smava.recrt.service.impl;

import de.smava.recrt.model.AppUserRole;
import de.smava.recrt.model.UserRole;
import org.junit.Test;

import java.util.List;

import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;

public class AppUserRoleServiceImplTest extends TestsBase {


    @Test
    public void testGetByAppUser() throws Exception {
        mockAdminRole(roleRepository);
        replay(roleRepository);
        List<? extends AppUserRole> userRoles = roleService.getByAppUser(appUser);
        assert (userRoles.size() == 1);
        assert (userRoles.get(0).getRole() == UserRole.ROLE_ADMIN);
    }

    @Test
    public void testGetByAppUserNoRoles() throws Exception {
        mock0Roles(roleRepository);
        replay(roleRepository);
        List<? extends AppUserRole> userRoles = roleService.getByAppUser(appUser);
        assert (userRoles.size() == 0);
    }
}
