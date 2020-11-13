package io.github.alkxyly.clientes.model.repository;

import io.github.alkxyly.clientes.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);

    boolean existsByUserName(String username);
}
