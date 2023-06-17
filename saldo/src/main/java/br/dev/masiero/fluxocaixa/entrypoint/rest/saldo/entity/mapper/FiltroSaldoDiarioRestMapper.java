package br.dev.masiero.fluxocaixa.entrypoint.rest.saldo.entity.mapper;

import org.mapstruct.Mapper;

import br.dev.masiero.fluxocaixa.core.entity.FiltroSaldo;
import br.dev.masiero.fluxocaixa.entrypoint.rest.saldo.entity.FiltroSaldoDiarioRest;

@Mapper
public interface FiltroSaldoDiarioRestMapper {

	public FiltroSaldo fromFiltroLancamentoRest(FiltroSaldoDiarioRest filtroRest);
	
}
