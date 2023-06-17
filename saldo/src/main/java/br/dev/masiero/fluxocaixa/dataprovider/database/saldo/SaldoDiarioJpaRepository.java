package br.dev.masiero.fluxocaixa.dataprovider.database.saldo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.masiero.fluxocaixa.core.entity.SaldoDiario;
import br.dev.masiero.fluxocaixa.dataprovider.database.saldo.entity.SaldoDiarioJpa;

public interface SaldoDiarioJpaRepository extends JpaRepository<SaldoDiarioJpa, LocalDate>{

	List<SaldoDiario> findByDataGreaterThanEqualOrderByDataAsc(LocalDate data);
	
}
