package br.dev.masiero.fluxocaixa.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.dev.masiero.fluxocaixa.core.dataprovider.LancamentoDataProvider;
import br.dev.masiero.fluxocaixa.core.dataprovider.NotificacaoDataProvider;
import br.dev.masiero.fluxocaixa.core.usecase.AtualizarLancamentoUseCase;
import br.dev.masiero.fluxocaixa.core.usecase.BuscarLancamentosUseCase;
import br.dev.masiero.fluxocaixa.core.usecase.ExcluirLancamentoUseCase;
import br.dev.masiero.fluxocaixa.core.usecase.RegistrarLancamentoUseCase;
import br.dev.masiero.fluxocaixa.core.usecase.VisualizarLancamentoUseCase;

@Configuration
public class LancamentoUseCaseConfiguration {

	@Bean
	AtualizarLancamentoUseCase atualizarLancamentoUseCase(LancamentoDataProvider lancamentoDataProvider,
			NotificacaoDataProvider notificacaoDataProvider) {
		return new AtualizarLancamentoUseCase(lancamentoDataProvider, notificacaoDataProvider);
	}

	@Bean
	BuscarLancamentosUseCase buscarLancamentosUseCase(LancamentoDataProvider lancamentoDataProvider) {
		return new BuscarLancamentosUseCase(lancamentoDataProvider);
	}

	@Bean
	ExcluirLancamentoUseCase excluirLancamentoUseCase(LancamentoDataProvider lancamentoDataProvider,
			NotificacaoDataProvider notificacaoDataProvider) {
		return new ExcluirLancamentoUseCase(lancamentoDataProvider, notificacaoDataProvider);
	}

	@Bean
	RegistrarLancamentoUseCase registrarLancamentoUseCase(LancamentoDataProvider lancamentoDataProvider,
			NotificacaoDataProvider notificacaoDataProvider) {
		return new RegistrarLancamentoUseCase(lancamentoDataProvider, notificacaoDataProvider);
	}

	@Bean
	VisualizarLancamentoUseCase visualizarLancamentoUseCase(LancamentoDataProvider lancamentoDataProvider) {
		return new VisualizarLancamentoUseCase(lancamentoDataProvider);
	}

}
