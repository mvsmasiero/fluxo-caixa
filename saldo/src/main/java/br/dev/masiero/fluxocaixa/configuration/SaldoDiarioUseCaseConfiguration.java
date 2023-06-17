package br.dev.masiero.fluxocaixa.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.dev.masiero.fluxocaixa.core.dataprovider.SaldoDiarioDataProvider;
import br.dev.masiero.fluxocaixa.core.usecase.BuscarSaldoDiarioUseCase;

@Configuration
public class SaldoDiarioUseCaseConfiguration {

	@Bean
	BuscarSaldoDiarioUseCase buscarSaldoDiarioUseCase(SaldoDiarioDataProvider saldoDiarioDataProvider) {
		return new BuscarSaldoDiarioUseCase(saldoDiarioDataProvider);
	}

}
