package br.com.gft.pessoa.model.request;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PessoaRequest {

	@ApiModelProperty(value = "Nome completo.")
	@NotBlank(message = "O campo 'Nome' não pode ser nulo.")
	private String nome;

	@ApiModelProperty(value = "CPF da Pessoa. Deve conter apenas números.")
	@NotBlank(message = "O campo 'CPF' não pode ser nulo.")
	private String cpf;

	@ApiModelProperty(value = "Data de Nascimento.")
	@NotBlank(message = "O campo 'Data de Nascimento' não pode ser nulo.")
	private Timestamp dtNascimento;

	@ApiModelProperty(value = "CEP residencial.")
	private String cep;

	@ApiModelProperty(value = "Logradouro residencial.")
	private String logradouro;

	@ApiModelProperty(value = "Bairro residencial.")
	private String bairro;

	@ApiModelProperty(value = "Estado residencial.")
	private String uf;

	@ApiModelProperty(value = "Cidade residencial.")
	private String cidade;

	public PessoaRequest() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Timestamp getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Timestamp dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}
