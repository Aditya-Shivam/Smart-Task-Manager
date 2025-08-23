package com.smarttaskmanager.auth_service.service;

import com.smarttaskmanager.auth_service.model.User;
import com.smarttaskmanager.auth_service.model.UserPrincipal;
import com.smarttaskmanager.auth_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        System.out.println(username + "^^^^^^^^^^^");
        System.out.println(userRepo.findAll());
        if(user == null){
            System.out.println("User 404!!!");
            throw new UsernameNotFoundException("User 404");
        }
        return new UserPrincipal(user);
    }
}
