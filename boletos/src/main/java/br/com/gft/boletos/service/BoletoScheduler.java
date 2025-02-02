package br.com.gft.boletos.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.gft.boletos.model.Boleto;
import br.com.gft.boletos.model.enums.StatusBoletoEnum;
import br.com.gft.boletos.repository.BoletoRepository;

@Service
public class BoletoScheduler {

	@Autowired
	private BoletoRepository boletoRepository;

	@Scheduled(cron = "0 0 1 * * ?") // Diariamente às 01:00
	public void atualizarBoletosVencidos() {
		List<Boleto> boletosVencidos = boletoRepository.findByStatusAndDtVencimentoLessThan(
				StatusBoletoEnum.PENDENTE.statusCode, new Timestamp(System.currentTimeMillis()));
		
		boletosVencidos.stream().forEach(boleto -> boleto.setStatus(StatusBoletoEnum.VENCIDO));
	}
}
