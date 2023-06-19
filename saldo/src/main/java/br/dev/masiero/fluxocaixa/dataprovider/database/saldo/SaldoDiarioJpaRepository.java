package br.dev.masiero.fluxocaixa.dataprovider.database.saldo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.dev.masiero.fluxocaixa.dataprovider.database.saldo.entity.SaldoDiarioJpa;

public interface SaldoDiarioJpaRepository extends JpaRepository<SaldoDiarioJpa, LocalDate>{

	List<SaldoDiarioJpa> findByDataGreaterThanEqualOrderByDataAsc(LocalDate data);
	
	SaldoDiarioJpa findFirstByDataLessThanOrderByDataDesc(LocalDate data);
	
	@Query("SELECT s FROM SaldoDiarioJpa s WHERE (:dataInicial IS NULL OR s.data>= :dataInicial) AND (:dataFinal IS NULL OR s.data <= :dataFinal)")
	Page<SaldoDiarioJpa> queryByFilter(@Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal, Pageable pageable);
}
