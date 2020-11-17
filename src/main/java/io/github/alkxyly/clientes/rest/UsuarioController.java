package io.github.alkxyly.clientes.rest;

import io.github.alkxyly.clientes.UsuarioService;
import io.github.alkxyly.clientes.model.entity.Usuario;
import io.github.alkxyly.clientes.model.repository.UsuarioRepository;
import io.github.alkxyly.clientes.rest.exception.UsuarioCadastradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid Usuario usuario) throws Exception{
        try{
            usuarioService.salvar(usuario);
        }catch (UsuarioCadastradoException e){
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
