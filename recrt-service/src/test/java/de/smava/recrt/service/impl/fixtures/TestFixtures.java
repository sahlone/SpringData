package de.smava.recrt.service.impl.fixtures;

import de.smava.recrt.model.AppUser;
import de.smava.recrt.model.AppUserRole;
import de.smava.recrt.model.BankAccount;
import de.smava.recrt.model.UserRole;
import de.smava.recrt.persistence.model.AppUserEntity;
import de.smava.recrt.persistence.model.AppUserRoleEntity;
import de.smava.recrt.persistence.model.BankAccountEntity;
import de.smava.recrt.persistence.repository.AppUserRepository;
import de.smava.recrt.persistence.repository.AppUserRoleRepository;
import de.smava.recrt.persistence.repository.BankAccountRepository;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;

public class TestFixtures {

    protected AppUser appUser = new AppUserEntity() {

        private String user = "testUser";
        private String pass = "testPass";
        private String email = "test@email.com";

        @Override
        public String getUsername() {
            return user;
        }

        @Override
        public void setUsername(String username) {
            this.user = username;
        }

        @Override
        public String getPassword() {
            return pass;
        }

        @Override
        public void setPassword(String password) {
            this.pass = password;
        }

        @Override
        public String getEmail() {
            return email;
        }

        @Override
        public void setEmail(String email) {
            this.email = email;
        }
    };

    protected AppUserRole userRole = new AppUserRoleEntity() {
        private UserRole role = UserRole.ROLE_ADMIN;

        @Override
        public UserRole getRole() {
            return role;
        }

        @Override
        public void setRole(UserRole role) {
            this.role = role;
        }
    };

    protected BankAccount bankAccount = new BankAccountEntity() {

        private String iban= "testIban";
        private String bic= "testBic";

        @Override
        public String getIban() {
            return iban;
        }

        @Override
        public void setIban(String iban) {
            this.iban=iban;
        }

        @Override
        public String getBic() {
            return bic;
        }

        @Override
        public void setBic(String bic) {
            this.bic = bic;
        }

    };

    protected void mockAdminRole(AppUserRoleRepository repository){
        List<AppUserRoleEntity> roles= new ArrayList<>();
        roles.add((AppUserRoleEntity)userRole);
        expect(repository.findByKeyAppUser((AppUserEntity) appUser)).andReturn(roles);
    }

    protected void mock0Roles(AppUserRoleRepository repository){
        List<AppUserRoleEntity> roles= new ArrayList<>();
        expect(repository.findByKeyAppUser((AppUserEntity) appUser)).andReturn(roles);
    }

    protected void mockfindAllUsers(AppUserRepository userRepository){
        List<AppUserEntity> users= new ArrayList<>();
        users.add((AppUserEntity) appUser);
        expect(userRepository.findAll()).andReturn(users);
    }

    protected void mockFindOneUser(AppUserRepository userRepository){
        expect(userRepository.findOne(appUser.getUsername())).andReturn((AppUserEntity) appUser);
    }

    protected void mockBankAccForUser(BankAccountRepository bankAccountRepository){
        List<BankAccountEntity> accounts= new ArrayList<>();
        accounts.add((BankAccountEntity)bankAccount);
        expect(bankAccountRepository.findByAppUser((AppUserEntity) appUser)).andReturn(accounts);
    }

    protected void mockCreateBankAccForUser(BankAccountRepository bankAccountRepository){

        expect(bankAccountRepository.save(anyObject(BankAccountEntity.class))).andReturn((BankAccountEntity) bankAccount);
    }


}
