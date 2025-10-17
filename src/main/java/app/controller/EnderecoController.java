package app.controller;

import app.dto.EnderecoDTO;
import app.mapper.EnderecoMapper;
import app.model.Cliente;
import app.model.Endereco;
import app.service.ClienteService;
import app.service.EnderecoService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public EnderecoDTO criar(@RequestBody @Valid EnderecoDTO dto) {
        Cliente cliente = clienteService.buscarPorId(dto.getClienteId());
        Endereco endereco = EnderecoMapper.toEntity(dto, cliente);
        Endereco salvo = enderecoService.salvar(endereco);
        return EnderecoMapper.toDTO(salvo);
    }

    @GetMapping("/cliente/{idCliente}")
    public List<EnderecoDTO> listarPorCliente(@PathVariable int idCliente) {
        return enderecoService.listarPorCliente(idCliente).stream()
                .map(EnderecoMapper::toDTO)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) {
        enderecoService.deletar(id);
    }
}