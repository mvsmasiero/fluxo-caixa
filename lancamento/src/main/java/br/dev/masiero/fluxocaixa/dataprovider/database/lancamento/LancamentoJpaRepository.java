package br.dev.masiero.fluxocaixa.dataprovider.database.lancamento;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.masiero.fluxocaixa.dataprovider.database.lancamento.entity.LancamentoJpa;

public interface LancamentoJpaRepository extends JpaRepository<LancamentoJpa, Long>{

}
