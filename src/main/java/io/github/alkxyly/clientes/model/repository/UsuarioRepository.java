package io.github.alkxyly.clientes.model.repository;

import io.github.alkxyly.clientes.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {

}
