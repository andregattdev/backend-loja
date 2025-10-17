package app.service;

import app.model.ItemCompra;
import app.repository.ItemCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCompraService {

    @Autowired
    private ItemCompraRepository itemCompraRepository;

    public ItemCompra salvar(ItemCompra item) {
        item.setSubtotal(item.getPrecoUunitario() * item.getQuantidade());
        return itemCompraRepository.save(item);
    }

    public List<ItemCompra> listarPorCompra(int idCompra) {
        return itemCompraRepository.findByCompraId(idCompra);
    }

    public void deletar(int id) {
        itemCompraRepository.deleteById(id);
    }
}