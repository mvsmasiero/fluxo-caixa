package br.dev.masiero.fluxocaixa.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.dev.masiero.fluxocaixa.core.dataprovider.SaldoDiarioDataProvider;
import br.dev.masiero.fluxocaixa.dataprovider.database.saldo.SaldoDiarioJpaRepository;
import br.dev.masiero.fluxocaixa.dataprovider.database.saldo.SaldoDiarioMySQLDataProvider;

@Configuration
@EnableJpaRepositories(basePackages = "br.dev.masiero.fluxocaixa.dataprovider.database")
@EntityScan(basePackages = "br.dev.masiero.fluxocaixa.dataprovider.database")
public class SaldoDiarioDataProviderConfiguration {

	@Bean
	SaldoDiarioDataProvider saldoDiarioDataProvider(SaldoDiarioJpaRepository saldoDiarioJpaRepository) {
		return new SaldoDiarioMySQLDataProvider(saldoDiarioJpaRepository);
	}
	
}
