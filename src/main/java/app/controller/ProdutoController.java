package app.controller;

import app.dto.ProdutoDTO;
import app.mapper.ProdutoMapper;
import app.model.Produto;
import app.service.ProdutoService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@PostMapping
	public ProdutoDTO criar(@RequestBody @Valid ProdutoDTO dto) {
		Produto produto = ProdutoMapper.toEntity(dto);
		Produto salvo = produtoService.salvar(produto);
		return ProdutoMapper.toDTO(salvo);
	}

	@GetMapping
	public ResponseEntity<?> listarTodos() {
		List<Produto> produtos = produtoService.listarTodos();
		if (produtos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado ou não cadastrado");
		}
		List<ProdutoDTO> dtos = produtos.stream().map(ProdutoMapper::toDTO).toList();
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable int id) {
		Produto produto = produtoService.buscarPorId(id);
		if (produto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado ou não cadastrado");
		}
		return ResponseEntity.ok(ProdutoMapper.toDTO(produto));
	}

	/**
	 * Método atualizar estoque
	 * 
	 * @param id
	 * @param quantidadeVendida
	 * @return Retorna a quantidade atualizada para mais ou para menos
	 */
	@PutMapping("/{id}/estoque")
	public ResponseEntity<?> atualizarEstoque(@PathVariable int id, @RequestParam int quantidadeVendida) {
		boolean atualizado = produtoService.atualizarEstoque(id, quantidadeVendida);
		if (!atualizado) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado ou estoque insuficiente");
		}
		return ResponseEntity.ok("Estoque atualizado com sucesso");
	}

	/**
	 * M´rtodo atualizar o produto
	 * 
	 * @param id
	 * @param dto
	 * @return Retorna o produto atualizado
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarProduto(@PathVariable int id, @RequestBody @Valid ProdutoDTO dto) {
		Produto existente = produtoService.buscarPorId(id);
		if (existente == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado ou não cadastrado");
		}

		existente.setNome(dto.getNome());
		existente.setDescricao(dto.getDescricao());
		existente.setPreco(dto.getPreco());
		existente.setCategoria(dto.getCategoria());
		existente.setMarca(dto.getMarca());
		existente.setEstoque(dto.getEstoque());

		Produto atualizado = produtoService.salvar(existente);
		return ResponseEntity.ok(ProdutoMapper.toDTO(atualizado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable int id) {
		Produto produto = produtoService.buscarPorId(id);
		if (produto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado ou não cadastrado");
		}
		produtoService.deletar(id);
		return ResponseEntity.ok("Produto " + produto.getId() + " - " + produto.getNome() + " deletado com sucesso");
	}
}