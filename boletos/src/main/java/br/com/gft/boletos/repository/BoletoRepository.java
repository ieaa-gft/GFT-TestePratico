package br.com.gft.boletos.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gft.boletos.model.Boleto;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Long> {

	Optional<List<Boleto>> findByIdPessoaOrderByDtVencimento(Long idPessoa);

	List<Boleto> findByStatusAndDtVencimentoLessThan(Integer status, Timestamp dtVencimento);

}