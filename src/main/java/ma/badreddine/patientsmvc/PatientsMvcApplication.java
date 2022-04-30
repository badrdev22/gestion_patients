package ma.badreddine.patientsmvc;

import ma.badreddine.patientsmvc.entities.Patient;
import ma.badreddine.patientsmvc.repositories.PatientRepository;
import ma.badreddine.patientsmvc.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null,"Badr",new Date(),false,12));
            patientRepository.save(new Patient(null,"Hamza",new Date(),true,48));
            patientRepository.save(new Patient(null,"Zakaria",new Date(),true,198));
            patientRepository.save(new Patient(null,"Zainab",new Date(),false,46));

            patientRepository.findAll().forEach(patient -> {
                System.out.println(patient.getNom());
            });

        };
    }
    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
       return args ->  {
           securityService.saveNewUser("badr","1234","1234");
           securityService.saveNewUser("pedro","1234","1234");
           securityService.saveNewUser("badrdin","1234","1234");

           securityService.saveNewRole("USER","");
           securityService.saveNewRole("ADMIN","");

           securityService.addRoleToUser("badr","USER");
           securityService.addRoleToUser("badr","ADMIN");
           securityService.addRoleToUser("pedro","USER");
           securityService.addRoleToUser("badrdin","USER");
       };
    }
}
