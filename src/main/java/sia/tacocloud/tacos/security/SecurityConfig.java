package sia.tacocloud.tacos.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sia.tacocloud.sql.UserInfoRepository;
import sia.tacocloud.tacos.UserInfo;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(UserInfoRepository userRepo)
    {
        return username -> {
            UserInfo userInfo = userRepo.findByUsername(username);
            if (userInfo != null) return userInfo;

            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeRequests()
                .antMatchers("/design","/orders").access("hasRole('USER')")
                .antMatchers("/","/**").access("permitAll()")

                .and()
                .formLogin()
                .loginPage("/login")

                .and()
                .build();
    }

}