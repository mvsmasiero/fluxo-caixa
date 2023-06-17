package br.dev.masiero.fluxocaixa.core.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.dev.masiero.fluxocaixa.core.dataprovider.SaldoDiarioDataProvider;
import br.dev.masiero.fluxocaixa.core.entity.Lancamento;
import br.dev.masiero.fluxocaixa.core.entity.Notificacao;
import br.dev.masiero.fluxocaixa.core.entity.Notificacao.TipoNotificacao;
import br.dev.masiero.fluxocaixa.core.entity.SaldoDiario;

@ExtendWith(MockitoExtension.class)
class ProcessarNotificacaoUseCaseTest {

	private ProcessarNotificacaoUseCase processarNotificacaoUseCase;

	@Mock
	private SaldoDiarioDataProvider saldoDiarioDataProvider;

	@Captor
	ArgumentCaptor<List<SaldoDiario>> listaSaldoDiarioCaptor;

	@BeforeEach
	void init() {
		processarNotificacaoUseCase = new ProcessarNotificacaoUseCase(saldoDiarioDataProvider);
	}

	@Test
	void deveAtualizarValorDataExistenteNotificacaoInclusaoTest() {
		when(saldoDiarioDataProvider.recuperarTodosDesde(any(LocalDate.class))).thenReturn(gerarListaSaldoDiario());
		
		Notificacao notificacao = gerarNotificacao(TipoNotificacao.INCLUSAO, "2023-06-01", "200");
		
		processarNotificacaoUseCase.processar(notificacao);
		verify(saldoDiarioDataProvider).atualizar(listaSaldoDiarioCaptor.capture());
		
		assertEquals(5, listaSaldoDiarioCaptor.getValue().size());
		
		assertEquals(LocalDate.parse("2023-06-01"), listaSaldoDiarioCaptor.getValue().get(0).getData());
		assertEquals(new BigDecimal("1200"), listaSaldoDiarioCaptor.getValue().get(0).getValor());

		assertEquals(LocalDate.parse("2023-06-03"), listaSaldoDiarioCaptor.getValue().get(1).getData());
		assertEquals(new BigDecimal("1700"), listaSaldoDiarioCaptor.getValue().get(1).getValor());
		
		assertEquals(LocalDate.parse("2023-06-05"), listaSaldoDiarioCaptor.getValue().get(2).getData());
		assertEquals(new BigDecimal("1600"), listaSaldoDiarioCaptor.getValue().get(2).getValor());
		
		assertEquals(LocalDate.parse("2023-06-07"), listaSaldoDiarioCaptor.getValue().get(3).getData());
		assertEquals(new BigDecimal("1650"), listaSaldoDiarioCaptor.getValue().get(3).getValor());
		
		assertEquals(LocalDate.parse("2023-06-09"), listaSaldoDiarioCaptor.getValue().get(4).getData());
		assertEquals(new BigDecimal("1645"), listaSaldoDiarioCaptor.getValue().get(4).getValor());
	}

	@Test
	void deveAtualizarValorNovaDataIntermediariaNotificacaoInclusaoTest() {
		List<SaldoDiario> listaSaldo = gerarListaSaldoDiario();
		listaSaldo.remove(0);
		when(saldoDiarioDataProvider.recuperarTodosDesde(any(LocalDate.class))).thenReturn(listaSaldo);
		when(saldoDiarioDataProvider.buscarSaldoDataAnterior(any(LocalDate.class))).thenReturn(new BigDecimal(700));

		Notificacao notificacao = gerarNotificacao(TipoNotificacao.INCLUSAO, "2023-06-01", "-300");

		processarNotificacaoUseCase.processar(notificacao);
		verify(saldoDiarioDataProvider).atualizar(listaSaldoDiarioCaptor.capture());

		assertEquals(5, listaSaldoDiarioCaptor.getValue().size());

		assertEquals(LocalDate.parse("2023-06-01"), listaSaldoDiarioCaptor.getValue().get(0).getData());
		assertEquals(new BigDecimal("400"), listaSaldoDiarioCaptor.getValue().get(0).getValor());

		assertEquals(LocalDate.parse("2023-06-03"), listaSaldoDiarioCaptor.getValue().get(1).getData());
		assertEquals(new BigDecimal("1200"), listaSaldoDiarioCaptor.getValue().get(1).getValor());

		assertEquals(LocalDate.parse("2023-06-05"), listaSaldoDiarioCaptor.getValue().get(2).getData());
		assertEquals(new BigDecimal("1100"), listaSaldoDiarioCaptor.getValue().get(2).getValor());

		assertEquals(LocalDate.parse("2023-06-07"), listaSaldoDiarioCaptor.getValue().get(3).getData());
		assertEquals(new BigDecimal("1150"), listaSaldoDiarioCaptor.getValue().get(3).getValor());

		assertEquals(LocalDate.parse("2023-06-09"), listaSaldoDiarioCaptor.getValue().get(4).getData());
		assertEquals(new BigDecimal("1145"), listaSaldoDiarioCaptor.getValue().get(4).getValor());
	}

	@Test
	void deveAtualizarValorDataExistenteNotificacaoExclusaoValorPositivoTest() {
		when(saldoDiarioDataProvider.recuperarTodosDesde(any(LocalDate.class))).thenReturn(gerarListaSaldoDiario());
		
		Notificacao notificacao = gerarNotificacao(TipoNotificacao.EXCLUSAO, "2023-06-01", "300");
		
		processarNotificacaoUseCase.processar(notificacao);
		verify(saldoDiarioDataProvider).atualizar(listaSaldoDiarioCaptor.capture());		

		assertEquals(5, listaSaldoDiarioCaptor.getValue().size());

		assertEquals(LocalDate.parse("2023-06-01"), listaSaldoDiarioCaptor.getValue().get(0).getData());
		assertEquals(new BigDecimal("700"), listaSaldoDiarioCaptor.getValue().get(0).getValor());

		assertEquals(LocalDate.parse("2023-06-03"), listaSaldoDiarioCaptor.getValue().get(1).getData());
		assertEquals(new BigDecimal("1200"), listaSaldoDiarioCaptor.getValue().get(1).getValor());

		assertEquals(LocalDate.parse("2023-06-05"), listaSaldoDiarioCaptor.getValue().get(2).getData());
		assertEquals(new BigDecimal("1100"), listaSaldoDiarioCaptor.getValue().get(2).getValor());

		assertEquals(LocalDate.parse("2023-06-07"), listaSaldoDiarioCaptor.getValue().get(3).getData());
		assertEquals(new BigDecimal("1150"), listaSaldoDiarioCaptor.getValue().get(3).getValor());

		assertEquals(LocalDate.parse("2023-06-09"), listaSaldoDiarioCaptor.getValue().get(4).getData());
		assertEquals(new BigDecimal("1145"), listaSaldoDiarioCaptor.getValue().get(4).getValor());
	}

	@Test
	void deveAtualizarValorDataExistenteNotificacaoExclusaoValorNegativoTest() {
		when(saldoDiarioDataProvider.recuperarTodosDesde(any(LocalDate.class))).thenReturn(gerarListaSaldoDiario());
		
		Notificacao notificacao = gerarNotificacao(TipoNotificacao.EXCLUSAO, "2023-06-01", "-300");
		
		processarNotificacaoUseCase.processar(notificacao);
		verify(saldoDiarioDataProvider).atualizar(listaSaldoDiarioCaptor.capture());

		assertEquals(5, listaSaldoDiarioCaptor.getValue().size());

		assertEquals(LocalDate.parse("2023-06-01"), listaSaldoDiarioCaptor.getValue().get(0).getData());
		assertEquals(new BigDecimal("1300"), listaSaldoDiarioCaptor.getValue().get(0).getValor());

		assertEquals(LocalDate.parse("2023-06-03"), listaSaldoDiarioCaptor.getValue().get(1).getData());
		assertEquals(new BigDecimal("1800"), listaSaldoDiarioCaptor.getValue().get(1).getValor());

		assertEquals(LocalDate.parse("2023-06-05"), listaSaldoDiarioCaptor.getValue().get(2).getData());
		assertEquals(new BigDecimal("1700"), listaSaldoDiarioCaptor.getValue().get(2).getValor());

		assertEquals(LocalDate.parse("2023-06-07"), listaSaldoDiarioCaptor.getValue().get(3).getData());
		assertEquals(new BigDecimal("1750"), listaSaldoDiarioCaptor.getValue().get(3).getValor());

		assertEquals(LocalDate.parse("2023-06-09"), listaSaldoDiarioCaptor.getValue().get(4).getData());
		assertEquals(new BigDecimal("1745"), listaSaldoDiarioCaptor.getValue().get(4).getValor());
	}

	@Test
	void deveAtualizarValorDataExistenteNotificacaoAlteracaoValorMaiorTest() {
		when(saldoDiarioDataProvider.recuperarTodosDesde(any(LocalDate.class))).thenReturn(gerarListaSaldoDiario());
		
		Notificacao notificacao = gerarNotificacao(TipoNotificacao.ALTERACAO, "2023-06-01", "300", "350");
		
		processarNotificacaoUseCase.processar(notificacao);
		verify(saldoDiarioDataProvider).atualizar(listaSaldoDiarioCaptor.capture());		

		assertEquals(5, listaSaldoDiarioCaptor.getValue().size());

		assertEquals(LocalDate.parse("2023-06-01"), listaSaldoDiarioCaptor.getValue().get(0).getData());
		assertEquals(new BigDecimal("1050"), listaSaldoDiarioCaptor.getValue().get(0).getValor());

		assertEquals(LocalDate.parse("2023-06-03"), listaSaldoDiarioCaptor.getValue().get(1).getData());
		assertEquals(new BigDecimal("1550"), listaSaldoDiarioCaptor.getValue().get(1).getValor());

		assertEquals(LocalDate.parse("2023-06-05"), listaSaldoDiarioCaptor.getValue().get(2).getData());
		assertEquals(new BigDecimal("1450"), listaSaldoDiarioCaptor.getValue().get(2).getValor());

		assertEquals(LocalDate.parse("2023-06-07"), listaSaldoDiarioCaptor.getValue().get(3).getData());
		assertEquals(new BigDecimal("1500"), listaSaldoDiarioCaptor.getValue().get(3).getValor());

		assertEquals(LocalDate.parse("2023-06-09"), listaSaldoDiarioCaptor.getValue().get(4).getData());
		assertEquals(new BigDecimal("1495"), listaSaldoDiarioCaptor.getValue().get(4).getValor());
	}

	@Test
	void deveAtualizarValorDataExistenteNotificacaoAlteracaoValorMenorTest() {
		when(saldoDiarioDataProvider.recuperarTodosDesde(any(LocalDate.class))).thenReturn(gerarListaSaldoDiario());
		
		Notificacao notificacao = gerarNotificacao(TipoNotificacao.ALTERACAO, "2023-06-01", "300", "250");
		
		processarNotificacaoUseCase.processar(notificacao);
		verify(saldoDiarioDataProvider).atualizar(listaSaldoDiarioCaptor.capture());		

		assertEquals(5, listaSaldoDiarioCaptor.getValue().size());

		assertEquals(LocalDate.parse("2023-06-01"), listaSaldoDiarioCaptor.getValue().get(0).getData());
		assertEquals(new BigDecimal("950"), listaSaldoDiarioCaptor.getValue().get(0).getValor());

		assertEquals(LocalDate.parse("2023-06-03"), listaSaldoDiarioCaptor.getValue().get(1).getData());
		assertEquals(new BigDecimal("1450"), listaSaldoDiarioCaptor.getValue().get(1).getValor());

		assertEquals(LocalDate.parse("2023-06-05"), listaSaldoDiarioCaptor.getValue().get(2).getData());
		assertEquals(new BigDecimal("1350"), listaSaldoDiarioCaptor.getValue().get(2).getValor());

		assertEquals(LocalDate.parse("2023-06-07"), listaSaldoDiarioCaptor.getValue().get(3).getData());
		assertEquals(new BigDecimal("1400"), listaSaldoDiarioCaptor.getValue().get(3).getValor());

		assertEquals(LocalDate.parse("2023-06-09"), listaSaldoDiarioCaptor.getValue().get(4).getData());
		assertEquals(new BigDecimal("1395"), listaSaldoDiarioCaptor.getValue().get(4).getValor());
	}

	private Notificacao gerarNotificacao(TipoNotificacao tipoNotificacao, String data, String valorOriginal) {
		return Notificacao.builder().tipo(tipoNotificacao)
				.lancamentoOriginal(
						Lancamento.builder().data(LocalDate.parse(data)).valor(new BigDecimal(valorOriginal)).build())
				.build();
	}

	private Notificacao gerarNotificacao(TipoNotificacao tipoNotificacao, String data, String valorOriginal,
			String novoValor) {
		return Notificacao.builder().tipo(tipoNotificacao)
				.lancamentoOriginal(
						Lancamento.builder().data(LocalDate.parse(data)).valor(new BigDecimal(valorOriginal)).build())
				.lancamentoAlterado(
						Lancamento.builder().data(LocalDate.parse(data)).valor(new BigDecimal(novoValor)).build())
				.build();
	}

	private List<SaldoDiario> gerarListaSaldoDiario() {
		List<SaldoDiario> saldoDiarioList = new ArrayList<>();

		saldoDiarioList.add(gerarSaldoDiario("2023-06-01", "1000"));
		saldoDiarioList.add(gerarSaldoDiario("2023-06-03", "1500"));
		saldoDiarioList.add(gerarSaldoDiario("2023-06-05", "1400"));
		saldoDiarioList.add(gerarSaldoDiario("2023-06-07", "1450"));
		saldoDiarioList.add(gerarSaldoDiario("2023-06-09", "1445"));

		return saldoDiarioList;
	}

	private SaldoDiario gerarSaldoDiario(String data, String valor) {
		return SaldoDiario.builder().data(LocalDate.parse(data)).valor(new BigDecimal(valor)).build();
	}

}
