package app.mapper;

import app.dto.ProdutoDTO;
import app.model.Produto;

public class ProdutoMapper {

    public static ProdutoDTO toDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        dto.setCategoria(produto.getCategoria());
        dto.setMarca(produto.getMarca());
        dto.setEstoque(produto.getEstoque());
        return dto;
    }

    public static Produto toEntity(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setId_produto(dto.getId());
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setCategoria(dto.getCategoria());
        produto.setMarca(dto.getMarca());
        produto.setEstoque(dto.getEstoque());
        return produto;
    }
}