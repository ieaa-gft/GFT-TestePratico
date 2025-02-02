package br.com.gft.pessoa.model.response;

import java.sql.Timestamp;

import br.com.gft.pessoa.model.enums.StatusBoletoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoletoResponse {
	
	private Long idBoleto;

	private Long idPessoa;

	private Double valorBoleto;

	private Timestamp dtVencimento;

	private Double valorPago;

	private Timestamp dtPagamento;

	private StatusBoletoEnum status;

	public Long getIdBoleto() {
		return idBoleto;
	}

	public void setIdBoleto(Long idBoleto) {
		this.idBoleto = idBoleto;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Double getValorBoleto() {
		return valorBoleto;
	}

	public void setValorBoleto(Double valorBoleto) {
		this.valorBoleto = valorBoleto;
	}

	public Timestamp getDtVencimento() {
		return dtVencimento;
	}

	public void setDtVencimento(Timestamp dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public Timestamp getDtPagamento() {
		return dtPagamento;
	}

	public void setDtPagamento(Timestamp dtPagamento) {
		this.dtPagamento = dtPagamento;
	}

	public StatusBoletoEnum getStatus() {
		return status;
	}

	public void setStatus(StatusBoletoEnum status) {
		this.status = status;
	}

}
