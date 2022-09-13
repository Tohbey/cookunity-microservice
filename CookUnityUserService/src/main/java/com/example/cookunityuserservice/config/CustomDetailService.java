package com.example.cookunityuserservice.config;

import com.example.cookunityuserservice.exceptions.NotFoundException;
import com.example.cookunityuserservice.model.User;
import com.example.cookunityuserservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(username);
        CustomDetail userDetail = null;
        if(user.isPresent()){
            userDetail = new CustomDetail();
            userDetail.setUser(user.get());
        }else{
            throw new NotFoundException("User with email "+username+" does not exist");
        }
        return userDetail;
    }
}
