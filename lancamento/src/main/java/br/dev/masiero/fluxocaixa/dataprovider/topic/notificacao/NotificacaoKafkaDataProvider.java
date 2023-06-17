package br.dev.masiero.fluxocaixa.dataprovider.topic.notificacao;

import org.springframework.kafka.core.KafkaTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.dev.masiero.fluxocaixa.core.dataprovider.NotificacaoDataProvider;
import br.dev.masiero.fluxocaixa.core.entity.Notificacao;
import br.dev.masiero.fluxocaixa.core.exception.ErroSistemicoException;

public class NotificacaoKafkaDataProvider implements NotificacaoDataProvider {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	private KafkaTemplate<String, String> kafkaTemplate;
	private String topicName;

	public NotificacaoKafkaDataProvider(KafkaTemplate<String, String> kafkaTemplate, String topicName) {
		this.kafkaTemplate = kafkaTemplate;
		this.topicName = topicName;
	}

	@Override
	public void notificar(Notificacao notificacao) {
		
		try {
			String notificacaoJson = OBJECT_MAPPER.writeValueAsString(notificacao);
		
			kafkaTemplate.send(topicName, notificacaoJson);
		} catch (Exception e) {
			throw new ErroSistemicoException("Erro ao enviar a notificacao", e);
		}
	}

	
}
