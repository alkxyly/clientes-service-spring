package io.github.alkxyly.clientes;

import io.github.alkxyly.clientes.model.entity.Usuario;
import io.github.alkxyly.clientes.model.repository.UsuarioRepository;
import io.github.alkxyly.clientes.rest.exception.UsuarioCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void salvar(Usuario usuario){
        boolean exists = usuarioRepository.existsByUserName(usuario.getUsername());

        if(exists)
            throw new UsuarioCadastradoException(usuario.getUsername());

        usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Login não Encontrado"));

        return User
                .builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER")
                .build();
    }
}
