package com.dlebre.SecurityDemo.services;

import com.dlebre.SecurityDemo.models.Role;
import com.dlebre.SecurityDemo.models.User;
import com.dlebre.SecurityDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpringAuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (user == null){
            throw  new UsernameNotFoundException("Utilisateur et/ou mot de passe incorrecte !");
        }


        List<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();
        for (Role role:user.getRoles()){
            SimpleGrantedAuthority roleAuth = new SimpleGrantedAuthority(role.getRoleName());
            roleList.add(roleAuth);
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roleList);
    }

}
