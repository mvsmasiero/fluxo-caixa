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
import br.dev.masiero.fluxocaixa.core.exception.RegistroNaoEncontradoException;
import br.dev.masiero.fluxocaixa.core.exception.ValidacaoException;

public class AtualizarLancamentoUseCase {

	private LancamentoDataProvider lancamentoDataProvider;
	private NotificacaoDataProvider notificacaoDataProvider;

	public AtualizarLancamentoUseCase(LancamentoDataProvider lancamentoDataProvider, NotificacaoDataProvider notificacaoDataProvider) {
		this.lancamentoDataProvider = lancamentoDataProvider;
		this.notificacaoDataProvider = notificacaoDataProvider;
	}

	public Lancamento atualizar(Lancamento novoLancamento) {

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Lancamento>> violations = validator.validate(novoLancamento);
        
        if (!violations.isEmpty()) {
        	throw new ValidacaoException(violations);
        }
		
		if (!this.lancamentoDataProvider.existe(novoLancamento.getId())) {
			throw new RegistroNaoEncontradoException("Registro de ID " + novoLancamento.getId() + " não encontrado");
		}

		Lancamento lancamentoAtual = this.lancamentoDataProvider.visualizar(novoLancamento.getId());
		Lancamento lancamentoAtualizado = this.lancamentoDataProvider.atualizar(novoLancamento);
		
		Notificacao notificacao = Notificacao.builder()
				.tipo(Notificacao.TipoNotificacao.ALTERACAO)
				.lancamentoOriginal(lancamentoAtual)
				.lancamentoAlterado(lancamentoAtualizado)
				.build();
		
		this.notificacaoDataProvider.notificar(notificacao);
		
		return lancamentoAtualizado;

	}
}
