package br.dev.masiero.fluxocaixa.core.usecase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.dev.masiero.fluxocaixa.core.dataprovider.SaldoDiarioDataProvider;
import br.dev.masiero.fluxocaixa.core.entity.Notificacao;
import br.dev.masiero.fluxocaixa.core.entity.SaldoDiario;

public class ProcessarNotificacaoUseCase {

	private SaldoDiarioDataProvider saldoDiarioDataProvider;

	public void processar(Notificacao notificacao) {

		LocalDate data = notificacao.getLancamentoOriginal().getData();
		BigDecimal valorAjuste = notificacao.getLancamentoOriginal().getValor();

		if (Notificacao.TipoNotificacao.ALTERACAO == notificacao.getTipo()) {
			valorAjuste = notificacao.getLancamentoAlterado().getValor()
					.min(notificacao.getLancamentoOriginal().getValor());
		} else if (Notificacao.TipoNotificacao.EXCLUSAO == notificacao.getTipo()) {
			valorAjuste = valorAjuste.min(new BigDecimal("-1"));
		}

		List<SaldoDiario> saldoDiarioList = saldoDiarioDataProvider.recuperarTodosDesde(data);

		List<SaldoDiario> saldoDiarioListAtualizada = new ArrayList<>();

		if (saldoDiarioList != null && !saldoDiarioList.isEmpty()) {

			if (!data.equals(saldoDiarioList.get(0).getData())) {
				// precisa obter o saldo da ultima data anterior para fazer o calculo
				
				SaldoDiario saldo = SaldoDiario.builder().data(data).valor(valorAjuste).build();

				saldoDiarioListAtualizada.add(saldo);
			}

			for (SaldoDiario saldo : saldoDiarioList) {
				saldo.setValor(saldo.getValor().add(valorAjuste));
				saldoDiarioListAtualizada.add(saldo);
			}

		}

		saldoDiarioDataProvider.atualizar(saldoDiarioListAtualizada);

	}

}
