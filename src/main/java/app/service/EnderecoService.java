package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Endereco;
import app.repository.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> listarPorCliente(int idCliente) {
        return enderecoRepository.findByClienteId(idCliente);
    }

    public void deletar(int id) {
        enderecoRepository.deleteById(id);
    }
}
