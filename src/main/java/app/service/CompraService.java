package app.service;

import app.dto.CompraDTO;
import app.dto.ItemCompraDTO;
import app.model.Cliente;
import app.model.Compra;
import app.model.ItemCompra;
import app.model.Produto;
import app.repository.CompraRepository;
import app.repository.ItemCompraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    ItemCompraRepository itemCompraRepository;

    public Compra realizarCompra(CompraDTO dto) {
        Cliente cliente = clienteService.buscarPorId(dto.getClienteId());
        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setDataCompra(LocalDate.now());
        compra.setStatus("Confirmado");

        List<ItemCompra> itens = new ArrayList<>();
        float total = 0;

        for (ItemCompraDTO itemDTO : dto.getItens()) {
            Produto produto = produtoService.buscarPorId(itemDTO.getProdutoId());
            if (produto.getEstoque() < itemDTO.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            produto.setEstoque(produto.getEstoque() - itemDTO.getQuantidade());
            produtoService.salvar(produto);

            ItemCompra item = new ItemCompra();
            item.setProduto(produto);
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPrecoUnitario(produto.getPreco());
            item.setCompra(compra);
            itens.add(item);

            total += produto.getPreco() * itemDTO.getQuantidade();
        }

        compra.setItens(itens);
        compra.setValorTotal(total);
        compraRepository.save(compra);
        itemCompraRepository.saveAll(itens);

        return compra;
    }
    
    
    public List<Compra> listarTodas() {
        return compraRepository.findAll();
    }
    
    public List<Compra> listarPorCliente(int idCliente) {
        return compraRepository.findByCliente_Id(idCliente);
    }
    
    public List<Compra> listarPorPeriodo(String inicio, String fim) {
        LocalDate dataInicio = LocalDate.parse(inicio);
        LocalDate dataFim = LocalDate.parse(fim);
        return compraRepository.findByDataCompraBetween(dataInicio, dataFim);
    }

    public void atualizarStatus(int idCompra, String status) {
        Compra compra = compraRepository.findById(idCompra)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));
        compra.setStatus(status);
        compraRepository.save(compra);
    }

    public void deletar(int id) {
        compraRepository.deleteById(id);
    }
}