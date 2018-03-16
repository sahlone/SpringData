package de.smava.recrt.service.impl;

import de.smava.recrt.model.AppUser;
import de.smava.recrt.service.impl.fixtures.TestsBase;
import org.junit.Test;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static org.easymock.EasyMock.replay;

public class AppUserServiceImplTest extends TestsBase {

    @Test
    public void testGetAll() throws Exception {
        mockfindAllUsers(userRepository);
        replay(userRepository);
        List<? extends AppUser> all = appUserService.getAll();
        assertEquals(all.size(),1);
        assertEquals (all.get(0).getUsername(),appUser.getUsername());
    }

    @Test
    public void testGet() throws Exception {
        mockFindOneUser(userRepository);
        replay(userRepository);
        AppUser appUser = appUserService.get(this.appUser.getUsername());
        assertEquals (appUser.getUsername(), this.appUser.getUsername());
        assertEquals (appUser.getPassword(), this.appUser.getPassword());
        assertEquals (appUser.getEmail() , this.appUser.getEmail());

    }
}
