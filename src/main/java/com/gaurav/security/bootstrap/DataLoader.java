package com.gaurav.security.bootstrap;

import com.gaurav.security.entity.Authority;
import com.gaurav.security.entity.User;
import com.gaurav.security.repository.AuthorityRepository;
import com.gaurav.security.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;

    public DataLoader(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Authority zackAuth = new Authority("ROLE_MANAGER");
        authorityRepository.save(zackAuth);
        User zack = new User("zack", new BCryptPasswordEncoder().encode("zack"), true);
        zack.addAuthority(zackAuth);
        userRepository.save(zack);


        Authority codyAuth = new Authority("ROLE_ADMIN");
        authorityRepository.save(codyAuth);
        User cody = new User("cody", new BCryptPasswordEncoder().encode("cody"), true);
        cody.addAuthority(codyAuth);
        userRepository.save(cody);



    }
}
