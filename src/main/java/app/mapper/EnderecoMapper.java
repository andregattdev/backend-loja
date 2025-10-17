package app.mapper;

import app.dto.EnderecoDTO;
import app.model.Endereco;
import app.model.Cliente;

public class EnderecoMapper {

    public static EnderecoDTO toDTO(Endereco endereco) {
        EnderecoDTO dto = new EnderecoDTO();
        dto.setId(endereco.getId_endereco());
        dto.setRua(endereco.getRua());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());
        dto.setBairro(endereco.getBairro());
        dto.setCidade(endereco.getCidade());
        dto.setEstado(endereco.getEstado());
        dto.setCep(endereco.getCep());
        dto.setClienteId(endereco.getCliente().getId());
        return dto;
    }

    public static Endereco toEntity(EnderecoDTO dto, Cliente cliente) {
        Endereco endereco = new Endereco();
        endereco.setId_endereco(dto.getId());
        endereco.setRua(dto.getRua());
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setCep(dto.getCep());
        endereco.setCliente(cliente);
        return endereco;
    }
}