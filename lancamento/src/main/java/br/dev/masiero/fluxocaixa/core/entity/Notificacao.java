package br.dev.masiero.fluxocaixa.core.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Notificacao {

	public enum TipoNotificacao {
		INCLUSAO,
		ALTERACAO,
		EXCLUSAO
	}
	
	private TipoNotificacao tipo;
	private Lancamento lancamentoOriginal;
	private Lancamento lancamentoAlterado;
	
}
