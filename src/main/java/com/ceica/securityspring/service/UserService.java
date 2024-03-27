package com.ceica.securityspring.service;

import com.ceica.securityspring.model.Authority;
import com.ceica.securityspring.model.User;
import com.ceica.securityspring.repository.AuthorityRepository;
import com.ceica.securityspring.repository.ItemsRepository;
import com.ceica.securityspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository=authorityRepository;

        passwordEncoder=new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con nombre de usuario: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user.getAuthorities())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Authority> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
        return grantedAuthorities;
    }


    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public void deleteUserById(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        userOptional.ifPresent(user -> {
            userRepository.delete(user);
        });
    }



    public void crearUsuario(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser=userRepository.save(user);
        //Encriptamos password

        Authority authority=new Authority();
        authority.setAuthority("User");
        authority.setUser_id(newUser.getId());
        authorityRepository.save(authority);
    }
}