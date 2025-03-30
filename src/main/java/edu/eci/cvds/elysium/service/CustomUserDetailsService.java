package edu.eci.cvds.elysium.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.eci.cvds.elysium.model.Usuario;
import edu.eci.cvds.elysium.repository.UsuarioRepository;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correoInstitucional) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByCorreoInstitucional(correoInstitucional);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getCorreoInstitucional(), user.getPassword(), new ArrayList<>());
    }
}