package br.dev.masiero.fluxocaixa.core.usecase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.dev.masiero.fluxocaixa.core.dataprovider.LancamentoDataProvider;
import br.dev.masiero.fluxocaixa.core.dataprovider.NotificacaoDataProvider;
import br.dev.masiero.fluxocaixa.core.entity.Notificacao;
import br.dev.masiero.fluxocaixa.core.exception.RegistroNaoEncontradoException;

@ExtendWith(MockitoExtension.class)
class ExcluirLancamentoUseCaseTest {

	private ExcluirLancamentoUseCase excluirLancamentoUseCase;

	@Mock
	private LancamentoDataProvider lancamentoDataProvider;
	
	@Mock
	private NotificacaoDataProvider notificacaoDataProvider;

	@BeforeEach
	void init() {
		excluirLancamentoUseCase = new ExcluirLancamentoUseCase(lancamentoDataProvider, notificacaoDataProvider);
	}

	@Test
	void deveRetornarExcecaoSeNaoEncontrarRegistroTest() {
		when(lancamentoDataProvider.existe(anyLong())).thenReturn(false);
		
		assertThrows(RegistroNaoEncontradoException.class, () -> excluirLancamentoUseCase.excluir(1L));
	}

	@Test
	void deveExcluirSeEncontrarRegistroTest() {
		when(lancamentoDataProvider.existe(anyLong())).thenReturn(true);
		doNothing().when(lancamentoDataProvider).excluir(anyLong());
		doNothing().when(notificacaoDataProvider).notificar(any(Notificacao.class));
		
		assertDoesNotThrow(() -> excluirLancamentoUseCase.excluir(1L));
	}

}
