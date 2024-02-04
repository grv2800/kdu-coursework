    package com.example.miniproject.service;

    import com.example.miniproject.entity.User;
    import com.example.miniproject.repository.UserRepository;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.CommandLineRunner;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;
    @Service
    @Slf4j
    public class DataAddition implements CommandLineRunner {

            UserRepository userRepository;

            PasswordEncoder passwordEncoder;
            UserService userService;

            @Autowired
            DataAddition(UserRepository userRepository,PasswordEncoder passwordEncoder,UserService userService){
                this.userRepository=userRepository;
                this.passwordEncoder=passwordEncoder;
                this.userService=userService;
            }

            @Override
            public void run(String... args) throws Exception {
                log.info("checking");
                User user=new User();
                user.setUsername("grv28");
                user.setPassword(passwordEncoder.encode("new1234"));
                user.setName("GauravMehta");
                user.setFirstName("Gaurav");
                user.setLastName("Mehta");
                user.setEmailId("grv28@gmail.com");
                userService.adduser(user);
            }
    }

