package br.dev.masiero.fluxocaixa.core.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.dev.masiero.fluxocaixa.core.dataprovider.LancamentoDataProvider;
import br.dev.masiero.fluxocaixa.core.entity.Lancamento;
import br.dev.masiero.fluxocaixa.core.exception.RegistroNaoEncontradoException;

@ExtendWith(MockitoExtension.class)
class VisualizarLancamentoUseCaseTest {

	private VisualizarLancamentoUseCase visualizarLancamentoUseCase;

	@Mock
	private LancamentoDataProvider lancamentoDataProvider;

	@BeforeEach
	void init() {
		visualizarLancamentoUseCase = new VisualizarLancamentoUseCase(lancamentoDataProvider);
	}

	@Test
	void deveRetornarExcecaoSeNaoEncontrarRegistroTest() {
		when(lancamentoDataProvider.visualizar(anyLong())).thenReturn(null);
		
		assertThrows(RegistroNaoEncontradoException.class, () -> visualizarLancamentoUseCase.visualizar(1L));
	}

	@Test
	void deveRetornarSeEncontrarRegistroTest() {
		when(lancamentoDataProvider.visualizar(anyLong())).thenReturn(Lancamento.builder().build());
		
		Lancamento lancamento = visualizarLancamentoUseCase.visualizar(1L);
		
		assertNotNull(lancamento);
	}
}
