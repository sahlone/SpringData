package de.smava.recrt.service.impl;

import de.smava.recrt.model.AppUser;
import org.junit.Test;

import java.util.List;

import static org.easymock.EasyMock.replay;

public class AppUserServiceImplTest extends TestsBase {

    @Test
    public void testGetAll() throws Exception {
        mockfindAllUsers(userRepository);
        replay(userRepository);
        List<? extends AppUser> all = appUserService.getAll();
        assert(all.size() == 1);
        assert (all.get(0).getUsername()== appUser.getUsername());
    }

    @Test
    public void testGet() throws Exception {
        mockFindOneUser(userRepository);
        replay(userRepository);
        AppUser appUser = appUserService.get(this.appUser.getUsername());
        assert (appUser.getUsername()== this.appUser.getUsername());
        assert (appUser.getPassword()== this.appUser.getPassword());
        assert (appUser.getEmail() == this.appUser.getEmail());

    }
}
