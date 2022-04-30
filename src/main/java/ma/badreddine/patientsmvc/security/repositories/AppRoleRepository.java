package ma.badreddine.patientsmvc.security.repositories;

import ma.badreddine.patientsmvc.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository <AppRole,Long>{
    AppRole findByRoleName(String  rolename);
    

}
