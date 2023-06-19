package br.dev.masiero.fluxocaixa.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
