package io.github.alkxyly.clientes.rest.exception;

public class UsuarioCadastradoException extends  RuntimeException {

    public UsuarioCadastradoException(String username){
        super("Usuario  já Cadastrado para o login "+ username);
    }
}
