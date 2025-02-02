package br.com.gft.boletos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gft.boletos.model.request.BoletoRequest;
import br.com.gft.boletos.model.response.BoletoResponse;
import br.com.gft.boletos.service.BoletoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/boletos")
public class BoletoController {

	@Autowired
	private BoletoService boletoService;

	@PostMapping
	public ResponseEntity<BoletoResponse> incluirBoleto(@Valid @RequestBody BoletoRequest boletoRequest)
			throws Exception {
		return new ResponseEntity<>(boletoService.incluirBoleto(boletoRequest), HttpStatus.CREATED);
	}

	@GetMapping("/pessoa/{idPessoa}")
	public ResponseEntity<List<BoletoResponse>> buscarBoletosPorPessoa(@PathVariable Long idPessoa) throws Exception {
		return ResponseEntity.ok(boletoService.buscarTodosBoletosPorPessoa(idPessoa));
	}

	@GetMapping("/boleto/{idBoleto}")
	public ResponseEntity<BoletoResponse> buscarPorId(@PathVariable("idBoleto") Long idBoleto) throws Exception {
		return ResponseEntity.ok(boletoService.buscarBoletoPorId(idBoleto));
	}

	@PutMapping("/boleto/{idBoleto}/pagar")
	public ResponseEntity<BoletoResponse> pagarBoleto(@PathVariable("idBoleto") Long idBoleto,
			@RequestBody Double valorPago) throws Exception {
		return ResponseEntity.ok(boletoService.pagarBoleto(idBoleto, valorPago));
	}

	@DeleteMapping("/boleto/{idBoleto}")
	public ResponseEntity<Void> deletePerson(@PathVariable("idBoleto") Long idBoleto) throws Exception {
		boletoService.excluirBoleto(idBoleto);
		return ResponseEntity.noContent().build();
	}

}
