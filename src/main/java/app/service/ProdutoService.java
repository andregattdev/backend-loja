package app.service;

import app.model.Produto;
import app.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(int id) {
        return produtoRepository.findById(id).orElse(null);
    }
    
    public boolean atualizarEstoque(int id, int quantidadeVendida) {
        Produto produto = buscarPorId(id);
        if (produto == null || produto.getEstoque() < quantidadeVendida) {
            return false;
        }
        produto.setEstoque(produto.getEstoque() - quantidadeVendida);
        produtoRepository.save(produto);
        return true;
    }

    public void deletar(int id) {
        produtoRepository.deleteById(id);
    }
}