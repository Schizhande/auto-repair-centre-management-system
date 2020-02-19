package com.schizhande.usermanagementsystem.config;


import com.schizhande.usermanagementsystem.events.CreateUserListener;
import com.schizhande.usermanagementsystem.notifications.service.EmailService;
import com.schizhande.usermanagementsystem.dao.*;
import com.schizhande.usermanagementsystem.service.*;
import com.schizhande.usermanagementsystem.service.impl.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserServiceBeanFactory {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final TokenRepository tokenRepository;

    public UserServiceBeanFactory(UserRepository userRepository,
                                  PasswordEncoder passwordEncoder,
                                  ApplicationEventPublisher applicationEventPublisher, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
        this.tokenRepository = tokenRepository;
    }

    @Bean
    public UserService userService(RoleRepository roleRepository) {
        return new UserServiceImpl(userRepository, passwordEncoder, roleRepository, tokenRepository, applicationEventPublisher);
    }

    @Bean
    public RoleService roleService(RoleRepository roleRepository) {
        return new RoleServiceImpl(roleRepository);
    }

    @Bean
    public ApplicationListener createUserListener(EmailService emailService) {
        return new CreateUserListener(emailService, tokenRepository);
    }

    @Bean
    public UserInformationService userInformationService(UserInformationRepository userInformationRepository) {
        return new UserInformationServiceImpl(userRepository, userInformationRepository);
    }

    @Bean
    public UserPasswordService userPasswordService() {
        return new UserPasswordServiceImpl(tokenRepository, userRepository, passwordEncoder, applicationEventPublisher);
    }

    @Bean
    public UserPermissionService userPermissionService(UserPermissionRepository userPermissionRepository) {
        return new UserPermissionServiceImpl(userPermissionRepository);
    }


}
