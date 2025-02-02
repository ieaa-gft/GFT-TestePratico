package br.com.gft.pessoa.model;

import java.sql.Timestamp;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "PESSOA")
public class Pessoa extends AbstractPersistable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PESSOA")
	private Long idPessoa;

	@NotNull(message = "O campo Nome, não pode ser nulo.")
	@Column(name = "NOME")
	private String nome;

	@NotNull(message = "O campo CPF, não pode ser nulo.")
	@Column(name = "CPF", unique = true)
	private String cpf;

	@NotNull(message = "O campo Data de Nascimento, não pode ser nulo.")
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "DATA_NASCIMENTO")
	private Timestamp dtNascimento;

	@Column(name = "CEP")
	private String cep;

	@Column(name = "LOGRADOURO")
	private String Logradouro;

	@Column(name = "BAIRRO")
	private String Bairro;

	@Column(name = "UF")
	private String uf;

	@Column(name = "CIDADE")
	private String cidade;

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
		return Logradouro;
	}

	public void setLogradouro(String logradouro) {
		Logradouro = logradouro;
	}

	public String getBairro() {
		return Bairro;
	}

	public void setBairro(String bairro) {
		Bairro = bairro;
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

	@Override
	public String toString() {
		return "Pessoa [idPessoa=" + idPessoa + ", nome=" + nome + ", cpf=" + cpf + ", dtNascimento=" + dtNascimento
				+ ", cep=" + cep + ", Logradouro=" + Logradouro + ", Bairro=" + Bairro + ", uf=" + uf + ", cidade="
				+ cidade + "]";
	}

}
