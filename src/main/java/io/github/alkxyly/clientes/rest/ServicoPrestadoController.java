package io.github.alkxyly.clientes.rest;

import io.github.alkxyly.clientes.model.entity.Cliente;
import io.github.alkxyly.clientes.model.entity.ServicoPrestado;
import io.github.alkxyly.clientes.model.repository.ClienteRepository;
import io.github.alkxyly.clientes.model.repository.ServicoPrestadoRepository;
import io.github.alkxyly.clientes.rest.dto.ServicoPrestadoDTO;
import io.github.alkxyly.clientes.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {

    private final ClienteRepository clienteRepository;
    private final ServicoPrestadoRepository servicoPrestadoRepository;
    private final BigDecimalConverter bigDecimalConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto){
        ServicoPrestado servicoPrestado = new ServicoPrestado();
        Integer idCliente = dto.getIdCliente();
        Cliente cliente  =  clienteRepository.findById(idCliente).orElseThrow(() ->
            new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Cliente não existe"));

        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData(LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        servicoPrestado.setCliente(cliente);

        servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));

        return servicoPrestadoRepository.save(servicoPrestado);
    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(@RequestParam(value = "nome", required = false) String nome,
                                           @RequestParam(value = "mes", required = false) Integer mes){
        return servicoPrestadoRepository.findByNomeAndMes("%" + nome + "%", mes);
    }
}
