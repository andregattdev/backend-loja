package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{
	List<Endereco> findByClienteId(int idCliente);

}
