package br.dev.masiero.fluxocaixa.core.usecase;

import br.dev.masiero.fluxocaixa.core.dataprovider.LancamentoDataProvider;
import br.dev.masiero.fluxocaixa.core.dataprovider.NotificacaoDataProvider;
import br.dev.masiero.fluxocaixa.core.entity.Lancamento;
import br.dev.masiero.fluxocaixa.core.entity.Notificacao;
import br.dev.masiero.fluxocaixa.core.exception.RegistroNaoEncontradoException;

public class ExcluirLancamentoUseCase {

	private LancamentoDataProvider lancamentoDataProvider;
	private NotificacaoDataProvider notificacaoDataProvider;
	
	public ExcluirLancamentoUseCase(LancamentoDataProvider lancamentoDataProvider, NotificacaoDataProvider notificacaoDataProvider) {
		this.lancamentoDataProvider = lancamentoDataProvider;
		this.notificacaoDataProvider = notificacaoDataProvider;
	}

	public void excluir(long id) throws RegistroNaoEncontradoException {
		
		if(!this.lancamentoDataProvider.existe(id)) {
			throw new RegistroNaoEncontradoException("Registro de ID " + id + " não encontrado");
		}
		
		Lancamento lancamentoAtual = this.lancamentoDataProvider.visualizar(id);
		
		this.lancamentoDataProvider.excluir(id);

		Notificacao notificacao = Notificacao.builder()
				.tipo(Notificacao.TipoNotificacao.EXCLUSAO)
				.lancamentoOriginal(lancamentoAtual)
				.build();
		
		this.notificacaoDataProvider.notificar(notificacao);	
	}
	
	
}
