package br.dev.masiero.fluxocaixa.dataprovider.database.saldo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.dev.masiero.fluxocaixa.core.dataprovider.SaldoDiarioDataProvider;
import br.dev.masiero.fluxocaixa.core.entity.FiltroSaldo;
import br.dev.masiero.fluxocaixa.core.entity.FiltroSaldo.TipoOrdenacao;
import br.dev.masiero.fluxocaixa.core.entity.Paginacao;
import br.dev.masiero.fluxocaixa.core.entity.SaldoDiario;
import br.dev.masiero.fluxocaixa.dataprovider.database.saldo.entity.SaldoDiarioJpa;
import br.dev.masiero.fluxocaixa.dataprovider.database.saldo.entity.mapper.SaldoDiarioJpaMapper;

public class SaldoDiarioMySQLDataProvider implements SaldoDiarioDataProvider {

	private static final SaldoDiarioJpaMapper SALDO_MAPPER = Mappers.getMapper(SaldoDiarioJpaMapper.class);
	
	private SaldoDiarioJpaRepository saldoDiarioJpaRepository;
	
	public SaldoDiarioMySQLDataProvider(SaldoDiarioJpaRepository saldoDiarioJpaRepository) {
		this.saldoDiarioJpaRepository = saldoDiarioJpaRepository;
	}
	
	@Override
	public SaldoDiario incluir(SaldoDiario saldoDiario) {
		SaldoDiarioJpa saldoDiarioJpa = SALDO_MAPPER.toSaldoDiarioJpa(saldoDiario);
		saldoDiarioJpa = this.saldoDiarioJpaRepository.save(saldoDiarioJpa);
		return SALDO_MAPPER.fromSaldoDiarioJpa(saldoDiarioJpa);
	}

	@Override
	public void excluir(LocalDate data) {
		this.saldoDiarioJpaRepository.deleteById(data);
	}

	@Override
	public SaldoDiario atualizar(SaldoDiario saldoDiario) {
		return incluir(saldoDiario);
	}

	@Override
	public SaldoDiario obter(LocalDate data) {
		Optional<SaldoDiarioJpa> lancamentoJpaOptional = this.saldoDiarioJpaRepository.findById(data);
		return SALDO_MAPPER.fromSaldoDiarioJpa(lancamentoJpaOptional.orElse(null));
	}

	@Override
	public Paginacao<SaldoDiario> buscar(FiltroSaldo filtro) {

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
		Page<SaldoDiarioJpa> pageJpa = this.saldoDiarioJpaRepository.findAll(pageable);

		return Paginacao.<SaldoDiario>builder()
				.paginaAtual(pageJpa.getNumber())
				.quantidadeTotalRegistros(pageJpa.getTotalElements())
				.registros(SALDO_MAPPER.fromSaldoDiarioJpa(pageJpa.toList()))
				.build();
				
	}

	@Override
	public boolean existe(LocalDate data) {
		return this.saldoDiarioJpaRepository.existsById(data);
	}

	@Override
	public List<SaldoDiario> recuperarTodosDesde(LocalDate data) {
		return this.saldoDiarioJpaRepository.findByDataGreaterThanEqualOrderByDataAsc(data);
	}

	@Override
	public void atualizar(List<SaldoDiario> saldoDiarioList) {
		List<SaldoDiarioJpa> saldoDiarioJpaList = SALDO_MAPPER.toSaldoDiarioJpa(saldoDiarioList);
		this.saldoDiarioJpaRepository.saveAll(saldoDiarioJpaList);
	}

}
