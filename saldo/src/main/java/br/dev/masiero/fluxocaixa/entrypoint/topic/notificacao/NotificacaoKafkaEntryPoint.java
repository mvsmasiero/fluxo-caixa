package br.dev.masiero.fluxocaixa.entrypoint.topic.notificacao;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.dev.masiero.fluxocaixa.core.entity.Notificacao;
import br.dev.masiero.fluxocaixa.core.exception.ErroSistemicoException;
import br.dev.masiero.fluxocaixa.core.usecase.ProcessarNotificacaoUseCase;

public class NotificacaoKafkaEntryPoint {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private ProcessarNotificacaoUseCase processarNotificacaoUseCase;

	public NotificacaoKafkaEntryPoint(ProcessarNotificacaoUseCase processarNotificacaoUseCase) {
		this.processarNotificacaoUseCase = processarNotificacaoUseCase;
	}

	@KafkaListener(topics = "${topic.name.consumer}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(ConsumerRecord<String, String> payload) {
		try {
			String payloadValue = payload.value();
			Notificacao notificacao = OBJECT_MAPPER.readValue(payloadValue, Notificacao.class);

			this.processarNotificacaoUseCase.processar(notificacao);
		} catch (Exception e) {
			throw new ErroSistemicoException("Erro ao processar a notificacao", e);
		}
	}

}
