package app.mapper;

import app.dto.CompraDTO;
import app.dto.ItemCompraDTO;
import app.model.Compra;
import app.model.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class CompraMapper {

    public static CompraDTO toDTO(Compra compra) {
        CompraDTO dto = new CompraDTO();
        dto.setId(compra.getId());
        dto.setDataCompra(compra.getDataCompra());
        dto.setValorTotal(compra.getValorTotal());
        dto.setStatus(compra.getStatus());
        dto.setClienteId(compra.getCliente().getId());
        dto.setItens(compra.getItens().stream()
                .map(ItemCompraMapper::toDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    public static Compra toEntity(CompraDTO dto, Cliente cliente) {
        Compra compra = new Compra();
        compra.setId(dto.getId());
        compra.setDataCompra(dto.getDataCompra());
        compra.setValorTotal(dto.getValorTotal());
        compra.setStatus(dto.getStatus());
        compra.setCliente(cliente);
        // Os itens são adicionados separadamente
        return compra;
    }
}