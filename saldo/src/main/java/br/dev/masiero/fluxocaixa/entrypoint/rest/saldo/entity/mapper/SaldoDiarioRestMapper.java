package br.dev.masiero.fluxocaixa.entrypoint.rest.saldo.entity.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.dev.masiero.fluxocaixa.core.entity.SaldoDiario;
import br.dev.masiero.fluxocaixa.entrypoint.rest.saldo.entity.SaldoDiarioRest;

@Mapper
public interface SaldoDiarioRestMapper {

	public SaldoDiarioRest toSaldoDiarioRest(SaldoDiario saldoDiario);

	public SaldoDiario fromSaldoDarioRest(SaldoDiarioRest saldoDiarioRest);

	public List<SaldoDiario> fromSaldoDiarioRest(List<SaldoDiarioRest> saldoDiarioRestList);

}
