package br.dev.masiero.fluxocaixa.entrypoint.rest.saldo.entity.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import br.dev.masiero.fluxocaixa.core.entity.Paginacao;
import br.dev.masiero.fluxocaixa.core.entity.SaldoDiario;
import br.dev.masiero.fluxocaixa.entrypoint.rest.saldo.entity.PaginacaoRest;
import br.dev.masiero.fluxocaixa.entrypoint.rest.saldo.entity.SaldoDiarioRest;

@Mapper(builder = @Builder(disableBuilder = true))
public interface PaginacaoSaldoDiarioRestMapper {

	public PaginacaoRest<SaldoDiarioRest> toPaginacaoRest(Paginacao<SaldoDiario> paginacao);
	
}
