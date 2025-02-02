package br.com.gft.boletos.model.request;

import java.sql.Timestamp;

import br.com.gft.boletos.model.enums.StatusBoletoEnum;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoletoRequest {

	@ApiModelProperty(value = "Codigo da pessoa.")
	@NotNull(message = "O campo Pessoa é obrigatório.")
	private Long idPessoa;

	@ApiModelProperty(value = "Valor do documento/boleto.")
	@NotNull(message = "O campo Valor do Boleto é obrigatório.")
	@Positive(message = "O campo Valor do Boleto deve ser maior que zero")
	private Double valorBoleto;

	@ApiModelProperty(value = "Data de vencimento do boleto.")
	@NotNull(message = "O campo Data de vencimento é obrigatória.")
	private Timestamp dtVencimento;

	@ApiModelProperty(value = "Valor pago.")
	private Double valorPago;

	@ApiModelProperty(value = "Data em que o boleto foi pago.")
	private Timestamp dtPagamento;

	@ApiModelProperty(value = "Status do boleto (Pendente, Pago, Vencido).")
	@NotNull(message = "O campo Status é obrigatório.")
	private StatusBoletoEnum status;

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

	public void setDtPagamento(Timestamp dataPagamento) {
		this.dtPagamento = dataPagamento;
	}

	public StatusBoletoEnum getStatus() {
		return status;
	}

	public void setStatus(StatusBoletoEnum status) {
		this.status = status;
	}

}