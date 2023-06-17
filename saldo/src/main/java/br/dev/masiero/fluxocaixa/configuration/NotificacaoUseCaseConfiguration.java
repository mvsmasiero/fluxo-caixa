package br.dev.masiero.fluxocaixa.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.dev.masiero.fluxocaixa.core.dataprovider.SaldoDiarioDataProvider;
import br.dev.masiero.fluxocaixa.core.usecase.ProcessarNotificacaoUseCase;

@Configuration
public class NotificacaoUseCaseConfiguration {

	@Bean
	ProcessarNotificacaoUseCase processarNotificacaoUseCase(SaldoDiarioDataProvider saldoDiarioDataProvider) {
		return new ProcessarNotificacaoUseCase(saldoDiarioDataProvider);
	}

}
