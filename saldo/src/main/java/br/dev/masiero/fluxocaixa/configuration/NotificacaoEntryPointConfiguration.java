package br.dev.masiero.fluxocaixa.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

import br.dev.masiero.fluxocaixa.core.usecase.ProcessarNotificacaoUseCase;
import br.dev.masiero.fluxocaixa.entrypoint.topic.notificacao.NotificacaoKafkaEntryPoint;

@Configuration
@EnableKafka
public class NotificacaoEntryPointConfiguration {

	@Bean
	NotificacaoKafkaEntryPoint notificacaoDataProvider(
			ProcessarNotificacaoUseCase processarNotificacaoUseCase) {
		return new NotificacaoKafkaEntryPoint(processarNotificacaoUseCase);
	}
}
