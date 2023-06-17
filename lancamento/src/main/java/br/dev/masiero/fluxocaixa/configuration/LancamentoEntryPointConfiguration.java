package br.dev.masiero.fluxocaixa.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.dev.masiero.fluxocaixa.entrypoint.rest.RestExceptionHandler;
import br.dev.masiero.fluxocaixa.entrypoint.rest.lancamento.LancamentoRestEntrypoint;
import br.dev.masiero.fluxocaixa.core.usecase.AtualizarLancamentoUseCase;
import br.dev.masiero.fluxocaixa.core.usecase.BuscarLancamentosUseCase;
import br.dev.masiero.fluxocaixa.core.usecase.ExcluirLancamentoUseCase;
import br.dev.masiero.fluxocaixa.core.usecase.RegistrarLancamentoUseCase;
import br.dev.masiero.fluxocaixa.core.usecase.VisualizarLancamentoUseCase;

@Configuration
public class LancamentoEntryPointConfiguration {

	@Bean
	LancamentoRestEntrypoint lancamentoRestEntrypoint(AtualizarLancamentoUseCase atualizarLancamentoUseCase,
			BuscarLancamentosUseCase buscarLancamentosUseCase, ExcluirLancamentoUseCase excluirLancamentoUseCase,
			RegistrarLancamentoUseCase registrarLancamentoUseCase,
			VisualizarLancamentoUseCase visualizarLancamentoUseCase) {
		return new LancamentoRestEntrypoint(atualizarLancamentoUseCase, buscarLancamentosUseCase,
				excluirLancamentoUseCase, registrarLancamentoUseCase, visualizarLancamentoUseCase);
	}
	
	@Bean
	RestExceptionHandler restExceptionHandler() {
		return new RestExceptionHandler();
	}

}
