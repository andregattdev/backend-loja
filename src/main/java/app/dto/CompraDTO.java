package app.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class CompraDTO {
	private int id;
	private LocalDate dataCompra;
	private float valorTotal;
	private String status;
	private int clienteId;
	private List<ItemCompraDTO> itens;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public List<ItemCompraDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItemCompraDTO> itens) {
		this.itens = itens;
	}

}
