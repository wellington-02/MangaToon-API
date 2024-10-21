package br.ufpb.mangatoonapi.config;

import br.ufpb.mangatoonapi.model.User;
import br.ufpb.mangatoonapi.model.enums.UserType;
import br.ufpb.mangatoonapi.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminUserSeeder {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void seedAdminUser() {
        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setName("Admin");
            admin.setEmail("admin@admin.com");
            admin.setType(UserType.WRITER);
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            userRepository.save(admin);
        }
    }
}