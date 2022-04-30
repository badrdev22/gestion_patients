package ma.badreddine.patientsmvc.security.repositories;

import ma.badreddine.patientsmvc.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository <AppUser,String>{
    AppUser findByUsername(String username);


}
