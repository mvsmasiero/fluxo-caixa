package br.dev.masiero.fluxocaixa.core.usecase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.dev.masiero.fluxocaixa.core.dataprovider.SaldoDiarioDataProvider;
import br.dev.masiero.fluxocaixa.core.entity.Notificacao;
import br.dev.masiero.fluxocaixa.core.entity.SaldoDiario;
import br.dev.masiero.fluxocaixa.core.entity.Notificacao.TipoNotificacao;

public class ProcessarNotificacaoUseCase {

	private SaldoDiarioDataProvider saldoDiarioDataProvider;

	public ProcessarNotificacaoUseCase(SaldoDiarioDataProvider saldoDiarioDataProvider) {
		this.saldoDiarioDataProvider = saldoDiarioDataProvider;
	}

	public void processar(Notificacao notificacao) {

		LocalDate dataNotificacao = notificacao.getLancamentoOriginal().getData();
		BigDecimal valorAjuste = notificacao.getLancamentoOriginal().getValor();

		if (TipoNotificacao.EXCLUSAO == notificacao.getTipo()) {
			valorAjuste = valorAjuste.multiply(new BigDecimal("-1"));
		} else if (TipoNotificacao.ALTERACAO == notificacao.getTipo()) {
			valorAjuste = notificacao.getLancamentoAlterado().getValor().subtract(notificacao.getLancamentoOriginal().getValor());
		}
		
		List<SaldoDiario> saldoDiarioList = saldoDiarioDataProvider.recuperarTodosDesde(dataNotificacao);

		List<SaldoDiario> saldoDiarioListAtualizada = new ArrayList<>();

		if (saldoDiarioList != null && !saldoDiarioList.isEmpty()) {

			if (!dataNotificacao.equals(saldoDiarioList.get(0).getData())) {
				gerarSaldoBaseadoSaldoAnterior(dataNotificacao, valorAjuste, saldoDiarioListAtualizada);
			}
			
			for (SaldoDiario saldo : saldoDiarioList) {
				saldo.setValor(saldo.getValor().add(valorAjuste));
				saldoDiarioListAtualizada.add(saldo);
			}

		} else {
			gerarSaldoBaseadoSaldoAnterior(dataNotificacao, valorAjuste, saldoDiarioListAtualizada);
		}

		saldoDiarioDataProvider.atualizar(saldoDiarioListAtualizada);

	}

	private void gerarSaldoBaseadoSaldoAnterior(LocalDate dataNotificacao, BigDecimal valorAjuste,
			List<SaldoDiario> saldoDiarioListAtualizada) {

		BigDecimal ultimoSaldo = saldoDiarioDataProvider.buscarSaldoDataAnterior(dataNotificacao);
		
		SaldoDiario saldoInclusao = SaldoDiario.builder()
				.data(dataNotificacao)
				.valor(ultimoSaldo.add(valorAjuste)).build();

		saldoDiarioListAtualizada.add(saldoInclusao);
	}
	
}
