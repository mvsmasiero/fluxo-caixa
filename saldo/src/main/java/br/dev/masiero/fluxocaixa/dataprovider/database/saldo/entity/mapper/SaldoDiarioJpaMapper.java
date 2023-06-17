package br.dev.masiero.fluxocaixa.dataprovider.database.saldo.entity.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.dev.masiero.fluxocaixa.core.entity.SaldoDiario;
import br.dev.masiero.fluxocaixa.dataprovider.database.saldo.entity.SaldoDiarioJpa;

@Mapper
public interface SaldoDiarioJpaMapper {

	public SaldoDiarioJpa toSaldoDiarioJpa(SaldoDiario saldoDiario);
	
	public SaldoDiario fromSaldoDiarioJpa(SaldoDiarioJpa saldoDiarioJpa);
	
	public List<SaldoDiarioJpa> toSaldoDiarioJpa(List<SaldoDiario> saldoDiarioList);

	public List<SaldoDiario> fromSaldoDiarioJpa(List<SaldoDiarioJpa> saldoDiarioJpaList);
}
