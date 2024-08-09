package br.com.gft.pessoa.model.response;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PessoaResponse {

	private Long idPessoa;

	private String nome;

	private String cpf;

	private Timestamp dtNascimento;

	private String cep;

	private String logradouro;

	private String bairro;

	private String uf;

	private String cidade;

	private List<BoletoResponse> listBoletos;

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
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

	public List<BoletoResponse> getListBoletos() {
		return listBoletos;
	}

	public void setListBoletos(List<BoletoResponse> listBoletos) {
		this.listBoletos = listBoletos;
	}

}
