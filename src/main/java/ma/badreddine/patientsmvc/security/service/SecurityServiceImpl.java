package ma.badreddine.patientsmvc.security.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import ma.badreddine.patientsmvc.security.entities.AppRole;
import ma.badreddine.patientsmvc.security.entities.AppUser;
import ma.badreddine.patientsmvc.security.repositories.AppRoleRepository;
import ma.badreddine.patientsmvc.security.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService{
    PasswordEncoder passwordEncoder;
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;

    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new  RuntimeException("Passwords not match");
        String hashedPWD=passwordEncoder.encode(password);
        AppUser appUser = new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        AppUser savedAppUser=appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if (appRole!=null) throw  new RuntimeException("Role"+roleName+"already Exist");
        appRole=new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        AppRole savedAppRole =appRoleRepository.save(appRole);
        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if (appUser==null) throw  new RuntimeException("User not found");
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if (appRole==null) throw  new RuntimeException("Role not found");
        appUser.getAppRoles().add(appRole);

    }

    @Override
    public void RemoveRoleFromUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if (appUser==null) throw  new RuntimeException("User not found");
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if (appRole==null) throw  new RuntimeException("Role not found");
        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }
}
