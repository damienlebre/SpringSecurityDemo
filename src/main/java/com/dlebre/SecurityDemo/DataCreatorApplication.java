package com.dlebre.SecurityDemo;

import com.dlebre.SecurityDemo.models.Role;
import com.dlebre.SecurityDemo.models.User;
import com.dlebre.SecurityDemo.services.RoleService;
import com.dlebre.SecurityDemo.services.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class DataCreatorApplication {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(DataCreatorApplication.class);

    }

    @Bean
    public CommandLineRunner dataLoader(UserService userService, RoleService roleService){
        return args -> {
            Role roleAdmin = roleService.findByName("admin");
            Role roleUser =   roleService.findByName("user");

            if(roleAdmin == null){
                roleAdmin = new Role("admin");
                roleService.saveRole(roleAdmin);
            }

            if(roleUser == null){
                roleUser = new Role("user");
                roleService.saveRole(roleUser);
            }

            if(userService.findByUsername("user") == null){
                User user = new User();
                user.setUsername("user");
                user.setPassword("user");
                user.setFirstname("User");
                user.setLastname("User");

                user.addRole(roleUser);

                userService.registerUser(user);
            }

            if(userService.findByUsername("admin") == null){
                User user = new User();
                user.setUsername("admin");
                user.setPassword("admin");
                user.setFirstname("Admin");
                user.setLastname("Admin");

                user.addRole(roleUser);
                user.addRole(roleAdmin);

                userService.registerUser(user);
            }

        };
    }
}
