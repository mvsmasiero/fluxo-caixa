package br.dev.masiero.fluxocaixa.entrypoint.rest.saldo;

import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.masiero.fluxocaixa.core.entity.FiltroSaldo;
import br.dev.masiero.fluxocaixa.core.entity.Paginacao;
import br.dev.masiero.fluxocaixa.core.entity.SaldoDiario;
import br.dev.masiero.fluxocaixa.core.usecase.BuscarSaldoDiarioUseCase;
import br.dev.masiero.fluxocaixa.entrypoint.rest.saldo.entity.FiltroSaldoDiarioRest;
import br.dev.masiero.fluxocaixa.entrypoint.rest.saldo.entity.PaginacaoRest;
import br.dev.masiero.fluxocaixa.entrypoint.rest.saldo.entity.SaldoDiarioRest;
import br.dev.masiero.fluxocaixa.entrypoint.rest.saldo.entity.mapper.FiltroSaldoDiarioRestMapper;
import br.dev.masiero.fluxocaixa.entrypoint.rest.saldo.entity.mapper.PaginacaoSaldoDiarioRestMapper;

@RestController
@RequestMapping("/v1/saldo")
public class SaldoDiarioRestEntrypoint {

	private static final FiltroSaldoDiarioRestMapper FILTRO_MAPPER = Mappers.getMapper(FiltroSaldoDiarioRestMapper.class);
	private static final PaginacaoSaldoDiarioRestMapper PAGINACAO_MAPPER = Mappers
			.getMapper(PaginacaoSaldoDiarioRestMapper.class);
	
	private BuscarSaldoDiarioUseCase buscarSaldoDiarioUseCase;
	
	public SaldoDiarioRestEntrypoint(BuscarSaldoDiarioUseCase buscarSaldoDiarioUseCase) {
		this.buscarSaldoDiarioUseCase = buscarSaldoDiarioUseCase;
	}

	@GetMapping
	public ResponseEntity<PaginacaoRest<SaldoDiarioRest>> buscar(FiltroSaldoDiarioRest filtroRest) {
		FiltroSaldo filtroSaldoDiario = FILTRO_MAPPER.fromFiltroLancamentoRest(filtroRest);
		Paginacao<SaldoDiario> paginacaoSaldoDiario = buscarSaldoDiarioUseCase.buscar(filtroSaldoDiario);

		PaginacaoRest<SaldoDiarioRest> paginacaoRest = PAGINACAO_MAPPER.toPaginacaoRest(paginacaoSaldoDiario);
		return ResponseEntity.ok(paginacaoRest);
	}

}
