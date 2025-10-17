package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Cliente;
import app.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public List<Cliente> listarTodos() {
		return clienteRepository.findAll();
	}

	public Cliente buscarPorId(int id) {
		return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
	}

	public void deletar(int id) {
		clienteRepository.deleteById(id);
	}

}
