package ma.badreddine.patientsmvc.security.service;

import ma.badreddine.patientsmvc.security.entities.AppRole;
import ma.badreddine.patientsmvc.security.entities.AppUser;

public interface SecurityService{
    AppUser saveNewUser(String username,String password,String rePassword);
    AppRole saveNewRole(String rolename,String description);
    void addRoleToUser(String username,String roleName);
    void RemoveRoleFromUser(String username,String roleName);
    AppUser loadUserByUserName(String username);
}
