package app.controller;

import app.dto.ItemCompraDTO;
import app.mapper.ItemCompraMapper;
import app.model.ItemCompra;
import app.model.Produto;
import app.service.ItemCompraService;
import app.service.ProdutoService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-compra")
public class ItemCompraController {

    @Autowired
    private ItemCompraService itemCompraService;

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ItemCompraDTO criar(@RequestBody @Valid ItemCompraDTO dto) {
        Produto produto = produtoService.buscarPorId(dto.getProdutoId());
        ItemCompra item = ItemCompraMapper.toEntity(dto, produto);
        ItemCompra salvo = itemCompraService.salvar(item);
        return ItemCompraMapper.toDTO(salvo);
    }

    @GetMapping("/compra/{idCompra}")
    public List<ItemCompraDTO> listarPorCompra(@PathVariable int idCompra) {
        return itemCompraService.listarPorCompra(idCompra).stream()
                .map(ItemCompraMapper::toDTO)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) {
        itemCompraService.deletar(id);
    }
}