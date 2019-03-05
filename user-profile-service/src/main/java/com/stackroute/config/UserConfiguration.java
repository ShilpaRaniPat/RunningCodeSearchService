package com.stackroute.config;

        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.security.crypto.password.PasswordEncoder;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class UserConfiguration extends WebMvcConfigurationSupport {


    /*
    for encoding password
     */

    @Bean
    public PasswordEncoder encode(){
        return new BCryptPasswordEncoder();
    }
}
