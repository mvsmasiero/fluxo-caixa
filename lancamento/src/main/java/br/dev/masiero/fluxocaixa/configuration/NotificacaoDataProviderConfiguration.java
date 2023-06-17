package br.dev.masiero.fluxocaixa.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;

import br.dev.masiero.fluxocaixa.core.dataprovider.NotificacaoDataProvider;
import br.dev.masiero.fluxocaixa.dataprovider.topic.notificacao.NotificacaoKafkaDataProvider;

@Configuration
@EnableKafka
public class NotificacaoDataProviderConfiguration {

	@Bean
	NotificacaoDataProvider notificacaoDataProvider(@Value("${topic.name.producer}") String topicName,
			KafkaTemplate<String, String> kafkaTemplate) {
		return new NotificacaoKafkaDataProvider(kafkaTemplate, topicName);
	}
}
