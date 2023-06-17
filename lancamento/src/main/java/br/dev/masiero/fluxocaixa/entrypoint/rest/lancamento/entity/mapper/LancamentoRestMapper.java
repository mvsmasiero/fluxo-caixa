package br.dev.masiero.fluxocaixa.entrypoint.rest.lancamento.entity.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.dev.masiero.fluxocaixa.core.entity.Lancamento;
import br.dev.masiero.fluxocaixa.entrypoint.rest.lancamento.entity.LancamentoRest;

@Mapper
public interface LancamentoRestMapper {

	public LancamentoRest toLancamentoRest(Lancamento lancamento);
	
	public Lancamento fromLancamentoRest(LancamentoRest lancamentoRest);
	
	public List<Lancamento> fromLancamentoRest(List<LancamentoRest> lancamentoRestList);	
	
}
