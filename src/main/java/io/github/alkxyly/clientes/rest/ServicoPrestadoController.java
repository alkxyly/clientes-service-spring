package io.github.alkxyly.clientes.rest;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DateTimeDV;
import io.github.alkxyly.clientes.model.entity.Cliente;
import io.github.alkxyly.clientes.model.entity.ServicoPrestado;
import io.github.alkxyly.clientes.model.repository.ClienteRepository;
import io.github.alkxyly.clientes.model.repository.ServicoPrestadoRepository;
import io.github.alkxyly.clientes.rest.dto.ServicoPrestadoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {

    private  final ClienteRepository clienteRepository;
    private final ServicoPrestadoRepository servicoPrestadoRepository;

    @PostMapping
    public ServicoPrestado salvar(@RequestBody ServicoPrestadoDTO dto){
        ServicoPrestado servicoPrestado = new ServicoPrestado();
        Integer idCliente = dto.getIdCliente();
        Cliente cliente  =  clienteRepository.findById(idCliente).orElseThrow(() ->
            new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Cliente não existe"));

        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData(LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(new BigDecimal(dto.getPreco()));
        return servicoPrestado;
    }
}
