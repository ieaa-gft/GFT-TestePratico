package br.com.gft.pessoa.controller;

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

import br.com.gft.pessoa.model.request.PessoaRequest;
import br.com.gft.pessoa.model.response.PessoaResponse;
import br.com.gft.pessoa.service.PessoaService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@ApiOperation(value = "Incluir pessoas.")
	@PostMapping
	public ResponseEntity<PessoaResponse> incluirPessoa(@Valid @RequestBody PessoaRequest pessoaRequest)
			throws Exception {
		return new ResponseEntity<>(pessoaService.incluirPessoa(pessoaRequest), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Buscar lista de pessoas.")
	@GetMapping
	public ResponseEntity<List<PessoaResponse>> buscarTodas() {
		return ResponseEntity.ok(pessoaService.buscarTodasPessoas());
	}

	@ApiOperation(value = "Buscar pessoa pelo código de ID.")
	@GetMapping("/{idPessoa}")
	public ResponseEntity<PessoaResponse> buscarPorId(@PathVariable("idPessoa") Long idPessoa) throws Exception {
		return ResponseEntity.ok(pessoaService.buscarPessoaPorId(idPessoa));
	}

	@ApiOperation(value = "Buscar pessoa pelo CPF.")
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<PessoaResponse> buscarPorCpf(@PathVariable("cpf") String cpf) throws Exception {
		return ResponseEntity.ok(pessoaService.buscarPessoaPorCpf(cpf));
	}

	@ApiOperation(value = "Alterar dados de uma pessoa.")
	@PutMapping("/{idPessoa}")
	public ResponseEntity<PessoaResponse> alterarPessoa(@PathVariable("idPessoa") Long idPessoa,
			@Valid @RequestBody PessoaRequest pessoaResquest) throws Exception {
		return new ResponseEntity<>(pessoaService.alterarPessoa(idPessoa, pessoaResquest), HttpStatus.OK);
	}

	@ApiOperation(value = "Deletar pessoa.")
	@DeleteMapping("/{idPessoa}")
	public ResponseEntity<Void> deletePerson(@PathVariable("idPessoa") Long idPessoa) throws Exception {
		pessoaService.excluirPessoa(idPessoa);
		return ResponseEntity.noContent().build();
	}

}