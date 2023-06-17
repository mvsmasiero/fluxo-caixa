package br.dev.masiero.fluxocaixa.entrypoint.rest.lancamento;

import org.mapstruct.factory.Mappers;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.masiero.fluxocaixa.core.entity.FiltroLancamento;
import br.dev.masiero.fluxocaixa.core.entity.Lancamento;
import br.dev.masiero.fluxocaixa.core.entity.Paginacao;
import br.dev.masiero.fluxocaixa.entrypoint.rest.lancamento.entity.FiltroLancamentoRest;
import br.dev.masiero.fluxocaixa.entrypoint.rest.lancamento.entity.LancamentoRest;
import br.dev.masiero.fluxocaixa.entrypoint.rest.lancamento.entity.PaginacaoRest;
import br.dev.masiero.fluxocaixa.entrypoint.rest.lancamento.entity.mapper.FiltroLancamentoRestMapper;
import br.dev.masiero.fluxocaixa.entrypoint.rest.lancamento.entity.mapper.LancamentoRestMapper;
import br.dev.masiero.fluxocaixa.entrypoint.rest.lancamento.entity.mapper.PaginacaoLancamentoRestMapper;
import br.dev.masiero.fluxocaixa.core.usecase.AtualizarLancamentoUseCase;
import br.dev.masiero.fluxocaixa.core.usecase.BuscarLancamentosUseCase;
import br.dev.masiero.fluxocaixa.core.usecase.ExcluirLancamentoUseCase;
import br.dev.masiero.fluxocaixa.core.usecase.RegistrarLancamentoUseCase;
import br.dev.masiero.fluxocaixa.core.usecase.VisualizarLancamentoUseCase;

@RestController
@RequestMapping("/v1/lancamento")
public class LancamentoRestEntrypoint {

	private static final LancamentoRestMapper LANCAMENTO_MAPPER = Mappers.getMapper(LancamentoRestMapper.class);
	private static final FiltroLancamentoRestMapper FILTRO_MAPPER = Mappers.getMapper(FiltroLancamentoRestMapper.class);
	private static final PaginacaoLancamentoRestMapper PAGINACAO_MAPPER = Mappers
			.getMapper(PaginacaoLancamentoRestMapper.class);

	private AtualizarLancamentoUseCase atualizarLancamentoUseCase;
	private BuscarLancamentosUseCase buscarLancamentosUseCase;
	private ExcluirLancamentoUseCase excluirLancamentoUseCase;
	private RegistrarLancamentoUseCase registrarLancamentoUseCase;
	private VisualizarLancamentoUseCase visualizarLancamentoUseCase;

	public LancamentoRestEntrypoint(AtualizarLancamentoUseCase atualizarLancamentoUseCase,
			BuscarLancamentosUseCase buscarLancamentosUseCase, ExcluirLancamentoUseCase excluirLancamentoUseCase,
			RegistrarLancamentoUseCase registrarLancamentoUseCase,
			VisualizarLancamentoUseCase visualizarLancamentoUseCase) {
		this.atualizarLancamentoUseCase = atualizarLancamentoUseCase;
		this.buscarLancamentosUseCase = buscarLancamentosUseCase;
		this.excluirLancamentoUseCase = excluirLancamentoUseCase;
		this.registrarLancamentoUseCase = registrarLancamentoUseCase;
		this.visualizarLancamentoUseCase = visualizarLancamentoUseCase;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LancamentoRest> registrarLancamento(@RequestBody LancamentoRest lancamentoRest) {
		Lancamento lancamento = LANCAMENTO_MAPPER.fromLancamentoRest(lancamentoRest);
		lancamento = registrarLancamentoUseCase.registrar(lancamento);

		LancamentoRest lancamentoRestRegistrado = LANCAMENTO_MAPPER.toLancamentoRest(lancamento);
		return ResponseEntity.ok(lancamentoRestRegistrado);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LancamentoRest> atualizarLancamento(@RequestBody LancamentoRest lancamentoRest) {
		Lancamento lancamento = LANCAMENTO_MAPPER.fromLancamentoRest(lancamentoRest);
		lancamento = atualizarLancamentoUseCase.atualizar(lancamento);

		LancamentoRest lancamentoRestAtualizado = LANCAMENTO_MAPPER.toLancamentoRest(lancamento);
		return ResponseEntity.ok(lancamentoRestAtualizado);
	}

	@GetMapping
	public ResponseEntity<PaginacaoRest<LancamentoRest>> buscar(FiltroLancamentoRest filtroRest) {
		FiltroLancamento filtroLancameto = FILTRO_MAPPER.fromFiltroLancamentoRest(filtroRest);
		Paginacao<Lancamento> paginacaoLancamento = buscarLancamentosUseCase.buscar(filtroLancameto);

		PaginacaoRest<LancamentoRest> paginacaoRest = PAGINACAO_MAPPER.toPaginacaoRest(paginacaoLancamento);
		return ResponseEntity.ok(paginacaoRest);
	}

	@GetMapping("{id}")
	public ResponseEntity<LancamentoRest> visualizar(@PathVariable long id) {
		Lancamento lancamento = visualizarLancamentoUseCase.visualizar(id);

		LancamentoRest lancamentoRest = LANCAMENTO_MAPPER.toLancamentoRest(lancamento);
		return ResponseEntity.ok(lancamentoRest);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> excluir(@PathVariable long id) {
		excluirLancamentoUseCase.excluir(id);

		return ResponseEntity.ok().build();
	}
}
