package br.dev.masiero.fluxocaixa.core.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.dev.masiero.fluxocaixa.core.dataprovider.LancamentoDataProvider;
import br.dev.masiero.fluxocaixa.core.dataprovider.NotificacaoDataProvider;
import br.dev.masiero.fluxocaixa.core.entity.Lancamento;
import br.dev.masiero.fluxocaixa.core.entity.Notificacao;
import br.dev.masiero.fluxocaixa.core.exception.ValidacaoException;

@ExtendWith(MockitoExtension.class)
class RegistrarLancamentoUseCaseTest {

	private RegistrarLancamentoUseCase registrarLancamentoUseCase;

	@Mock
	private LancamentoDataProvider lancamentoDataProvider;
	
	@Mock
	private NotificacaoDataProvider notificacaoDataProvider;

	@BeforeEach
	void init() {
		registrarLancamentoUseCase = new RegistrarLancamentoUseCase(lancamentoDataProvider, notificacaoDataProvider);
	}

	@Test
	void deveRegistrarTest() {
		Lancamento lancamentoEntrada = createLancamento();
		Lancamento lancamentoRetorno = createLancamento();

		when(lancamentoDataProvider.incluir(lancamentoEntrada)).thenReturn(lancamentoRetorno);
		doNothing().when(notificacaoDataProvider).notificar(any(Notificacao.class));

		Lancamento lancamentoRetornado = registrarLancamentoUseCase.registrar(lancamentoEntrada);
		assertNotNull(lancamentoRetornado);
		assertEquals(lancamentoRetorno, lancamentoRetornado);
	}

	@Test
	void naoDeveRegistrarSeHouverErroValidacaoTest() {
		Lancamento lancamento = createLancamento();
		lancamento.setDescricao(null);
		lancamento.setData(null);
		lancamento.setValor(null);

		ValidacaoException exception = assertThrows(ValidacaoException.class,
				() -> registrarLancamentoUseCase.registrar(lancamento));
		assertEquals(3, exception.getViolacoes().size());
	}

	Lancamento createLancamento() {
		return Lancamento.builder().id(1L).descricao("Lancamento Teste").data(LocalDate.now())
				.valor(new BigDecimal("12.3")).build();
	}

}
