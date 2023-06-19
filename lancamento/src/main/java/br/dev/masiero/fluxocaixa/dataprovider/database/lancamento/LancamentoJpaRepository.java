package br.dev.masiero.fluxocaixa.dataprovider.database.lancamento;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.dev.masiero.fluxocaixa.dataprovider.database.lancamento.entity.LancamentoJpa;

public interface LancamentoJpaRepository extends JpaRepository<LancamentoJpa, Long>{

	@Query("SELECT l FROM LancamentoJpa l WHERE (:dataInicial IS NULL OR l.data>= :dataInicial) AND (:dataFinal IS NULL OR l.data <= :dataFinal)")
	Page<LancamentoJpa> queryByFilter(@Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal, Pageable pageable);
	
}
