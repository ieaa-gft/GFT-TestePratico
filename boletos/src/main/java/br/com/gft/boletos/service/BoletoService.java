package br.com.gft.boletos.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gft.boletos.model.Boleto;
import br.com.gft.boletos.model.enums.StatusBoletoEnum;
import br.com.gft.boletos.model.request.BoletoRequest;
import br.com.gft.boletos.model.response.BoletoResponse;
import br.com.gft.boletos.repository.BoletoRepository;

@Service
@Transactional
public class BoletoService {

	@Autowired
	private BoletoRepository repository;

	@Transactional
	public BoletoResponse incluirBoleto(BoletoRequest boletoRequest) throws Exception {
		try {
			this.validarInclusaoBoleto(boletoRequest);
			Boleto boleto = this.convertToEntity(boletoRequest);
			return this.convertToResponse(repository.save(boleto));
		} catch (Exception e) {
			throw new Exception("Erro ao tentar criar um Boleto: " + e.getMessage());
		}
	}

	public List<BoletoResponse> buscarTodosBoletosPorPessoa(Long idPessoa) {
		List<Boleto> listaBoleto = repository.findByIdPessoaOrderByDtVencimento(idPessoa).orElseThrow(
				() -> new RuntimeException("Não foram encontrados boletos para a pessoa informada: " + idPessoa));
		return listaBoleto.stream().map(this::convertToResponse).collect(Collectors.toList());
	}

	public BoletoResponse buscarBoletoPorId(Long id) throws Exception {
		Boleto boleto = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Este Boleto não foi encontrado ou não existe."));
		return this.convertToResponse(boleto);
	}

	@Transactional
	public BoletoResponse pagarBoleto(Long id, Double valorPago) throws Exception {
		try {
			Boleto boleto = repository.findById(id)
					.orElseThrow(() -> new Exception("O Boleto (" + id + ") não foi encontrado ou não existe."));
			this.validarBoletoParaPagamento(boleto, valorPago);
			boleto.setValorPago(valorPago);
			boleto.setDtPagamento(new Timestamp(System.currentTimeMillis()));
			return this.convertToResponse(repository.save(boleto));
		} catch (Exception e) {
			throw new Exception("Erro ao tentar Pagar o Boleto: " + e.getMessage());
		}
	}

	@Transactional
	public void excluirBoleto(Long id) throws Exception {
		try {
			Boleto boleto = repository.findById(id)
					.orElseThrow(() -> new Exception("O Boleto (" + id + ") não foi encontrado ou não existe."));

			if (boleto.getStatus().equals(StatusBoletoEnum.PAGO)) {
				throw new Exception("Não é possível excluir um boleto com status PAGO.");
			}

			repository.delete(boleto);

		} catch (Exception e) {
			throw new Exception("Erro ao tentar excluir o Boleto: " + e.getMessage());
		}
	}

	private void validarInclusaoBoleto(BoletoRequest boletoRequest) throws Exception {
		if (boletoRequest.getDtVencimento().before(new Timestamp(System.currentTimeMillis())))
			throw new RuntimeException("Data de Vencimento deve ser posterior a data atual.");
		if (boletoRequest.getValorBoleto().doubleValue() < 0.0)
			throw new RuntimeException("Valor do Boleto deve ser maior que zero.");
	}

	private void validarBoletoParaPagamento(Boleto boleto, Double valorPago) throws Exception {
		if (!boleto.getStatus().equals(StatusBoletoEnum.PENDENTE))
			throw new Exception("Só é permitido pagar um Boleto que esteja com Status de PENDENTE.");
		if (boleto.getValorBoleto() != valorPago)
			throw new Exception("O Valor a ser Pago, deve ser o mesmo Valor do Boleto.");
	}

	private Boleto convertToEntity(BoletoRequest boletoRequest) {
		Boleto boleto = new Boleto();
		boleto.setIdPessoa(boletoRequest.getIdPessoa());
		boleto.setValorBoleto(boletoRequest.getValorBoleto());
		boleto.setDtVencimento(boletoRequest.getDtVencimento());
		boleto.setValorPago(boletoRequest.getValorPago());
		boleto.setDtPagamento(boletoRequest.getDtPagamento());
		boleto.setStatus(boletoRequest.getStatus());
		return boleto;
	}

	private BoletoResponse convertToResponse(Boleto boleto) {
		BoletoResponse boletoResource = new BoletoResponse();
		boletoResource.setIdBoleto(boleto.getIdBoleto());
		boletoResource.setIdPessoa(boleto.getIdPessoa());
		boletoResource.setValorBoleto(boleto.getValorBoleto());
		boletoResource.setDtVencimento(boleto.getDtVencimento());
		boletoResource.setValorPago(boleto.getValorPago());
		boletoResource.setDtPagamento(boleto.getDtPagamento());
		boletoResource.setStatus(boleto.getStatus());
		return boletoResource;
	}
}
