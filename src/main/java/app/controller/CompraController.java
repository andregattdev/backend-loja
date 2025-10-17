package app.controller;

import app.dto.CompraDTO;
import app.mapper.CompraMapper;
import app.model.Cliente;
import app.model.Compra;
import app.repository.CompraRepository;
import app.service.ClienteService;
import app.service.CompraService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private CompraRepository compraRepository;

    @PostMapping
    public CompraDTO criar(@RequestBody @Valid CompraDTO dto) {
        Compra compra = compraService.realizarCompra(dto);
        return CompraMapper.toDTO(compra);
    }

    @GetMapping("/cliente/{idCliente}")
    public List<CompraDTO> listarPorCliente(@PathVariable int idCliente) {
        return compraService.listarPorCliente(idCliente).stream()
                .map(CompraMapper::toDTO)
                .toList();
    }
    
    @GetMapping("/periodo")
    public List<CompraDTO> listarPorPeriodo(@RequestParam String inicio, @RequestParam String fim) {
        return compraService.listarPorPeriodo(inicio, fim).stream()
            .map(CompraMapper::toDTO)
            .toList();
    }

    
    @GetMapping
    public List<CompraDTO> listarTodas() {
        return compraService.listarTodas().stream()
            .map(CompraMapper::toDTO)
            .toList();
    }
    
    @GetMapping("/produto/{idProduto}")
    public List<CompraDTO> listarPorProduto(@PathVariable int idProduto) {
        return compraRepository.findByItens_Produto_Id(idProduto)
            .stream().map(CompraMapper::toDTO).toList();
    }
    
    @PutMapping("/{id}/status")
    public void atualizarStatus(@PathVariable int id, @RequestParam String status) {
        compraService.atualizarStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) {
        compraService.deletar(id);
    }
}