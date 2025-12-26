package tacos.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import tacos.User;
import tacos.data.UserRepo;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1) authorizeRequests()
                .authorizeHttpRequests(auth -> auth
                        // protected routes
                        .requestMatchers("/design", "/orders").hasRole("USER")

                        // public routes
                        .requestMatchers("/", "/public/**", "/login", "/oauth2/**", "/h2-console/**").permitAll()

                        // anything else
                        .anyRequest().permitAll()
                )

                // 2) formLogin().loginPage("/login")
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )

                // 3) oauth2Login()
//                .oauth2Login(oauth2 -> oauth2
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/design", true)
//                )

                // 4) logout()
                .logout(logout -> logout.logoutSuccessUrl("/"))

                // Required for H2 console
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepo userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null) return user;
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner dataLoader(UserRepo userRepo, PasswordEncoder encoder) {
        return args -> {
            if (userRepo.findByUsername("admin") == null) {
                User admin = new User(
                        "admin",
                        encoder.encode("test"),
                        "Admin User",
                        "Street",
                        "City",
                        "State",
                        "00000",
                        "0800000000"
                );
                userRepo.save(admin);
            }
        };
    }
}
