package app.controller;

import app.dto.ClienteDTO;
import app.mapper.ClienteMapper;
import app.model.Cliente;
import app.service.ClienteService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ClienteDTO criar(@RequestBody @Valid ClienteDTO clienteDTO) {
        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        Cliente salvo = clienteService.salvar(cliente);
        return ClienteMapper.toDTO(salvo);
    }

    @GetMapping
    public List<ClienteDTO> listarTodos() {
        return clienteService.listarTodos().stream()
                .map(ClienteMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ClienteDTO buscarPorId(@PathVariable int id) {
        return ClienteMapper.toDTO(clienteService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) {
        clienteService.deletar(id);
    }
}