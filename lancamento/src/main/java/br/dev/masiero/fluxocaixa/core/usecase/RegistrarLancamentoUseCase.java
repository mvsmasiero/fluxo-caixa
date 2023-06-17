package br.dev.masiero.fluxocaixa.core.usecase;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.dev.masiero.fluxocaixa.core.dataprovider.LancamentoDataProvider;
import br.dev.masiero.fluxocaixa.core.dataprovider.NotificacaoDataProvider;
import br.dev.masiero.fluxocaixa.core.entity.Lancamento;
import br.dev.masiero.fluxocaixa.core.entity.Notificacao;
import br.dev.masiero.fluxocaixa.core.exception.ValidacaoException;

public class RegistrarLancamentoUseCase {

	private LancamentoDataProvider lancamentoDataProvider;
	private NotificacaoDataProvider notificacaoDataProvider;

	public RegistrarLancamentoUseCase(LancamentoDataProvider lancamentoDataProvider, NotificacaoDataProvider notificacaoDataProvider) {
		this.lancamentoDataProvider = lancamentoDataProvider;
		this.notificacaoDataProvider = notificacaoDataProvider;
	}

	public Lancamento registrar(Lancamento lancamento) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Lancamento>> violations = validator.validate(lancamento);

		if (!violations.isEmpty()) {
			throw new ValidacaoException(violations);
		}

		Lancamento lancamentoIncluido = this.lancamentoDataProvider.incluir(lancamento);
		
		Notificacao notificacao = Notificacao.builder()
				.tipo(Notificacao.TipoNotificacao.INCLUSAO)
				.lancamentoOriginal(lancamentoIncluido)
				.build();
		
		this.notificacaoDataProvider.notificar(notificacao);
		
		return lancamentoIncluido;
	}

}
