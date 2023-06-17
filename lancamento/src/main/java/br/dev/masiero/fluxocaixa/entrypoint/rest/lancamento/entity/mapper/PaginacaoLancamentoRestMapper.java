package br.dev.masiero.fluxocaixa.entrypoint.rest.lancamento.entity.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import br.dev.masiero.fluxocaixa.core.entity.Lancamento;
import br.dev.masiero.fluxocaixa.core.entity.Paginacao;
import br.dev.masiero.fluxocaixa.entrypoint.rest.lancamento.entity.LancamentoRest;
import br.dev.masiero.fluxocaixa.entrypoint.rest.lancamento.entity.PaginacaoRest;

@Mapper(builder = @Builder(disableBuilder = true))
public interface PaginacaoLancamentoRestMapper {

	public PaginacaoRest<LancamentoRest> toPaginacaoRest(Paginacao<Lancamento> paginacao);
	
}
