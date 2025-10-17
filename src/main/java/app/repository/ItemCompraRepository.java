package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.model.ItemCompra;

public interface ItemCompraRepository extends JpaRepository<ItemCompra, Integer>{
	List<ItemCompra> findByCompraId(int idCompra);
}
