package app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Integer>{
	List<Compra> findByCliente_Id(int id);
	
    List<Compra> findByItens_Produto_Id(int idProduto);

	List<Compra> findByDataCompraBetween(LocalDate inicio, LocalDate fim);


}
