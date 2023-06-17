package br.dev.masiero.fluxocaixa.dataprovider.database.lancamento;

import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.dev.masiero.fluxocaixa.core.dataprovider.LancamentoDataProvider;
import br.dev.masiero.fluxocaixa.core.entity.FiltroLancamento;
import br.dev.masiero.fluxocaixa.core.entity.Lancamento;
import br.dev.masiero.fluxocaixa.core.entity.Paginacao;
import br.dev.masiero.fluxocaixa.core.entity.FiltroLancamento.TipoOrdenacao;
import br.dev.masiero.fluxocaixa.dataprovider.database.lancamento.entity.LancamentoJpa;
import br.dev.masiero.fluxocaixa.dataprovider.database.lancamento.entity.mapper.LancamentoJpaMapper;

public class LancamentoMySQLDataProvider implements LancamentoDataProvider {

	private static final LancamentoJpaMapper LANCAMENTO_MAPPER = Mappers.getMapper(LancamentoJpaMapper.class);
	
	private LancamentoJpaRepository lancamentoJpaRepository;
	
	public LancamentoMySQLDataProvider(LancamentoJpaRepository lancamentoJpaRepository) {
		this.lancamentoJpaRepository = lancamentoJpaRepository;
	}
	
	@Override
	public Lancamento incluir(Lancamento lancamento) {
		LancamentoJpa lancamentoJpa = LANCAMENTO_MAPPER.toLancamentoJpa(lancamento);
		lancamentoJpa = this.lancamentoJpaRepository.save(lancamentoJpa);
		return LANCAMENTO_MAPPER.fromLancamentoJpa(lancamentoJpa);
	}

	@Override
	public void excluir(long id) {
		this.lancamentoJpaRepository.deleteById(id);
	}

	@Override
	public Lancamento atualizar(Lancamento lancamento) {
		return incluir(lancamento);
	}

	@Override
	public Lancamento visualizar(long id) {
		Optional<LancamentoJpa> lancamentoJpaOptional = this.lancamentoJpaRepository.findById(id);
		return LANCAMENTO_MAPPER.fromLancamentoJpa(lancamentoJpaOptional.orElse(null));
	}

	@Override
	public Paginacao<Lancamento> buscar(FiltroLancamento filtro) {

		// Por enquanto só há ordenação pelo campo data.
		String nomeCampo = "data";
		
		Sort sort = Sort.by(nomeCampo);
		
		if (TipoOrdenacao.ASC == filtro.getTipoOrdenacao()) {
			sort = sort.ascending();
		} else {
			sort = sort.descending();
		}
		
		PageRequest.of(filtro.getPagina(), filtro.getQuantidadeRegistrosPagina(), sort);
		Pageable pageable = Pageable.ofSize(filtro.getQuantidadeRegistrosPagina());
		Page<LancamentoJpa> pageJpa = this.lancamentoJpaRepository.findAll(pageable);

		return Paginacao.<Lancamento>builder()
				.paginaAtual(pageJpa.getNumber())
				.quantidadeTotalRegistros(pageJpa.getTotalElements())
				.registros(LANCAMENTO_MAPPER.fromLancamentoJpa(pageJpa.toList()))
				.build();
				
	}

	@Override
	public boolean existe(long id) {
		return this.lancamentoJpaRepository.existsById(id);
	}

}
