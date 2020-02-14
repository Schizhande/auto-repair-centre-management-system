package com.schizhande.usermanagementsystem.service.impl;

import com.schizhande.usermanagementsystem.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        UserDetails userDetails = userRepository.findByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));

        new AccountStatusUserDetailsChecker().check(userDetails);

        return userDetails;

    }
}
