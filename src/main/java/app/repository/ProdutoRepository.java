package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	List<Produto> findByCategoria(String categoria);
    List<Produto> findByMarca(String marca);

}
