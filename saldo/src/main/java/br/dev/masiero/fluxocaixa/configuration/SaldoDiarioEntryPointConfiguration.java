package br.dev.masiero.fluxocaixa.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.dev.masiero.fluxocaixa.core.usecase.BuscarSaldoDiarioUseCase;
import br.dev.masiero.fluxocaixa.entrypoint.rest.RestExceptionHandler;
import br.dev.masiero.fluxocaixa.entrypoint.rest.saldo.SaldoDiarioRestEntrypoint;

@Configuration
public class SaldoDiarioEntryPointConfiguration {

	@Bean
	SaldoDiarioRestEntrypoint saldoDiarioRestEntrypoint(BuscarSaldoDiarioUseCase buscarSaldoDiarioUseCase) {
		return new SaldoDiarioRestEntrypoint(buscarSaldoDiarioUseCase);
	}

	@Bean
	RestExceptionHandler restExceptionHandler() {
		return new RestExceptionHandler();
	}

}
