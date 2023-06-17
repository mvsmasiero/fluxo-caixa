package br.dev.masiero.fluxocaixa.core.usecase;

import java.util.Optional;

import br.dev.masiero.fluxocaixa.core.dataprovider.LancamentoDataProvider;
import br.dev.masiero.fluxocaixa.core.entity.Lancamento;
import br.dev.masiero.fluxocaixa.core.exception.RegistroNaoEncontradoException;

public class VisualizarLancamentoUseCase {

	private LancamentoDataProvider lancamentoDataProvider;

	public VisualizarLancamentoUseCase(LancamentoDataProvider lancamentoDataProvider) {
		this.lancamentoDataProvider = lancamentoDataProvider;
	}

	public Lancamento visualizar(long id) {

		Lancamento lancamentoEncontrado = this.lancamentoDataProvider.visualizar(id);

		return Optional.ofNullable(lancamentoEncontrado)
				.orElseThrow(() -> new RegistroNaoEncontradoException("Registro de ID " + id + " não encontrado"));

	}

}
