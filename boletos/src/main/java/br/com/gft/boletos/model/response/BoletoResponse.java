package br.com.gft.boletos.model.response;

import java.sql.Timestamp;
import java.util.Objects;

import br.com.gft.boletos.model.enums.StatusBoletoEnum;

public class BoletoResponse {

	private Long idBoleto;

	private Long idPessoa;

	private Double valorBoleto;

	private Timestamp dtVencimento;

	private Double valorPago;

	private Timestamp dtPagamento;

	private StatusBoletoEnum status;

	public BoletoResponse() {
		super();
	}

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

	@Override
	public String toString() {
		return "BoletoResponse [idBoleto=" + idBoleto + ", idPessoa=" + idPessoa + ", valorBoleto=" + valorBoleto
				+ ", dtVencimento=" + dtVencimento + ", valorPago=" + valorPago + ", dtPagamento=" + dtPagamento
				+ ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dtPagamento, dtVencimento, idBoleto, idPessoa, status, valorBoleto, valorPago);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoletoResponse other = (BoletoResponse) obj;
		return Objects.equals(dtPagamento, other.dtPagamento) && Objects.equals(dtVencimento, other.dtVencimento)
				&& Objects.equals(idBoleto, other.idBoleto) && Objects.equals(idPessoa, other.idPessoa)
				&& status == other.status && Objects.equals(valorBoleto, other.valorBoleto)
				&& Objects.equals(valorPago, other.valorPago);
	}

}