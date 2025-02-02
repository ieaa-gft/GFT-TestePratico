package br.com.gft.boletos.model;

import java.sql.Timestamp;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.gft.boletos.model.enums.StatusBoletoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "BOLETO")
public class Boleto extends AbstractPersistable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_BOLETO")
	private Long idBoleto;

	@NotNull(message = "O campo Pessoa, não pode ser nulo")
	@Column(name = "ID_PESSOA")
	private Long idPessoa;

	@NotNull(message = "O campo Valor do Documento/Boleto, não pode ser nulo")
	@DecimalMin(value = "0.01", inclusive = false, message = "O campo Valor do Documento/Boleto, deve ser maior que 0")
	@Column(name = "VALOR_BOLETO")
	private Double valorBoleto;

	@NotNull(message = "O campo Data de Vencimento, não pode ser nulo")
	@Column(name = "DATA_VENCIMENTO")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Timestamp dtVencimento;

	@Column(name = "VALOR_PAGO")
	private Double valorPago;

	@Column(name = "DATA_PAGAMENTO")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Timestamp dtPagamento;

	@NotNull
	@Column(name = "STATUS", columnDefinition = "integer default 0")
	private Integer status;

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
		return StatusBoletoEnum.buscarStatusPorCodigo(this.status);
	}

	public void setStatus(StatusBoletoEnum status) {
		this.status = status.statusCode;
	}
}
