package com.gaurav.security.utility;

import com.gaurav.security.entity.User;
import com.gaurav.security.repository.UserRepository;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceUtilImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceUtilImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).orElse(null);
        UserBuilder userBuilder = null;
        if(user!=null){
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
            userBuilder.password(user.getPassword());
            String[] stringsAuthority = user.getAuthority().stream().map(x -> x.getRoleName()).toArray(String[]::new);
            userBuilder.authorities(stringsAuthority);
        }else {
            throw new UsernameNotFoundException("Username Not Found - "+username);
        }
        return userBuilder.build();
    }
}
