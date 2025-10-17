package app.mapper;

import app.dto.ItemCompraDTO;
import app.model.ItemCompra;
import app.model.Produto;

public class ItemCompraMapper {

    public static ItemCompraDTO toDTO(ItemCompra item) {
        ItemCompraDTO dto = new ItemCompraDTO();
        dto.setId(item.getId_item());
        dto.setQuantidade(item.getQuantidade());
        dto.setPrecoUnitario(item.getPrecoUunitario());
        dto.setSubtotal(item.getSubtotal());
        dto.setProdutoId(item.getProduto().getId());
        return dto;
    }

    public static ItemCompra toEntity(ItemCompraDTO dto, Produto produto) {
        ItemCompra item = new ItemCompra();
        item.setId_item(dto.getId());
        item.setQuantidade(dto.getQuantidade());
        item.setPrecoUnitario(dto.getPrecoUnitario());
        item.setSubtotal(dto.getSubtotal());
        item.setProduto(produto);
        return item;
    }
}