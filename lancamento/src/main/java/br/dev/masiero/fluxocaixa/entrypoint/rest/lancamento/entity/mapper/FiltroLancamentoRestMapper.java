package br.dev.masiero.fluxocaixa.entrypoint.rest.lancamento.entity.mapper;

import org.mapstruct.Mapper;

import br.dev.masiero.fluxocaixa.core.entity.FiltroLancamento;
import br.dev.masiero.fluxocaixa.entrypoint.rest.lancamento.entity.FiltroLancamentoRest;

@Mapper
public interface FiltroLancamentoRestMapper {

	public FiltroLancamento fromFiltroLancamentoRest(FiltroLancamentoRest filtroRest);
	
}
