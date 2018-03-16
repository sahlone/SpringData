package de.smava.recrt.service.impl;

import de.smava.recrt.model.AppUserRole;
import de.smava.recrt.model.UserRole;
import de.smava.recrt.service.impl.fixtures.TestsBase;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import java.util.List;
import static org.easymock.EasyMock.replay;

public class AppUserRoleServiceImplTest extends TestsBase {


    @Test
    public void testGetByAppUser() throws Exception {
        mockAdminRole(roleRepository);
        replay(roleRepository);
        List<? extends AppUserRole> userRoles = roleService.getByAppUser(appUser);
        assertEquals (userRoles.size() , 1);
        assertEquals (userRoles.get(0).getRole(),UserRole.ROLE_ADMIN);
    }

    @Test
    public void testGetByAppUserNoRoles() throws Exception {
        mock0Roles(roleRepository);
        replay(roleRepository);
        List<? extends AppUserRole> userRoles = roleService.getByAppUser(appUser);
        assertEquals (userRoles.size() , 0);
    }
}
