package app.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

public class ClienteDTO {

	private int id;

	@NotBlank(message = "O nome é obrigatorio")
	private String nome;
	
	@NotBlank(message = "O email é obrigatorio")
	private String email;
	
	@NotBlank(message = "O telefone é obrigatorio")
	private String telefone;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Past(message = "Data de nascimento deve ser no passado")
	private Date dataNascimento;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
