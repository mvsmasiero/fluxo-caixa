package br.dev.masiero.fluxocaixa.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.dev.masiero.fluxocaixa.core.dataprovider.LancamentoDataProvider;
import br.dev.masiero.fluxocaixa.dataprovider.database.lancamento.LancamentoJpaRepository;
import br.dev.masiero.fluxocaixa.dataprovider.database.lancamento.LancamentoMySQLDataProvider;

@Configuration
@EnableJpaRepositories(basePackages = "br.dev.masiero.fluxocaixa.dataprovider.database")
@EntityScan(basePackages = "br.dev.masiero.fluxocaixa.dataprovider.database")
public class LancamentoDataProviderConfiguration {

	@Bean
	LancamentoDataProvider lancamentoDataProvider(LancamentoJpaRepository lancamentoJpaRepository) {
		return new LancamentoMySQLDataProvider(lancamentoJpaRepository);
	}
	
}
