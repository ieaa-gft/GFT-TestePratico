package br.com.gft.pessoa.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gft.pessoa.model.Pessoa;
import br.com.gft.pessoa.model.enums.StatusBoletoEnum;
import br.com.gft.pessoa.model.request.PessoaRequest;
import br.com.gft.pessoa.model.response.BoletoResponse;
import br.com.gft.pessoa.model.response.PessoaResponse;
import br.com.gft.pessoa.repository.BoletoRepository;
import br.com.gft.pessoa.repository.PessoaRepository;

@Service
@Transactional
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	@Autowired
	private BoletoRepository boletoRepository;

	public List<PessoaResponse> buscarTodasPessoas() {
		List<Pessoa> listPessoa = repository.findAll();
		return listPessoa.stream().map(this::convertToResponse).collect(Collectors.toList());
	}

	public PessoaResponse buscarPessoaPorId(Long idPessoa) throws Exception {
		PessoaResponse pessoaResponse = this.convertToResponse(repository.findById(idPessoa)
				.orElseThrow(() -> new RuntimeException("A Pessoa não encontrada: ID " + idPessoa)));
		pessoaResponse.setListBoletos(boletoRepository.buscarBoletosPorPessoa(idPessoa));
		return pessoaResponse;
	}

	public PessoaResponse buscarPessoaPorCpf(String cpf) throws Exception {
		return this.convertToResponse(repository.findByCpf(cpf)
				.orElseThrow(() -> new RuntimeException("A Pessoa não encontrada: CPF " + cpf)));
	}

	@Transactional
	public PessoaResponse incluirPessoa(PessoaRequest pessoaRequest) throws Exception {
		try {
			this.validarInclusaoPessoa(pessoaRequest);
			Pessoa pessoa = this.convertToEntity(pessoaRequest);
			return this.convertToResponse(repository.save(pessoa));
		} catch (Exception e) {
			throw new Exception("Erro ao tentar incluir Pessoa: " + e.getMessage());
		}
	}

	@Transactional
	public PessoaResponse alterarPessoa(Long idPessoa, PessoaRequest pessoaRequest) throws Exception {
		try {
			Pessoa pessoa = repository.findById(idPessoa)
					.orElseThrow(() -> new Exception("A Pessoa (" + idPessoa + ") não foi encontrado ou não existe."));
			pessoa.setNome(pessoaRequest.getNome());
			pessoa.setCpf(pessoaRequest.getCpf());
			pessoa.setDtNascimento(pessoaRequest.getDtNascimento());
			pessoa.setCep(pessoaRequest.getCep());
			pessoa.setLogradouro(pessoaRequest.getLogradouro());
			pessoa.setBairro(pessoaRequest.getBairro());
			pessoa.setCidade(pessoaRequest.getCidade());
			pessoa.setUf(pessoaRequest.getUf());
			return this.convertToResponse(repository.saveAndFlush(pessoa));
		} catch (Exception ex) {
			throw new Exception("Erro ao tentar alterar dados dw Pessoa: " + ex.getMessage());
		}
	}

	@Transactional
	public void excluirPessoa(Long idPessoa) throws Exception {
		try {
			Pessoa pessoa = repository.findById(idPessoa)
					.orElseThrow(() -> new Exception("A Pessoa (" + idPessoa + ") não foi encontrado ou não existe."));
			this.validarExclusaoPessoa(pessoa.getIdPessoa());
			repository.delete(pessoa);
		} catch (Exception ex) {
			throw new Exception("Erro ao tentar excluir dados de Pessoa: " + ex.getMessage());
		}
	}

	private void validarInclusaoPessoa(PessoaRequest pessoaRequest) {
		if (repository.existsByCpf(pessoaRequest.getCpf()))
			throw new RuntimeException("CPF já cadastrado.");
		if (this.isMenorDeIdade(pessoaRequest.getDtNascimento()))
			throw new RuntimeException("A Pessoa cadastrada deve ter 18 anos ou mais.");
	}

	private void validarExclusaoPessoa(Long idPessoa) {
		List<BoletoResponse> listBoletos = boletoRepository.buscarBoletosPorPessoa(idPessoa);
		if (!listBoletos.isEmpty()) {
			for (BoletoResponse boleto : listBoletos) {
				if (boleto.getStatus().equals(StatusBoletoEnum.PENDENTE))
					throw new RuntimeException("Não é possivel excluir Pessoa que tenham boleto(s) pendente(s.");
			}
		}
	}

	private Boolean isMenorDeIdade(Timestamp dtNascimento) {
		Timestamp dtAtual = new Timestamp(System.currentTimeMillis());
		return (dtAtual.getYear() - dtNascimento.getYear()) <= 18;
	}

	private Pessoa convertToEntity(PessoaRequest pessoaRequest) {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(pessoaRequest.getNome());
		pessoa.setCpf(pessoaRequest.getCpf());
		pessoa.setDtNascimento(pessoaRequest.getDtNascimento());
		pessoa.setCep(pessoaRequest.getCep());
		pessoa.setLogradouro(pessoaRequest.getLogradouro());
		pessoa.setBairro(pessoaRequest.getBairro());
		pessoa.setCidade(pessoaRequest.getCidade());
		pessoa.setUf(pessoaRequest.getUf());
		return pessoa;
	}

	private PessoaResponse convertToResponse(Pessoa pessoa) {
		PessoaResponse pessoaResource = new PessoaResponse();
		pessoaResource.setIdPessoa(pessoa.getIdPessoa());
		pessoaResource.setNome(pessoa.getNome());
		pessoaResource.setCpf(pessoa.getCpf());
		pessoaResource.setDtNascimento(pessoa.getDtNascimento());
		pessoaResource.setCep(pessoa.getCep());
		pessoaResource.setLogradouro(pessoa.getLogradouro());
		pessoaResource.setBairro(pessoa.getBairro());
		pessoaResource.setCidade(pessoa.getCidade());
		pessoaResource.setUf(pessoa.getUf());
		return pessoaResource;
	}
}
