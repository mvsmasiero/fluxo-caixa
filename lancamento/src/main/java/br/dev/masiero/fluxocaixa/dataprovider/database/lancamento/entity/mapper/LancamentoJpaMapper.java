package br.dev.masiero.fluxocaixa.dataprovider.database.lancamento.entity.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.dev.masiero.fluxocaixa.core.entity.Lancamento;
import br.dev.masiero.fluxocaixa.dataprovider.database.lancamento.entity.LancamentoJpa;

@Mapper
public interface LancamentoJpaMapper {

	public LancamentoJpa toLancamentoJpa(Lancamento lancamento);
	
	public Lancamento fromLancamentoJpa(LancamentoJpa lancamentoJpa);
	
	public List<Lancamento> fromLancamentoJpa(List<LancamentoJpa> lancamentoJpaList);
}
