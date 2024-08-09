package br.com.gft.pessoa.repository;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gft.pessoa.model.response.BoletoResponse;

@FeignClient(name = "boleto", url = "http://localhost:8081/api/boletos")
@Validated
public interface BoletoRepository {

	@RequestMapping("/pessoa/{idPessoa}")
	List<BoletoResponse> buscarBoletosPorPessoa(@PathVariable Long idPessoa);

}
